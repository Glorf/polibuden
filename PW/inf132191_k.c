#include "inf132191_h.h"
#include <stdio.h>
#include <sys/msg.h>
#include <sys/prctl.h>
#include <signal.h>
#include <string.h>
#include <stdlib.h>
#include <stdbool.h>
#include <unistd.h>

#define QUEUE_SND 2137
#define QUEUE_RCV 2138

int privtype;
int rcvid;
int sndid;
int userid;
char login[56];

Message *sendAndGet(enum TypWiadomosci typ, void *ptr) {
    Message *message = malloc(128);
    if(ptr != NULL)
        memcpy(message->payload, ptr, 116);
    message->code = typ;
    if(typ == FREE)
        message->mtype = privtype;
    else
        message->mtype = privtype+10;

    msgsnd(sndid, message, 120, 0);
    if(typ==FREE)
        msgrcv(rcvid, message, 120, privtype, 0);
    else
        msgrcv(rcvid, message, 120, privtype+10, 0);

    return message;
}

bool doLogin(char *login, char *password, char *response) {
    LoginPayload loginPayload;
    strcpy(loginPayload.login, login);
    strcpy(loginPayload.password, password);

    Message *respMsg = sendAndGet(LOGIN, &loginPayload);
    memcpy(response, respMsg->payload, 116);
    bool status;
    if(respMsg->code == STATUS_OK)
        status = true;
    else
        status = false;
    free(respMsg);
    return status;
}

bool doLogout(char *response) {
    Message *respMsg = sendAndGet(LOGOUT, NULL);
    memcpy(response, respMsg->payload, 116);
    bool status;
    if(respMsg->code == STATUS_OK)
        status = true;
    else
        status = false;
    free(respMsg);
    return status;
}

void conversationPriv(int userId) {
    printf("Napisz wiadomość: ");
    struct SendTextPayload payload;
    payload.address = userId;
    char dummy;
    scanf("%c",&dummy);
    fgets(payload.text,112,stdin);
    Message *rcv = sendAndGet(SEND_PRIV, &payload);
    if(rcv->code==STATUS_ERROR) {
        printf("%s\n", rcv->payload);
    }
}


void menuPriv() {
    Message *respMsg = sendAndGet(SHOW_LOGGED, NULL);
    struct ListPayload lista;
    memcpy(&lista, respMsg->payload, 116);
    free(respMsg);
    if(lista.list[1] == -1) {
        printf("Brak zalogowanych użytkowników!\n");
        return;
    }
    printf("Wybierz użytkownika:\n");
    for(int i=0; i<29 && lista.list[i]!=-1; i++) {
        char payload[116];
        memcpy(payload, &lista.list[i], sizeof(int));
        Message *msgUserId = sendAndGet(USERID_NAME, payload);
        if(lista.list[i] != userid)
            printf("%d) %s\n", lista.list[i], msgUserId->payload);
        free(msgUserId);
    }
    printf("Wybrany id: ");
    int chosen;
    scanf("%d", &chosen);
    conversationPriv(chosen);
}

void conversationGroup(int groupId) {
    printf("Napisz wiadomość: ");
    struct SendTextPayload payload;
    payload.address = groupId;
    char dummy;
    scanf("%c",&dummy);
    fgets(payload.text,112,stdin);
    Message *rcv = sendAndGet(SEND_GROUP, &payload);
    if(rcv->code==STATUS_ERROR) {
        printf("%s\n", rcv->payload);
    }
}

void signOutGroup(int groupId) {
    struct SendTextPayload payload;
    payload.address = groupId;
    Message *response = sendAndGet(SIGNOUT_GROUP, &payload);
    printf("%s\n", response->payload);
}

void signInGroup(int groupId) {
    struct SendTextPayload payload;
    payload.address = groupId;
    Message *response = sendAndGet(SIGNIN_GROUP, &payload);
    printf("%s\n", response->payload);
}

void grupaSzczegoly(int groupId) {
    struct SendTextPayload payload;
    payload.address = groupId;
    Message *nazwa = sendAndGet(GROUPID_NAME, &payload);
    printf("Nazwa: %s\n", nazwa->payload);
    free(nazwa);
    bool registered = false;
    printf("Członkowie: \n");
    Message *czlonkowie = sendAndGet(SHOW_GROUPUSERS, &payload);
    struct ListPayload lista;
    memcpy(&lista, czlonkowie->payload, 116);
    free(czlonkowie);
    for (int i = 0; lista.list[i] != -1; i++) {
        if (lista.list[i] == userid)
            registered = true;
        struct SendTextPayload userPld;
        userPld.address = lista.list[i];
        Message *nCzl = sendAndGet(USERID_NAME, &userPld);
        printf("%d) %s\n", lista.list[i], nCzl->payload);
    }
    if(!registered)
        printf("Nie jesteś członkiem grupy\n");
    else
        printf("Jesteś członkiem grupy\n");

    printf("Wybierz opcję: \n");
    if(registered) {
        printf("1) Wyślij wiadomość\n");
        printf("2) Wypisz się\n");
        int choice;
        scanf("%d", &choice);
        switch(choice) {
            case 1: conversationGroup(groupId); break;
            case 2: signOutGroup(groupId); break;
            default: return;
        }
    }
    else {
        printf("1) Zapisz się\n");
        int choice;
        scanf("%d", &choice);
        switch(choice) {
            case 1: signInGroup(groupId); break;
            default: return;
        }
    }
}

void menuGrup() {
    Message *respMsg = sendAndGet(SHOW_GROUPS, NULL);
    struct ListPayload lista;
    memcpy(&lista, respMsg->payload, 116);
    free(respMsg);
    if(lista.list[0] == -1) {
        printf("Brak dostępnych grup!\n");
        return;
    }
    printf("Wybierz grupę:\n");
    for(int i=0; i<29 && lista.list[i]!=-1; i++) {
        struct SendTextPayload payload;
        memcpy(&payload.address, &lista.list[i], 4);
        Message *userId = sendAndGet(GROUPID_NAME, &payload);
        printf("%d) %s\n", lista.list[i], userId->payload);
    }
    printf("Wybrany id: ");
    int chosen;
    scanf("%d", &chosen);
    grupaSzczegoly(chosen);
}


void logout() {
    char response[116];
    doLogout(response);
    printf("%s\n", response);
}

int main() {
    printf("Witamy w aplikacji\n");

    rcvid = msgget(QUEUE_RCV, 0644);
    sndid = msgget(QUEUE_SND, 0644);

    for(privtype = 1;privtype<=9; privtype++) {
        Message *ret = sendAndGet(FREE, NULL);
        if(ret->code == STATUS_OK) {
            free(ret);
            break;
        } else
            free(ret);
    }


    int deliveryProcess = fork();

    if(deliveryProcess == 0) {
        prctl(PR_SET_PDEATHSIG, SIGHUP);
        Message msg;
        bool always = true;
        while(always) {
            msgrcv(rcvid, &msg, 120, privtype+20, 0);
            if(msg.code == RCV_PRIV) {
                struct SendTextPayload payload;
                memcpy(&payload, &msg.payload, 116);

                struct SendTextPayload hint;
                hint.address = payload.address;
                Message *name = sendAndGet(USERID_NAME, &hint);
                printf("Prywatna wiadomość od %s: %s\n", name->payload, payload.text);
            }
            else if(msg.code == RCV_GROUP) {
                struct SendGroupPayload payload;
                memcpy(&payload, &msg.payload, 116);
                struct SendTextPayload hintGroup;
                hintGroup.address = payload.group;
                Message *groupName = sendAndGet(GROUPID_NAME, &hintGroup);

                struct SendTextPayload hintUser;
                hintUser.address = payload.user;
                Message *userName = sendAndGet(USERID_NAME, &hintUser);

                printf("Użytkownik %s do grupy %s: %s\n", userName->payload, groupName->payload, payload.text);
            }
        }
    }

    char messagebuf[116];
    bool success;

    bool logged = false;
    while(!logged) {

        printf("Podaj login:");
        char haslo[56];

        scanf("%s", login);
        printf("Podaj hasło:");
        scanf("%s", haslo);

        success = doLogin(login, haslo, messagebuf);
        if (!success)
            printf("%s\n", messagebuf);
        else {
            logged = true;
            printf("%s\n", messagebuf);
        }
    }

    char payload[116];
    strcpy(payload, login);
    Message *userIdMsg = sendAndGet(NAME_USERID, payload);

    struct SendTextPayload rcvpayload;
    memcpy(&rcvpayload, userIdMsg->payload, 116);
    free(userIdMsg);
    userid = rcvpayload.address;

    bool running = true;
    while(running) {
        printf("Witaj w aplikacji\n");
        printf("1) Chat prywatny\n");
        printf("2) Chat grupowy\n");
        printf("3) Wyloguj\n");
        printf("Wybierz opcję: ");
        int wybor;
        scanf("%d", &wybor);

        switch(wybor) {
            case 1: menuPriv(); break;
            case 2: menuGrup(); break;
            case 3: logout(); running=false; break;
            default: continue;
        }
    }

    return 0;
}