//
// Created by mbien on 03.01.18.
//

#ifndef ZUS_LOKATA_H
#define ZUS_LOKATA_H

#include <string>
#include <boost/serialization/string.hpp>
#include <boost/serialization/base_object.hpp>
#include "Aktywa.h"

class Lokata: public Aktywa {
    friend class boost::serialization::access;
    template<class Archive>
    void serialize(Archive & ar, const unsigned int version)
    {
        ar & boost::serialization::base_object<Aktywa>(*this);
        ar & czasTrwania;
        ar & instytucja;
        ar & odnawialna;
    }

    int czasTrwania;
    std::string instytucja;
    bool odnawialna;

public:
    void init();
    double getWartoscZaXLat(int nLat);
    void wypiszSzczegoly();
};


#endif //ZUS_LOKATA_H
