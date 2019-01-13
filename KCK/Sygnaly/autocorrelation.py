import sys
import numpy as np
from scipy.io import wavfile
import scipy.signal as sig
import warnings


#TWEAKABLE ARGS
threshold = 165 #female frequency threshold

if not sys.argv[1]:
    print("Podaj plik do analizy")
    exit(0)

try:
    warnings.filterwarnings('ignore')
    wav_fs, data = wavfile.read(sys.argv[1]) #read WAV file
except ValueError:
    print("K") #File is broken, return anything
    exit(0)



#change mono file to stereo
data = data.astype(float)
if len(data.shape) > 1:
    data = data.sum(axis=1) / 2


data = data - np.mean(data)
data_autokor = sig.correlate(data, data)
data_autokor = data_autokor[len(data_autokor)//2:]
data_autokor = data_autokor / data_autokor[0]

autokor2 = np.maximum(data_autokor, 0)
bufor = np.zeros(len(autokor2))

x = len(bufor)
n = len(autokor2) // 2
bufor[:x-1:2] = autokor2[:n]
bufor[1::2] = (autokor2[:n] + autokor2[1:n+1]) / 2
autokor2 = np.maximum(autokor2 - bufor, 0)
m = np.argmax(autokor2)

freq = wav_fs / m

if freq>threshold:
    print("K")
else:
    print("M")