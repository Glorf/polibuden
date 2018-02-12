//
// Created by mbien on 29.12.17.
//

#ifndef ZUS_PORTFELINWESTYCYJNY_H
#define ZUS_PORTFELINWESTYCYJNY_H

#include "Portfel.h"
#include "Aktywa.h"

#include <boost/serialization/string.hpp>


class PortfelInwestycyjny: public Portfel<Aktywa> {
    friend class boost::serialization::access;

    template<class Archive>
    void serialize(Archive & ar, const unsigned int version)
    {
        ar & boost::serialization::base_object<Portfel<Aktywa>>(*this);
        ar & imie;
        ar & nazwisko;
    }

    std::string imie;
    std::string nazwisko;

public:
    void init();
    double lacznyStan();
    double zyskZaXLat(int nLat);
    double lacznyStanZaXLat(int nLat);
    static PortfelInwestycyjny* wczytaj(std::string filename);
    void zapisz(std::string filename);
};

#endif //ZUS_PORTFELINWESTYCYJNY_H
