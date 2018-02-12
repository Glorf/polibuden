#include <fstream>
#include <boost/archive/text_oarchive.hpp>
#include <boost/archive/text_iarchive.hpp>
#include <boost/serialization/export.hpp>
#include <iostream>
#include "FunduszBD.h"
#include "FunduszZD.h"
#include "IKE.h"
#include "IKZE.h"
#include "Kontrakt.h"
#include "Lokata.h"
#include "Nieruchomosc.h"
#include "Produkt.h"

#include "PortfelInwestycyjny.h"

BOOST_CLASS_EXPORT(FunduszBD);
BOOST_CLASS_EXPORT(FunduszZD);
BOOST_CLASS_EXPORT(IKE);
BOOST_CLASS_EXPORT(IKZE);
BOOST_CLASS_EXPORT(Kontrakt);
BOOST_CLASS_EXPORT(Lokata);
BOOST_CLASS_EXPORT(Nieruchomosc);
BOOST_CLASS_EXPORT(Produkt);

void PortfelInwestycyjny::init() {
    std::cout<<"Podaj imię: ";
    std::cin>>imie;
    std::cout<<"Podaj nazwisko: ";
    std::cin>>nazwisko;
}

double PortfelInwestycyjny::lacznyStan() {
    double suma = 0;
    for(int i=0; i<pobierz().size(); i++) {
        suma += pobierz().at(i)->getWartosc();
    }

    return suma;
}

double PortfelInwestycyjny::zyskZaXLat(int nLat) {
    return lacznyStanZaXLat(nLat) - lacznyStan();
}

double PortfelInwestycyjny::lacznyStanZaXLat(int nLat) {
    double suma = 0;
    for(int i=0; i<pobierz().size(); i++) {
        suma += pobierz().at(i)->getWartoscZaXLat(nLat);
    }
}

PortfelInwestycyjny *PortfelInwestycyjny::wczytaj(std::string filename) {
    std::ifstream plik(filename);
    PortfelInwestycyjny *portfelInwestycyjny = new PortfelInwestycyjny();

    if(!plik.good()) {
        portfelInwestycyjny->init();
        portfelInwestycyjny->zapisz("zapis.sav");
        return portfelInwestycyjny;
    }

    boost::archive::text_iarchive ia(plik);
    ia >> portfelInwestycyjny;

    plik.close();

    return portfelInwestycyjny;
}

void PortfelInwestycyjny::zapisz(std::string filename) {
    std::ofstream plik(filename);

    boost::archive::text_oarchive oa(plik);
    oa << this;

    plik.close();
}