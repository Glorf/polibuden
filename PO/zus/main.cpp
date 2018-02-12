#include <iostream>
#include <iomanip>
#include "PortfelInwestycyjny.h"
#include "FunduszBD.h"
#include "FunduszZD.h"
#include "IKE.h"
#include "IKZE.h"
#include "Kontrakt.h"
#include "Lokata.h"
#include "Nieruchomosc.h"
#include "Produkt.h"
#include <boost/serialization/export.hpp>


PortfelInwestycyjny *portfel;

int aktywaChooser() {
    std::cout<<"Wybierz aktywo: "<<std::endl;
    for(int i=0; i<portfel->pobierz().size(); i++) {
        std::cout<<i+1<<") "<<portfel->pobierz().at(i)->getNazwa()<<std::endl;
    }
    std::cout<<"Wybrane: ";
    int chosen;
    std::cin>>chosen;
    return chosen-1;
}

Aktywa *typAktywaChooser() {
    std::cout<<"Wybierz typ aktywa: "<<std::endl;
    std::cout<<"1) Fundusz bez dywidendy" <<std::endl;
    std::cout<<"2) Fundusz z dywidendą" <<std::endl;
    std::cout<<"3) IKE" <<std::endl;
    std::cout<<"4) IKZE" <<std::endl;
    std::cout<<"5) Kontrakt"<<std::endl;
    std::cout<<"6) Lokata"<<std::endl;
    std::cout<<"7) Nieruchomość"<<std::endl;
    std::cout<<"8) Produkt strukturyzowany"<<std::endl;
    int chosen;
    std::cin>>chosen;
    switch(chosen) {
        case 1: return new FunduszBD();
        case 2: return new FunduszZD();
        case 3: return new IKE();
        case 4: return new IKZE();
        case 5: return new Kontrakt();
        case 6: return new Lokata();
        case 7: return new Nieruchomosc();
        case 8: return new Produkt();
        default: return nullptr;
    }
}

void menuOperacje() {
    std::cout<<"[dodaj/edytuj/usun/anuluj]: ";
    std::string chosen;
    std::cin>>chosen;

    if(chosen == "anuluj") return;
    else if(chosen == "edytuj" || chosen == "usun") {
        int index = aktywaChooser();
        if(chosen == "usun") portfel->usun(portfel->pobierz().at(index));
        else portfel->pobierz().at(index)->init();
    }
    else if(chosen == "dodaj") {
        Aktywa *a = typAktywaChooser();
        a->init();
        portfel->dodaj(a);
    }

    portfel->zapisz("zapis.sav");
}

void menuStan() {
    std::cout<<"[wartosc/symulacja/aktywo]: ";
    std::string chosen;
    std::cin>>chosen;

    if(chosen == "wartosc") {
        std::cout<< "Sumaryczna wartosć Twoich aktywów to: "<<portfel->lacznyStan()<<std::endl;
    }
    else if(chosen == "symulacja") {
        std::cout<<"Podaj okno czasowe: ";
        int lat;
        std::cin>>lat;
        std::cout<<"Za "<<lat<<" lat Twoje aktywa będą warte "<<std::fixed<<std::setprecision(2)<<portfel->lacznyStanZaXLat(lat)<<std::endl;
    }
    else {
        int aktywo = aktywaChooser();
        portfel->pobierz().at(aktywo)->wypiszSzczegoly();
    }
}

int main() {
    portfel = PortfelInwestycyjny::wczytaj("zapis.sav");

    bool run = true;
    while(run) {
        std::cout << "Witaj w systemie ZUS!" << std::endl;
        std::cout << "[stan/operacje/koniec]: ";
        std::string s;
        std::cin >> s;
        if (s == "operacje") {
            menuOperacje();
        }
        else if(s == "stan") {
            menuStan();
        }
        else if(s == "koniec") {
            run = false;
            continue;
        }
        else {
            std::cout<<"Błędna instrukcja!"<<std::endl;
        }
    }
    return 0;
}