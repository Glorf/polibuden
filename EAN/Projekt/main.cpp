#include "okno.h"
#include <QApplication>

int main(int argc, char *argv[])
{
    QApplication a(argc, argv);
    Okno w;
    w.show();

    return a.exec();
}
