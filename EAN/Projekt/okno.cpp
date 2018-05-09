#include "okno.h"
#include "ui_okno.h"
#include <iostream>

Okno::Okno(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::Okno)
{
    ui->setupUi(this);
    obliczacz = new Obliczacz();
    ai = 0;
}

Okno::~Okno()
{
    delete ui;
}

void Okno::on_spinBoxN_valueChanged(int value) {
    QSpinBox *aselector = findChild<QSpinBox*>(QString("spinBoxASelect"));
    aselector->setMaximum(value);
    obliczacz->setStopien(value);
}

void Okno::on_lineEditAValue_textEdited() {
    QLineEdit *lineEditAValue = findChild<QLineEdit*>(QString("lineEditAValue"));
    obliczacz->setWspolczynnikAt(ai, lineEditAValue->text().toStdString());
}

void Okno::on_spinBoxASelect_valueChanged(int value) {
    QLineEdit *lineEditAValue = findChild<QLineEdit*>(QString("lineEditAValue"));
    ai = value;
    lineEditAValue->setText(QString::fromStdString(obliczacz->getWspolczynnikAt(ai)));
}

void Okno::on_buttonSubmit_clicked() {
    QSpinBox *spinBoxMit = findChild<QSpinBox*>(QString("spinBoxMit"));
    obliczacz->setMaxIterations(spinBoxMit->value());

    QSpinBox *spinBoxEpsilon = findChild<QSpinBox*>(QString("spinBoxEpsilon"));
    obliczacz->setEpsilon(spinBoxEpsilon->value());


    QLineEdit *lineEditX = findChild<QLineEdit*>(QString("lineEditX"));
    QLineEdit *lineEditXR = findChild<QLineEdit*>(QString("lineEditXR"));
    obliczacz->setLX(lineEditX->text().toStdString());
    obliczacz->setRX(lineEditXR->text().toStdString());

    res wynik = obliczacz->calculateNormal();

    QLabel *labelValNormal = findChild<QLabel*>(QString("labelValNormal"));
    labelValNormal->setText(QString::number(wynik.val, 'e', 14));

    QLabel *labelWNormal = findChild<QLabel*>(QString("labelWNormal"));
    labelWNormal->setText(QString::number(wynik.w, 'e', 14));

    QLabel *labelIterNormal = findChild<QLabel*>(QString("labelIterNormal"));
    labelIterNormal->setText(QString::number(wynik.it));

    ires iwynik = obliczacz->calculateInterval();

    string lwynik, rwynik, lwartosc, rwartosc;
    iwynik.w.IEndsToStrings(lwynik, rwynik);
    iwynik.val.IEndsToStrings(lwartosc, rwartosc);

    QLabel *labelValIntervalL = findChild<QLabel*>(QString("labelValIntervalL"));
    labelValIntervalL->setText(QString::fromStdString(lwartosc));

    QLabel *labelValIntervalR = findChild<QLabel*>(QString("labelValIntervalR"));
    labelValIntervalR->setText(QString::fromStdString(rwartosc));

    QLabel *labelWIntervalL = findChild<QLabel*>(QString("labelWIntervalL"));
    labelWIntervalL->setText(QString::fromStdString(lwynik));

    QLabel *labelWIntervalR = findChild<QLabel*>(QString("labelWIntervalR"));
    labelWIntervalR->setText(QString::fromStdString(rwynik));

    QLabel *labelIterInterval = findChild<QLabel*>(QString("labelIterInterval"));
    labelIterInterval->setText(QString::number(iwynik.it));

    QLabel *labelWIntervalWidth = findChild<QLabel*>(QString("labelWIntervalWidth"));
    labelWIntervalWidth->setText(QString::number(iwynik.w.GetWidth(), 'e', 14));

    QLabel *labelValIntervalWidth = findChild<QLabel*>(QString("labelValIntervalWidth"));
    labelValIntervalWidth->setText(QString::number(iwynik.val.GetWidth(), 'e', 14));

    vector<string> stany = {"Sukces", "Błędne dane", "Dzielenie przez zero", "Nie osiągnięto zadanej dokładności"};
    QLabel *labelStatus = findChild<QLabel*>(QString("labelStatus"));
    labelStatus->setText(QString::fromStdString(stany.at(wynik.st)));

    QLabel *labelIntervalStatus = findChild<QLabel*>(QString("labelIntervalStatus"));
    labelIntervalStatus->setText(QString::fromStdString(stany.at(iwynik.st)));
}
