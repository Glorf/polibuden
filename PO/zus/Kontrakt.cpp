//
// Created by mbien on 03.01.18.
//

#include <iostream>
#include "Kontrakt.h"

void Kontrakt::init() {
    Aktywa::init();
    std::cout<<"Podaj czas trwania: ";
    std::cin>>czasTrwania;
}

double Kontrakt::getWartoscZaXLat(int nLat) {
    double wartosc2 = getWartosc();
    for(int i=0; i<std::min(nLat, czasTrwania); i++) {
        wartosc2 += wartosc2*procentWzrostuRocznie;
    }

    return wartosc2;
}

void Kontrakt::wypiszSzczegoly() {
    Aktywa::wypiszSzczegoly();
    std::cout<<"Typ: Kontrakt"<<std::endl;
    std::cout<<"Czas trwania: "<<czasTrwania<<std::endl;
}