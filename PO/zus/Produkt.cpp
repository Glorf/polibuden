//
// Created by mbien on 03.01.18.
//

#include <iostream>
#include "Produkt.h"

void Produkt::init() {
    Aktywa::init();
    std::cout<<"Podaj czas trwania: ";
    std::cin>>czasTrwania;
    std::cout<<"Podaj szacowane ryzyko procentowe: ";
    std::cin>>ryzyko;
}

double Produkt::getWartoscZaXLat(int nLat) {
    double wartosc2 = getWartosc();
    for(int i=0; i<std::min(nLat, czasTrwania); i++) {
        wartosc2 += wartosc2*procentWzrostuRocznie;
    }

    return wartosc2 - (wartosc2*ryzyko);
}

void Produkt::wypiszSzczegoly() {
    Aktywa::wypiszSzczegoly();
    std::cout<<"Typ: Produkt strukturyzowany"<<std::endl;
    std::cout<<"Czas trwania: "<<czasTrwania<<std::endl;
    std::cout<<"Ryzyko procentowe: "<<ryzyko<<std::endl;
}