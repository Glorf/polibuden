//
// Created by mbien on 07.03.18.
//

#ifndef EAN_WSZYSTKO_H
#define EAN_WSZYSTKO_H

#include "Interval.h"

typedef interval_arithmetic::Interval<long double> ild;

typedef long double ld;

struct result {
    ld val;
    ld w;
    int it;
    int st;
};
typedef struct result res;

struct iresult {
    ild val;
    ild w;
    int it;
    int st;
};
typedef struct iresult ires;

class Obliczacz {
    int n;
    string *a;
    string lx;
    string rx;
    int mit;
    int eps;
public:
    Obliczacz();
    ~Obliczacz();
    void setStopien(int n);
    int getStopien();
    void setWspolczynnikAt(int i, string val);
    string getWspolczynnikAt(int i);
    void setLX(string x);
    string getLX();
    void setRX(string x);
    string getRX();
    void setMaxIterations(int mit);
    int getMaxIterations();
    void setEpsilon(int eps);
    int getEpsilon();
    res calculateNormal();
    ires calculateInterval();
};

res newtonroots (int n, ld *a, ld x, int mit, ld eps);
ires inewtonroots (int n, ild *a, ild x, int mit, ld eps);



#endif //EAN_WSZYSTKO_H
