#include "Aktywa.h"
#include <iostream>

void Aktywa::init() {
    std::cout<<"Podaj nazwę: ";
    std::cin>>this->nazwa;
    std::cout<<"Podaj wartość: ";
    std::cin>>this->wartosc;
    std::cout<<"Podaj roczny procent wzrostu: ";
    std::cin>>this->procentWzrostuRocznie;
}

std::string Aktywa::getNazwa() {
    return this->nazwa;
}

double Aktywa::getWartosc() {
    return this->wartosc;
}

Aktywa& Aktywa::operator+=(double d) {
    this->wartosc+=d;
}

Aktywa& Aktywa::operator-=(double d) {
    this->wartosc-=d;
}

double Aktywa::getWartoscZaXLat(int nLat) {
    double wartosc2 = this->wartosc;
    for(int i=0; i<nLat; i++) {
        wartosc2 = wartosc2+(wartosc2*this->procentWzrostuRocznie);
    }

    return wartosc2;
}

void Aktywa::wypiszSzczegoly() {
    std::cout<<"========== "<<getNazwa()<<" =========="<<std::endl;
    std::cout<<"Całkowita wartość: "<<getWartosc()<<std::endl;
    std::cout<<"Roczny procent wzrostu: "<<procentWzrostuRocznie<<std::endl;
}