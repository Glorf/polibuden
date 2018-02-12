//
// Created by mbien on 03.01.18.
//

#ifndef ZUS_FUNDUSZZD_H
#define ZUS_FUNDUSZZD_H

#include "Fundusz.h"

class FunduszZD: public Fundusz {
    friend class boost::serialization::access;
    template<class Archive>
    void serialize(Archive & ar, const unsigned int version)
    {
        ar & boost::serialization::base_object<Fundusz>(*this);
        ar & dywidendaRoczna;
    }

    double dywidendaRoczna;
public:
    void init();
    double getWartoscZaXLat(int nLat);
    void wypiszSzczegoly();
};


#endif //ZUS_FUNDUSZZD_H
