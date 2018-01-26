//
// Created by mbien on 29.12.17.
//

#ifndef PROJEKT_SPECYFIKACJA_H
#define PROJEKT_SPECYFIKACJA_H

enum TypWiadomosci {
    FREE,
    LOGIN,
    LOGOUT,
    USERID_NAME,
    NAME_USERID,
    SHOW_LOGGED,
    SHOW_GROUPUSERS,
    GROUPID_NAME,
    SIGNIN_GROUP,
    SIGNOUT_GROUP,
    SHOW_GROUPS,
    SEND_GROUP,
    SEND_PRIV,
    RCV_GROUP,
    RCV_PRIV,
    STATUS_OK,
    STATUS_ERROR
};

struct Message {
    long mtype;
    enum TypWiadomosci code;
    char payload[116];
};
typedef struct Message Message;

struct LoginPayload {
    char login[56];
    char password[56];
};
typedef struct LoginPayload LoginPayload;


struct ListPayload {
    int list[29];
};

struct SendTextPayload {
    int address;
    char text[112];
};

struct SendGroupPayload {
    int user;
    int group;
    char text[108];
};

#endif //PROJEKT_SPECYFIKACJA_H
