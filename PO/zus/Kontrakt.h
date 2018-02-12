//
// Created by mbien on 03.01.18.
//

#ifndef ZUS_KONTRAKT_H
#define ZUS_KONTRAKT_H

#include <string>
#include <boost/serialization/base_object.hpp>
#include "Aktywa.h"

class Kontrakt: public Aktywa {
    friend class boost::serialization::access;

    template<class Archive>
    void serialize(Archive & ar, const unsigned int version)
    {
        ar & boost::serialization::base_object<Aktywa>(*this);
        ar & czasTrwania;
    }

    int czasTrwania;

public:
    void init();
    double getWartoscZaXLat(int nLat);
    void wypiszSzczegoly();
};


#endif //ZUS_KONTRAKT_H
