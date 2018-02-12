//
// Created by mbien on 03.01.18.
//

#include <iostream>
#include "IKZE.h"

void IKZE::init() {
    IKE::init();
    std::cout<<"Podaj procent zwracanego podatku VAT: ";
    std::cin>>procZwrotuVAT;
}

double IKZE::getWartoscZaXLat(int nLat) {
    double wartosc2 = getWartosc();
    for(int i=0; i<nLat; i++) {
        wartosc2 += wartosc2*procentWzrostuRocznie;
    }

    wartosc2 += wartosc2*nLat/wiek;

    return wartosc2+(procZwrotuVAT*wartosc2);
}

void IKZE::wypiszSzczegoly() {
    IKE::wypiszSzczegoly();
    std::cout<<"Podtyp: Konto Zabezpieczenia Emerytalnego"<<std::endl;
    std::cout<<"Procent zwrotu VAT: "<<procZwrotuVAT<<std::endl;
}