#include "inf132191_h.h"
#include <sys/msg.h>
#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <string.h>

#define QUEUE_RCV 2137
#define QUEUE_SND 2138

#define NUSERS 9
#define NGROUPS 3

int rcvid;
int sndid;

struct sUser {
    int id;
    char login[56];
    char password[56];
    long mtype;
};
typedef struct sUser User;

struct sGroup {
    char name[55];
    User *users[NUSERS];
};
typedef struct sGroup Group;

User users[NUSERS];
Group groups[NGROUPS];
bool freePorts[NUSERS];


void sendAck(long connectionId, enum TypWiadomosci type, void *payload) {
    Message ack;
    ack.code = type;
    ack.mtype = connectionId;
    if(payload!=NULL)
        memcpy(ack.payload, payload, 116);

    msgsnd(sndid, &ack, sizeof(ack)- sizeof(long), 0);
}

User *findUserByMType(long mtype) {
    for(int i=0; i < NUSERS; i++) {
        if(users[i].mtype == mtype|| users[i].mtype == mtype-10 || users[i].mtype == mtype-20) {
            return &users[i];
        }
    }

    return NULL;
}


int main() {
    for(int i=0; i<NGROUPS; i++) {
        strcpy(groups[i].name, "");
        for(int j=0; j<NUSERS; j++)
            groups[i].users[j] = NULL;
    }

    for(int i=0; i<NUSERS; i++) {
        freePorts[i] = true;
    }

    for(int i=0; i<NUSERS; i++) {
        users[i].mtype = 0;
        users[i].id = i;
    }

    FILE *fid = fopen("config", "r");

    int useriter = 0;
    int groupiter = 0;
    while (!feof(fid))
    {
        char first;
        char second[56];
        char third[56];

        fscanf(fid,"%c %s %s\n",&first, second, third);

        if(first == 'u') {
            strcpy(users[useriter].login, second);
            strcpy(users[useriter].password, third);
            useriter++;
        }
        else if(first == 'g') {
            strcpy(groups[groupiter].name, second);

            User *userptr;
            for(int i=0; i<NUSERS; i++) {
                if(strcmp(third, users[i].login) == 0) {
                    userptr = &users[i];
                    break;
                }
            }

            groups[groupiter].users[0] = userptr;
            groupiter++;
        }
    }
    fclose(fid);

    rcvid = msgget(QUEUE_RCV, IPC_CREAT|0644);
    sndid = msgget(QUEUE_SND, IPC_CREAT|0644);

    bool running = true;

    while(running) {
        Message msg;
        msgrcv(rcvid, &msg, 120, 0, 0);

        if(msg.code == FREE) {
            printf("FREE? %d\n",(int)msg.mtype);
            if(freePorts[msg.mtype-1]) {
                freePorts[msg.mtype-1] = false;
                sendAck(msg.mtype, STATUS_OK, NULL);
            }
            else
                sendAck(msg.mtype, STATUS_ERROR, NULL);
        }
        else if (msg.code == LOGIN) {
            printf("LOGOWANIE\n");
            char *ptr = msg.payload;
            LoginPayload *payload = (LoginPayload *) ptr;

            bool found = false;
            bool authorized = false;
            User *userptr;
            for (int i = 0; i < NUSERS; i++) {
                if (strcmp(users[i].login, payload->login) == 0) {
                    found = true;
                    if (strcmp(users[i].password, payload->password) == 0) {
                        authorized = true;
                        userptr = &users[i];
                    }
                }
            }
            if (!found) {
                sendAck(msg.mtype, STATUS_ERROR, "User not found");
            } else if (!authorized) {
                sendAck(msg.mtype, STATUS_ERROR, "Wrong password");
            } else {
                userptr->mtype = msg.mtype-10;
                sendAck(msg.mtype, STATUS_OK, "Logged in successfully");
            }
        }
        else if(msg.code == LOGOUT) {
            printf("LOGOUT\n");
            User *userptr = findUserByMType(msg.mtype);
            if (userptr == NULL)
                sendAck(msg.mtype, STATUS_ERROR, "You are not logged in!");
            else {
                sendAck(msg.mtype, STATUS_OK, "Logged out successfully");

                freePorts[userptr->mtype-1] = true;
                userptr->mtype = 0;
            }
        }
        else if(msg.code == SHOW_LOGGED) {
            printf("SHOW_LOGGED\n");
            struct ListPayload lista;
            int listit = 0;
            for(int i=0; i<NUSERS; i++) {
                if(users[i].mtype == 0)
                    continue;
                lista.list[listit++] = i;
            }
            lista.list[listit] = -1;

            struct ListPayload payload;
            memcpy(&payload, &lista, 116);
            sendAck(msg.mtype, STATUS_OK, &payload);
        }
        else if(msg.code == USERID_NAME) {
            printf("USERID_NAME\n");
            struct SendTextPayload payload;
            memcpy(&payload, msg.payload, 116);
            if(payload.address<0 || payload.address>NUSERS)
                sendAck(msg.mtype, STATUS_ERROR, "Unknown user id");
            else {
                User *userptr = &users[payload.address];
                sendAck(msg.mtype, STATUS_OK, userptr->login);
            }
        }
        else if(msg.code == NAME_USERID) {
            printf("NAME_USERID\n");
            int userId = -1;
            for(int i=0; i<NUSERS; i++) {
                if(strcmp(msg.payload, users[i].login) == 0) {
                    userId = i;
                    break;
                }
            }

            if(userId == -1)
                sendAck(msg.mtype, STATUS_ERROR, "Unknown username");
            else {
                struct SendTextPayload payload;
                memcpy(&payload.address, &userId, 4);
                sendAck(msg.mtype, STATUS_OK, &payload);
            }
        }
        else if(msg.code == SHOW_GROUPS) {
            printf("SHOW_GROUPS\n");
            struct ListPayload lista;
            int listit = 0;
            for(int i=0; i<NGROUPS; i++) {
                if(strcmp(groups[i].name, "") == 0)
                    continue;
                lista.list[listit++] = i;
            }
            lista.list[listit] = -1;

            struct ListPayload payload;
            memcpy(payload.list, &lista, 116);
            sendAck(msg.mtype, STATUS_OK, &payload);
        }
        else if(msg.code == GROUPID_NAME) {
            printf("GROUPID_NAME\n");
            struct SendTextPayload payload;
            memcpy(&payload, msg.payload, 116);
            if(payload.address<0 || payload.address > NGROUPS)
                sendAck(msg.mtype, STATUS_ERROR, "Unknown group id");
            else {
                Group *groupptr = &groups[payload.address];
                sendAck(msg.mtype, STATUS_OK, groupptr->name);
            }
        }
        else if(msg.code == SHOW_GROUPUSERS) {
            printf("SHOW_GROUPUSERS\n");
            struct SendTextPayload rcvd;
            memcpy(&rcvd, msg.payload, 116);
            int groupId = rcvd.address;

            struct ListPayload lista;
            int listit = 0;
            for(int i=0; i<NUSERS; i++) {
                if(groups[groupId].users[i] == NULL)
                    continue;

                lista.list[listit++] = groups[groupId].users[i]->id;
            }
            lista.list[listit] = -1;

            struct ListPayload payload;
            memcpy(payload.list, &lista, 116);
            sendAck(msg.mtype, STATUS_OK, &payload);
        }
        else if(msg.code == SIGNIN_GROUP) {
            printf("SIGNIN_GROUP\n");
            struct SendTextPayload rcvd;
            memcpy(&rcvd, msg.payload, 116);
            int groupId = rcvd.address;
            User *userptr = findUserByMType(msg.mtype);
            int userId = userptr->id;

            bool exist = false;
            int nullIndex = -1;
            for(int i=0; i<NUSERS; i++) {
                if(groups[groupId].users[i] == NULL) {
                    if (nullIndex == -1)
                        nullIndex = i;
                }
                else if(groups[groupId].users[i]->id == userId) {
                    exist = true;
                }
            }

            if(exist) {
                sendAck(msg.mtype, STATUS_ERROR, "You are already signed to this group");
            }
            else {
                groups[groupId].users[nullIndex] = userptr;
                sendAck(msg.mtype, STATUS_OK, "You were signed in successfully");
            }
        }
        else if(msg.code == SIGNOUT_GROUP) {
            printf("SIGNOUT_GROUP\n");

            struct SendTextPayload rcvd;
            memcpy(&rcvd, msg.payload, 116);
            int groupId = rcvd.address;

            User *userptr = findUserByMType(msg.mtype);
            bool removed = false;
            for(int i=0; i<NUSERS; i++) {
                if(groups[groupId].users[i] == userptr) {
                    removed = true;
                    groups[groupId].users[i] = NULL;
                }
            }

            if(removed) {
                sendAck(msg.mtype, STATUS_OK, "You were signed out successfully");
            }
            else {
                sendAck(msg.mtype, STATUS_ERROR, "You are not signed in this group!");
            }
        }
        else if(msg.code == SEND_PRIV) {
            printf("SEND_PRIV\n");
            struct SendTextPayload wiadomosc;
            memcpy(&wiadomosc, msg.payload, 120);

            int rcvAddress = wiadomosc.address;
            wiadomosc.address = findUserByMType(msg.mtype-10)->id;

            sendAck(users[rcvAddress].mtype+20, RCV_PRIV, &wiadomosc);
            sendAck(msg.mtype, STATUS_OK, "Message sent");
        }
        else if(msg.code == SEND_GROUP) {
            printf("SEND_GROUP\n");
            struct SendTextPayload rcvPayload;
            memcpy(&rcvPayload, msg.payload, 116);

            struct SendGroupPayload sndPayload;
            sndPayload.user = findUserByMType(msg.mtype)->id;
            sndPayload.group = rcvPayload.address;
            strcpy(sndPayload.text, rcvPayload.text);

            for(int i=0; i<NUSERS; i++) {
                if(groups[rcvPayload.address].users[i]==NULL)
                    continue;

                sendAck(groups[rcvPayload.address].users[i]->mtype+20, RCV_GROUP, &sndPayload);
            }

            sendAck(msg.mtype, STATUS_OK, "Message sent");
        }
    }
}
