from keras.models import model_from_json
from keras.preprocessing.image import load_img, img_to_array
from keras.applications.imagenet_utils import preprocess_input

from flask import Flask, jsonify, abort, request, make_response, url_for,redirect, render_template
from werkzeug.utils import secure_filename
import sqlite3
import numpy as np
import os
import io
import tensorflow as tf

#Load David Sandberg's Tensorflow/Keras pretrained model
#Created thanks to by Sefik Sernegil and Andrew Ng lessons
model = model_from_json(open("model/facenet_model.json", "r").read())
model.load_weights('model/facenet_weights.h5')
#Set model validation threshold
threshold = 0.30

#default graph initialization for flask issue fix
global graph
graph = tf.get_default_graph()

#Initialize sqlite connection

def adapt_array(arr):
    out = io.BytesIO()
    np.save(out, arr)
    out.seek(0)
    return sqlite3.Binary(out.read())

def convert_array(text):
    out = io.BytesIO(text)
    out.seek(0)
    return np.load(out)

# Converts np.array to TEXT when inserting, and np.array to TEXT when selecting
sqlite3.register_adapter(np.ndarray, adapt_array)
sqlite3.register_converter("array", convert_array)

con = sqlite3.connect('base.db', detect_types=sqlite3.PARSE_DECLTYPES)
c = con.cursor()
c.execute("CREATE TABLE IF NOT EXISTS users(Name text, Face array)")
con.commit()
con.close()

is_open = 'true'

app = Flask(__name__)

def distance(from_db, from_user):
    dist = np.sum((from_db - from_user)**2)
    return np.sqrt(dist)

def process_img(img_path):
    img = load_img(img_path, target_size=(160,160))
    img = img_to_array(img)
    img = np.expand_dims(img, axis=0)
    img = preprocess_input(img)
    return img

#validate face owner's permissions
@app.route('/validate', methods=['POST'])
def validate():
    if not 'file' in request.files:
        return "No file!"
    file = request.files['file']
    filename = secure_filename(file.filename)
    img_path = os.path.join('/home/mbien/sw_uploads', filename)
    file.save(img_path)
    img = process_img(img_path)
    os.remove(img_path)
    global is_open
    with graph.as_default():
        user_embedding = model.predict(img)[0,:]
        user_embedding = user_embedding/np.linalg.norm(user_embedding)
        con = sqlite3.connect('base.db', detect_types=sqlite3.PARSE_DECLTYPES)
        c = con.cursor()
        c.execute("SELECT * FROM users")
        users = c.fetchall()
        con.close()
        for user in users:
            if distance(user[1], user_embedding) < threshold:
                is_open = 'true'
                return "Success, open! Welcome " + user[0] #send this to beagle
    is_open = 'false'
    return "Unauthorized"

#add new user to database (filename is username)
@app.route('/insert', methods=['POST'])
def insert():
    if not 'file' in request.files:
        return "No file!"
    file = request.files['file']
    filename = secure_filename(file.filename)
    img_path = os.path.join('/home/mbien/sw_uploads', filename)
    file.save(img_path)
    img = process_img(img_path)
    os.remove(img_path)
    with graph.as_default():
        embedding = model.predict(img)[0,:]
        embedding = embedding/np.linalg.norm(embedding)
        con = sqlite3.connect('base.db', detect_types=sqlite3.PARSE_DECLTYPES)
        c = con.cursor()
        c.execute("INSERT INTO users VALUES(?,?)", (os.path.splitext(filename)[0], embedding))
        con.commit()
        con.close()
        return "User added successfully"

@app.route('/open', methods=['GET'])
def opening():
    global is_open
    return is_open

if __name__ == '__main__':
    app.run(debug = True, host= '0.0.0.0')
