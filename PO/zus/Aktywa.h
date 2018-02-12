//
// Created by mbien on 29.12.17.
//

#ifndef ZUS_AKTYWA_H
#define ZUS_AKTYWA_H

#include <string>
#include <vector>
#include <boost/serialization/access.hpp>
#include <boost/serialization/string.hpp>

class Aktywa {
    friend class boost::serialization::access;
    template<class Archive>
    void serialize(Archive & ar, const unsigned int version)
    {
        ar & nazwa;
        ar & wartosc;
        ar & procentWzrostuRocznie;
    }
protected:
    std::string nazwa;
    double wartosc;
    double procentWzrostuRocznie;
public:
    virtual void init();
    std::string getNazwa();
    double getWartosc();
    Aktywa &operator+=(double d);
    Aktywa &operator-=(double d);
    virtual double getWartoscZaXLat(int nLat);
    virtual void wypiszSzczegoly();
};

#endif //ZUS_AKTYWA_H
