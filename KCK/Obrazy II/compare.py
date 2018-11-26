import numpy as np
import cv2
from matplotlib import pyplot as plt

FLANN_INDEX_KDTREE = 0
index_params = dict(algorithm=FLANN_INDEX_KDTREE, trees=5)
search_params = dict(checks=50)
maxx = []
maxi = 0
img1 = cv2.imread('image4.jpg',0)          # queryImage

# Initiate SIFT detector

minHessian = 400
orb = cv2.xfeatures2d.SURF_create(hessianThreshold=minHessian)



for imgName in ["kostka1.jpg", "kostka3.jpg", "kostka2.jpg"]:

    img2 = cv2.imread(imgName, 0)  # trainImage

    # find the keypoints and descriptors with SIFT
    kp1, des1 = orb.detectAndCompute(img1, None)
    kp2, des2 = orb.detectAndCompute(img2, None)

    # BFMatcher with default params
    matcher = cv2.FlannBasedMatcher(index_params, search_params)
    matches = matcher.knnMatch(des1, des2, k=2)

    
    good = []
    for m, n in matches:
        if m.distance < 0.8 * n.distance:
            good.append([m])

    print(len(good))

    if(len(good)>maxi):
        maxi = len(good)
        maxx = good
        img_wynik = img2

print(maxi)

    # cv2.drawMatchesKnn expects list of lists as matches.
img3 = cv2.drawMatchesKnn(img1, kp1, img_wynik, kp2, maxx, None, flags=2)

plt.imshow(img3), plt.show()






