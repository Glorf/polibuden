//
// Created by mbien on 03.01.18.
//

#include <iostream>
#include "Nieruchomosc.h"

void Nieruchomosc::init() {
    Aktywa::init();
    std::cout<<"Podaj metraż: ";
    std::cin>>metraz;
    std::cout<<"Podaj adres: ";
    std::cin>>adres;
    std::cout<<"Podaj cenę najmu: ";
    std::cin>>cenaNajmu;
}

double Nieruchomosc::getWartoscZaXLat(int nLat) {
    double wartosc2 = getWartosc();
    for(int i=0; i<nLat; i++) {
        wartosc2 += wartosc2*procentWzrostuRocznie;
    }

    return wartosc2 + (cenaNajmu*12*nLat);
}

void Nieruchomosc::wypiszSzczegoly() {
    Aktywa::wypiszSzczegoly();
    std::cout<<"Typ: Nieruchomość"<<std::endl;
    std::cout<<"Metraż: "<<metraz<<std::endl;
    std::cout<<"Adres: "<<adres<<std::endl;
    std::cout<<"Cena najmu: "<<cenaNajmu<<std::endl;
}