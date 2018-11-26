import cv2
import numpy as np

FLANN_INDEX_KDTREE = 0
index_params = dict(algorithm = FLANN_INDEX_KDTREE, trees = 5)
search_params = dict(checks = 50)

minHessian = 400
orb = cv2.xfeatures2d.SIFT_create()#hessianThreshold=minHessian)

#orb = cv2.ORB_create(nfeatures=1000)

def show_webcam():
    cam = cv2.VideoCapture(0)
    
    t_img, t_kp, t_des = load_patterns()
    
    while True:
        ret_val, img = cam.read()
        img = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
       
#         print(ret_val)
#         print(img)

        img = preprocess(img)
        
        kp, des = orb.detectAndCompute(img,None)
        
        if t_des is None or des is None:
            continue
        
        matcher = cv2.FlannBasedMatcher(index_params, search_params)
        knn_matches = matcher.knnMatch(t_des, des, k=2)
        
        #-- Filter matches using the Lowe's ratio test
        ratio_thresh = 0.7
        good_matches = []
        for mn in knn_matches:
            if len(mn) != 2:
                continue
            (m,n) = mn
            if m.distance < ratio_thresh * n.distance:
                good_matches.append(m)
        if(len(good_matches) > 7):
            print("Wykryto jabłko")
        img_matches = np.empty((max(img.shape[0], t_img.shape[0]), img.shape[1]+t_img.shape[1], 3), dtype=np.uint8)
        cv2.drawMatches(t_img, t_kp, img, kp, good_matches, img_matches, flags=cv2.DrawMatchesFlags_NOT_DRAW_SINGLE_POINTS)
        
        # zapisuje klatki
#         cv2.imwrite("img{}.png".format(count), img2)
#         count += 1
        
        cv2.imshow('Story Cubes', img_matches)
        if cv2.waitKey(1) == 27:
            break
    cv2.destroyAllWindows()

def load_patterns():
    img = cv2.imread("kostka1.jpg", cv2.IMREAD_GRAYSCALE)
    img = preprocess(img)
    kp, des = orb.detectAndCompute(img,None)
    
    return img, kp, des

def preprocess(img):
    #img=np.uint8(np.log(img))
    #img = cv2.normalize(img, None, 0, 255, cv2.NORM_MINMAX, dtype = cv2.CV_8U)
    #blur = cv2.GaussianBlur(img,(3,3),0)
    #ret,th = cv2.threshold(blur, 0, 255, cv2.THRESH_BINARY+cv2.THRESH_OTSU)
    th = cv2.adaptiveThreshold(img,255,cv2.ADAPTIVE_THRESH_MEAN_C,cv2.THRESH_BINARY,5,6)
    return th

def main():
    show_webcam()


if __name__ == '__main__':
    main()
