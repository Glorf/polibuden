//
// Created by mbien on 29.12.17.
//

#ifndef ZUS_FUNDUSZ_H
#define ZUS_FUNDUSZ_H

#include <boost/serialization/base_object.hpp>
#include "Aktywa.h"

class Fundusz: public Aktywa {
    friend class boost::serialization::access;
    template<class Archive>
    void serialize(Archive & ar, const unsigned int version)
    {
        ar & boost::serialization::base_object<Aktywa>(*this);
        ar & ilosc;
    }
protected:
    double ilosc;

public:
    virtual void init() = 0;
    virtual double getWartoscZaXLat(int nLat) = 0;
    virtual void wypiszSzczegoly() = 0;
};

#endif //ZUS_FUNDUSZ_H
