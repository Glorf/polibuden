import cv2
import numpy as np

minHessian = 400
detector = cv2.xfeatures2d.SURF_create(hessianThreshold=minHessian)
i = 1
def show_webcam():
    cam = cv2.VideoCapture(0)

    while True:
        ret_val, img = cam.read()
        img = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)

        img = preprocess(img)
        
        kp, des = detector.detectAndCompute(img,None)
        
        if des is None:
            continue
        
        img_matches = np.empty((img.shape[0], img.shape[1], 3), dtype=np.uint8)
        cv2.drawKeypoints(img, kp, img_matches)
        
        cv2.imshow('Story Cubes', img_matches)

        key = cv2.waitKey(1)
        if key == 32:
            r = cv2.selectROI("Image", img, fromCenter=False)
            (x,y,w,h) = r
            if(w==0 or h==0):
                continue
            img = img[int(r[1]):int(r[1]+r[3]), int(r[0]):int(r[0]+r[2])]
            kpt, dest = detector.detectAndCompute(img, None)
            img_matches = np.empty((img.shape[0], img.shape[1], 3), dtype=np.uint8)
            cv2.drawKeypoints(img, kpt, img_matches)
            cv2.imshow('Saved', img_matches)
            while True:
                ch = cv2.waitKey(1)
                if ch == 99:
                    break
                elif ch == 115:
                    if dest.shape[0] < 100:
                        print("Zidentyfikowano za mało punktów")
                        break
                    dest = dest[0:100, :]
                    np.savetxt("zegar-"+str(i), dest)
                    print(dest.shape)
                    break 
        elif key == 27:
            break
        
    cv2.destroyAllWindows()

def preprocess(img):
    #img=np.uint8(np.log(img))
    #img = cv2.normalize(img, None, 0, 255, cv2.NORM_MINMAX, dtype = cv2.CV_8U)
    #blur = cv2.GaussianBlur(img,(3,3),0)
    #ret,th = cv2.threshold(blur, 0, 255, cv2.THRESH_BINARY+cv2.THRESH_OTSU)
    th = cv2.adaptiveThreshold(img,255,cv2.ADAPTIVE_THRESH_MEAN_C,cv2.THRESH_BINARY,9,8)
    return th

def main():
    show_webcam()


if __name__ == '__main__':
    main()
