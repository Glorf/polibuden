//
// Created by mbien on 03.01.18.
//

#ifndef ZUS_IKZE_H
#define ZUS_IKZE_H

#include "IKE.h"

class IKZE: public IKE {
    friend class boost::serialization::access;
    template<class Archive>
    void serialize(Archive & ar, const unsigned int version)
    {
        ar & boost::serialization::base_object<IKE>(*this);
        ar & procZwrotuVAT;
    }
    double procZwrotuVAT;
public:
    void init();
    double getWartoscZaXLat(int nLat);
    void wypiszSzczegoly();
};


#endif //ZUS_IKZE_H
