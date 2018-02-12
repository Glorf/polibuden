//
// Created by mbien on 03.01.18.
//

#include <iostream>
#include "Lokata.h"

void Lokata::init() {
    Aktywa::init();
    std::cout<<"Podaj czas trwania: ";
    std::cin>>czasTrwania;
    std::cout<<"Podaj nazwę instytucji udzielającej: ";
    std::cin>>instytucja;
    std::cout<<"Czy jest odnawialna? [tak/nie]: ";
    std::string respons;
    std::cin>>respons;
    if(respons == "tak") odnawialna = true;
    else odnawialna = false;
}

double Lokata::getWartoscZaXLat(int nLat) {
    double wartosc2 = getWartosc();
    for(int i = 0; i<(odnawialna?nLat:std::min(czasTrwania,nLat)); i++) {
        wartosc2 += wartosc2*procentWzrostuRocznie;
    }

    return wartosc2;
}

void Lokata::wypiszSzczegoly() {
    Aktywa::wypiszSzczegoly();
    std::cout<<"Typ: Lokata"<<std::endl;
    std::cout<<"Czas trwania: "<<czasTrwania<<std::endl;
    std::cout<<"Kto udzielił: "<<instytucja<<std::endl;
    std::cout<<"Odnawialność: "<<(odnawialna?"Tak":"Nie")<<std::endl;
}