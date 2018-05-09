//
// Created by mbien on 07.03.18.
//

#include "liczenie.h"

using namespace std;

Obliczacz::Obliczacz() {
    this->a = new string[60];
    for(int i=0; i<60; i++) a[i] = "0";

    this->eps = 0;
    this->mit = 0;
    this->n = 0;
    this->lx = "0";
    this->rx = "";
}

Obliczacz::~Obliczacz() {
    delete(a);
}

void Obliczacz::setStopien(int n) {
    this->n = n;
}
int Obliczacz::getStopien() {
    return n;
}

void Obliczacz::setEpsilon(int eps) {
    this->eps = eps;
}
int Obliczacz::getEpsilon() {
    return eps;
}

void Obliczacz::setMaxIterations(int mit) {
    this->mit = mit;
}
int Obliczacz::getMaxIterations() {
    return mit;
}

void Obliczacz::setWspolczynnikAt(int i, string val) {
    this->a[i] = val;
}
string Obliczacz::getWspolczynnikAt(int i) {
    return this->a[i];
}

void Obliczacz::setLX(string x) {
    this->lx = x;
}
string Obliczacz::getLX() {
    return this->lx;
}

void Obliczacz::setRX(string x) {
    this->rx = x;
}

string Obliczacz::getRX() {
    return this->rx;
}

res Obliczacz::calculateNormal() {

    ld *la = new ld[n+1];
    for(int i=0; i<=n; i++) {
        la[i] = stold(a[i]);
    }

    ld ldlx = stold(lx);

    return newtonroots(n, la, ldlx, mit, pow(10,-eps));
}

ires Obliczacz::calculateInterval() {
    ild *ia = new ild[n+1];
    for(int i=0; i<=n; i++) {
        ia[i] = ild::IntRead(a[i]);
    }

    ild ix;

    if(rx != "") {
        ix.a = ix.LeftRead(lx);
        ix.b = ix.RightRead(rx);
    }
    else {
        ix = ild::IntRead(lx);
    }

    return inewtonroots(n, ia, ix, mit, pow(10,-eps));
}

/*
 * DOKUMENTACJA TODO dla arytmetyki przedziałowej:
 * 1. Zastosowanie
 * 2. Opis metody
 * 3. Wywołanie funkcji
 * 4. Dane
 * 5. Wynik
 * 6. Inne parametry wynikowe
 * 7. Typy parametrów
 * 8. Identyfikatory nielokalne (np. macierz, wektor...)
 * 9. Tekst funkcji
 * 10. Przykłady
 * /

/*
 * zwykły algorytm newtona
 * wejście
 * n - stopień wielomianu
 * a - wektor kolejnych współczynników wielomianu od najmniejszej potęgi
 * x - aproksymacja początkowa pierwiastka wielomianu
 * mit - maksymalna liczba iteracji algorytmu
 * eps - minimalne pożądane przybliżenie wartości (EPSILON)
 *
 * wyjście - struktura result
 * .val - wartość obliczona pierwiastka
 * .w - wartość wielomianu dla obliczonego pierwiastka (sprawdza stopień błędności rozwiązania)
 * .it - liczba iteracji algorytmu
 * .st - stan algorytmu:
 * 1 - podano błędne dane
 * 2 - wykryto dzielenie przez zero
 * 3 - nie udało się osiągnąć zadanej dokładności rozwiązania
 * 0 - wpw (sukces)
 */
res newtonroots (int n, ld *a, ld x, int mit, ld eps) {
    if(n<1 || mit<1) {
        res to_return;
        to_return.st = 1;
        return to_return;
    }

    ld w = a[n];
    for(int i=n-1; i>=0; i--)
        w = w * x + a[i];

    int st = 0;
    int it = 0;
    if(w != 0) {
        st = 3;
        do {
            it++;
            w = a[n];
            ld dw = w;

            for(int i = n-1; i>0; i--) {
                w = w * x + a[i];
                dw = dw * x + w;
            }
            w = w * x + a[0];

            if(dw == 0) {
                res to_return;
                to_return.st = 2;
                return to_return;
            }

            ld xh = x;
            ld u = abs(xh);
            x -= w/dw;
            ld v = abs(x);

            if(v<u) v = u;
            if(v == 0 || abs(x-xh)/v <= eps)
                st = 0;

        } while(it!=mit && st==3);
    }

    res to_return;
    to_return.it = it;
    to_return.val = x;
    to_return.st = st;
    w = a[n];
    for(int i = n-1; i>=0; i--)
        w = w * x + a[i];
    to_return.w = w;

    return to_return;
}

ild iabs(ild in) {
    ild ret;
    if(in.a<0 && in.b>0) ret.a = 0;
    else ret.a = min(abs(in.a), abs(in.b));
    ret.b = max(abs(in.a), abs(in.b));

    return ret;
}

ires inewtonroots (int n, ild *a, ild x, int mit, ld eps) {
    ires to_return;
    to_return.st = 0;
    to_return.it = 0;

    if(n<1 || mit<1) {
        to_return.st = 1;
        return to_return;
    }

    ild w = a[n];
    for(int i=n-1; i>=0; i--)
        w = w * x + a[i];

    if(abs(w.a) > 1e-16 || abs(w.b) > 1e-16) {
        to_return.st = 3;
        do {
            to_return.it++;
            w = a[n];
            ild dw = w;

            for(int i = n-1; i>0; i--) {
                w = w * x + a[i];
                dw = dw * x + w;
            }
            w = w * x + a[0];

            if(dw.a < 0 && dw.b >0) {
                to_return.st = 2;
                return to_return;
            }

            ild xh = x;
            ild u = iabs(xh);
            x = x-(w/dw);
            ild v = iabs(x);

            if(v.a<u.a && v.b<u.b) v = u;
            if((abs(v.a) < 1e-16 && abs(v.b) < 1e-16) || (abs(x.a-xh.a)/abs(v.a) <= eps && abs(x.b-xh.b)/abs(v.b) <= eps))
                to_return.st = 0;

        } while(to_return.it!=mit && to_return.st==3);
    }

    to_return.val = x;
    w = a[n];
    for(int i = n-1; i>=0; i--)
        w = w * x + a[i];
    to_return.w = w;

    return to_return;
}
