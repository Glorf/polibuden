//
// Created by mbien on 03.01.18.
//

#ifndef ZUS_PRODUKT_H
#define ZUS_PRODUKT_H

#include <boost/serialization/base_object.hpp>
#include "Aktywa.h"

class Produkt: public Aktywa {
    friend class boost::serialization::access;
    template<class Archive>
    void serialize(Archive & ar, const unsigned int version)
    {
        ar & boost::serialization::base_object<Aktywa>(*this);
        ar & czasTrwania;
        ar & ryzyko;
    }
    int czasTrwania;
    double ryzyko;
public:
    void init();
    double getWartoscZaXLat(int nLat);
    void wypiszSzczegoly();
};


#endif //ZUS_PRODUKT_H
