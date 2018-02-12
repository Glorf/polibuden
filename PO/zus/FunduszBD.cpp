//
// Created by mbien on 03.01.18.
//

#include "FunduszBD.h"
#include <iostream>

void FunduszBD::init() {
    Aktywa::init();
    std::cout<<"Podaj ilość jednostek: ";
    std::cin>>ilosc;
}

double FunduszBD::getWartoscZaXLat(int nLat) {
    double wartosc2 = getWartosc();
    for(int i=0; i<nLat; i++) {
        wartosc2 += wartosc2*procentWzrostuRocznie;
    }

    return wartosc2*ilosc;
}

void FunduszBD::wypiszSzczegoly() {
    Aktywa::wypiszSzczegoly();
    std::cout<<"Typ: Fundusz bez dywidendy"<<std::endl;
    std::cout<<"Ilość jednostek: "<<ilosc<<std::endl;
}