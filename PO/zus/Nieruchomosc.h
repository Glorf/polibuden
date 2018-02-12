//
// Created by mbien on 03.01.18.
//

#ifndef ZUS_NIERUCHOMOSC_H
#define ZUS_NIERUCHOMOSC_H


#include <boost/serialization/base_object.hpp>
#include <boost/serialization/string.hpp>
#include "Aktywa.h"

class Nieruchomosc: public Aktywa {
    friend class boost::serialization::access;
    template<class Archive>
    void serialize(Archive & ar, const unsigned int version)
    {
        ar & boost::serialization::base_object<Aktywa>(*this);
        ar & metraz;
        ar & adres;
        ar & cenaNajmu;
    }
    int metraz;
    std::string adres;
    double cenaNajmu;
public:
    void init();
    double getWartoscZaXLat(int nLat);
    void wypiszSzczegoly();
};


#endif //ZUS_NIERUCHOMOSC_H
