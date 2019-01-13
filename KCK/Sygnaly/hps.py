import sys
import warnings
import numpy as np
from scipy.io import wavfile
from scipy.signal import decimate

#TWEAKABLE ARGS
alpha = 6 #downsampling upper limit
beta = 50 #kaiser window size
threshold = 165 #female frequency threshold

#ALGORITHM
if not sys.argv[1]:
    print("Podaj plik do analizy")
    exit(0)
try:
    warnings.filterwarnings('ignore')
    sample_rate, sig_in = wavfile.read(sys.argv[1]) #read WAV file
except ValueError:
    print("K") #File is broken, return anything
    exit(0)
#change mono file to stereo
sig = sig_in.astype(float)
if len(sig.shape) > 1:
    sig = sig.sum(axis=1)/2

n = sig.shape[0]
sig -= np.mean(sig) #mean normalize
sig *= np.kaiser(n, beta) #kaiser window
X = np.log(np.abs(np.fft.rfft(sig))) #fourier transform
X -= np.mean(X) #mean normalize again

#Perform hps spectrum downsampling using decimate function
sampler = np.copy(X)
for h in range(2, alpha):
    dec = decimate(X, h, ftype = 'fir')
    sampler[:len(dec)] += dec

# Find the peak index
peak = np.argmax(sampler[:len(dec)])

#get frequency
freq = sample_rate * peak / n

if freq>threshold:
    print("K")
else:
    print("M")
