//
// Created by mbien on 03.01.18.
//

#include <iostream>
#include "IKE.h"

void IKE::init() {
    Aktywa::init();
    std::cout<<"Podaj wiek: ";
    std::cin>>this->wiek;
}

double IKE::getWartoscZaXLat(int nLat) {
    double wartosc2 = getWartosc();
    for(int i=0; i<nLat; i++) {
        wartosc2 += wartosc2*procentWzrostuRocznie;
    }

    double bonus = wartosc2*nLat/wiek;

    return wartosc2+bonus;
}

void IKE::wypiszSzczegoly() {
    Aktywa::wypiszSzczegoly();
    std::cout<<"Typ: Indywidualne Konto Emerytalne"<<std::endl;
    std::cout<<"Wiek właściciela: "<<wiek<<std::endl;
}