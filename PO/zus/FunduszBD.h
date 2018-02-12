//
// Created by mbien on 03.01.18.
//

#ifndef ZUS_FUNDUSZBD_H
#define ZUS_FUNDUSZBD_H

#include <string>
#include "Aktywa.h"
#include "Fundusz.h"

class FunduszBD: public Fundusz {
    friend class boost::serialization::access;
    template<class Archive>
    void serialize(Archive & ar, const unsigned int version)
    {
        ar & boost::serialization::base_object<Fundusz>(*this);
    }
public:
    void init();
    double getWartoscZaXLat(int nLat);
    void wypiszSzczegoly();
};


#endif //ZUS_FUNDUSZBD_H
