import cv2
import numpy as np

FLANN_INDEX_KDTREE = 0
index_params = dict(algorithm = FLANN_INDEX_KDTREE, trees = 5)
search_params = dict(checks = 50)

minHessian = 400
sift = cv2.xfeatures2d.SIFT_create()


cap = cv2.VideoCapture(0) #Webcam Capture
while (True):

    maxx = []
    maxi = 0

    ret, frame = cap.read()
    img1 = cv2.cvtColor(frame, cv2.COLOR_BGR2GRAY)

    for i in range(4):
        template = cv2.imread('kostka' + str(i) + '.jpg', cv2.IMREAD_GRAYSCALE)

        #img2 = cv2.cvtColor(template, cv2.COLOR_BGR2GRAY)
        kp1, des1 = sift.detectAndCompute(img1, None)
        kp2, des2 = sift.detectAndCompute(template, None)

        # BFMatcher with default params
        matcher = cv2.FlannBasedMatcher(index_params, search_params)
        matches = matcher.knnMatch(des1, des2, k=2)

        good = []
        for m, n in matches:
            if m.distance < 0.8 * n.distance:
                good.append([m])

        print(len(good))

        if (len(good) > maxi):
            maxi = len(good)
            maxx = good
            img_wynik = template
            kp_w = kp2

    print(maxi)

    # cv2.drawMatchesKnn expects list of lists as matches.
    img_matches = np.empty((max(img1.shape[0], img_wynik.shape[0]), img1.shape[1] + img_wynik.shape[1], 3), dtype=np.uint8)
    cv2.drawMatchesKnn(img_wynik, kp_w, img1, kp1, maxx, img_matches, flags=cv2.DrawMatchesFlags_NOT_DRAW_SINGLE_POINTS)

    cv2.imshow('Story Cubes', img_matches)
    if cv2.waitKey(1) == 27:
        break

cap.release()
cv2.destroyAllWindows()