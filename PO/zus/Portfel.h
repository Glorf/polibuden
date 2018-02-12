//
// Created by mbien on 29.12.17.
//

#ifndef ZUS_PORTFEL_H
#define ZUS_PORTFEL_H

#include <vector>
#include <string>

#include <boost/serialization/vector.hpp>

template <class T>
class Portfel {
    friend class boost::serialization::access;

    template<class Archive>
    void serialize(Archive & ar, const unsigned int version)
    {
        ar & elementy;
    }

    std::vector<T*> elementy;

public:
    void dodaj(T *element) {
        elementy.push_back(element);
    }
    std::vector<T*>& pobierz() {
        return elementy;
    }

    void usun(T *element) {
        int i;
        for(i=0; i<elementy.size(); i++) {
            if(elementy.at(i) == element) {
                elementy.erase(elementy.begin()+i);
                delete element;
                return;
            }
        }
    }

    virtual void zapisz(std::string filename) = 0;
};

#endif //ZUS_PORTFEL_H
