from flask import Flask, g, request, jsonify
import sqlite3

app = Flask(__name__)

DATABASE = './db.sqlite3'

def get_db():
    db = getattr(g, '_database', None)
    if db is None:
        db = g._database = sqlite3.connect(DATABASE)
    return db


@app.teardown_appcontext
def close_connection(exception):
    db = getattr(g, '_database', None)
    if db is not None:
        db.close()

def check(user, password):
    con = get_db()
    cur = con.cursor()
    cur.execute("SELECT * FROM users WHERE name=?", [user])
    results = cur.fetchone()

    if user!="" and password!="" and (results is None or len(results) == 0):
        cur.execute("INSERT INTO users(name, password, score) VALUES (?, ?, 0)", [user, password])
        con.commit()
        return 1
    elif results[2] == password:
        cur.close()
        return 1
    else:
        return 0


@app.route('/load')
def score():
    user = request.args.get("username", "")
    password = request.args.get("password", "")
    if check(user, password) == 1:
        con = get_db()
        cur = con.cursor()
        cur.execute("SELECT name, score FROM users ORDER BY score DESC")
        results = cur.fetchall()
        cur.close()
        my_score = 0
        my_pos = 0
        my_next = 0
        for i in range(0, len(results)):
            if results[i][0] == user:
                my_score = results[i][1]
                my_pos = i+1
                if i != 0:
                    my_next = results[i-1][1]
        res = {"score": my_score, "position": my_pos, "next": my_next}
        return jsonify(res)
    else:
        return "0"


@app.route('/save')
def save():
    user = request.args.get("username", "")
    password = request.args.get("password", "")
    score = request.args.get("score", "")
    if score == "" or not score.isdecimal():
        return "0"
    if check(user, password) == 1:
        con = get_db()
        cur = con.cursor()
        cur.execute("UPDATE users SET score=? WHERE name=?", [score, user])
        con.commit()

        return "1"
    else:
        return "0"


if __name__ == '__main__':
    con = sqlite3.connect(DATABASE)
    cur = con.cursor()
    cur.execute("CREATE TABLE IF NOT EXISTS users(id integer PRIMARY KEY, name text, password text, score integer)")
    con.commit()
    app.run(host='0.0.0.0', port=2137)
