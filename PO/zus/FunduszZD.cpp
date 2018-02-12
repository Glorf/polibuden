//
// Created by mbien on 03.01.18.
//

#include "FunduszZD.h"
#include <iostream>

void FunduszZD::init() {
    Aktywa::init();
    std::cout<<"Podaj ilość jednostek: ";
    std::cin>>ilosc;
    std::cout<<"Podaj wartość rocznej dywidendy: ";
    std::cin>>dywidendaRoczna;
}

double FunduszZD::getWartoscZaXLat(int nLat) {
    double wartosc2 = getWartosc();
    for(int i=0; i<nLat; i++) {
        wartosc2 += wartosc2*procentWzrostuRocznie;
    }

    return wartosc2*ilosc + (dywidendaRoczna*nLat);
}

void FunduszZD::wypiszSzczegoly() {
    Aktywa::wypiszSzczegoly();
    std::cout<<"Typ: Fundusz z dywidendą"<<std::endl;
    std::cout<<"Ilość jednostek: "<<ilosc<<std::endl;
    std::cout<<"Roczna dywidenda: "<<dywidendaRoczna<<std::endl;
}