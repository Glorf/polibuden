//
// Created by mbien on 03.01.18.
//

#ifndef ZUS_IKE_H
#define ZUS_IKE_H


#include "Aktywa.h"
#include <boost/serialization/base_object.hpp>

class IKE: public Aktywa {
    friend class boost::serialization::access;
    template<class Archive>
    void serialize(Archive & ar, const unsigned int version)
    {
        ar & boost::serialization::base_object<Aktywa>(*this);
        ar & wiek;
    }
protected:
    int wiek;
public:
    virtual void init();
    virtual double getWartoscZaXLat(int nLat);
    virtual void wypiszSzczegoly();
};


#endif //ZUS_IKE_H
