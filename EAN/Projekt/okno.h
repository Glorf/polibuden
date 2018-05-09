#ifndef OKNO_H
#define OKNO_H

#include <QMainWindow>
#include "liczenie.h"

namespace Ui {
class Okno;
}

class Okno : public QMainWindow
{
    Q_OBJECT

public:
    explicit Okno(QWidget *parent = 0);
    ~Okno();

private slots:
    void on_spinBoxN_valueChanged(int value);
    void on_lineEditAValue_textEdited();
    void on_spinBoxASelect_valueChanged(int value);
    void on_buttonSubmit_clicked();
private:
    Ui::Okno *ui;
    Obliczacz *obliczacz;
    int ai;
};

#endif // OKNO_H
