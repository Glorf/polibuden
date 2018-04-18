SELECT NAZWISKO, ETAT, ID_ZESP, NAZWA FROM PRACOWNICY NATURAL JOIN ZESPOLY ORDER BY NAZWISKO;
SELECT NAZWISKO, ETAT, ID_ZESP, ADRES FROM PRACOWNICY NATURAL JOIN ZESPOLY WHERE ADRES = 'PIOTROWO 3A' ORDER BY NAZWISKO;
SELECT NAZWISKO, ADRES, NAZWA FROM PRACOWNICY NATURAL JOIN ZESPOLY WHERE PLACA_POD>400 ORDER BY NAZWISKO;
SELECT NAZWISKO, PLACA_POD, ETAT, NAZWA AS KAT_PLAC, PLACA_MIN, PLACA_MAX FROM PRACOWNICY JOIN ETATY ON PLACA_POD BETWEEN PLACA_MIN AND PLACA_MAX ORDER BY NAZWISKO, KAT_PLAC;
SELECT NAZWISKO, PLACA_POD, ETAT, NAZWA AS KAT_PLAC, PLACA_MIN, PLACA_MAX FROM PRACOWNICY JOIN ETATY ON PLACA_POD BETWEEN PLACA_MIN AND PLACA_MAX WHERE NAZWA='SEKRETARKA' ORDER BY PLACA_POD DESC;
SELECT p.NAZWISKO, p.ETAT, p.PLACA_POD, e.NAZWA AS KAT_PLAC, z.NAZWA FROM PRACOWNICY p NATURAL JOIN ZESPOLY z JOIN ETATY e ON p.PLACA_POD BETWEEN PLACA_MIN AND PLACA_MAX WHERE ETAT!='ASYSTENT' ORDER BY p.PLACA_POD DESC;
SELECT p.NAZWISKO, p.ETAT, p.PLACA_POD*12+NVL(p.PLACA_DOD, 0) AS ROCZNA_PLACA, z.NAZWA, e.NAZWA AS KAT_PLAC FROM PRACOWNICY p NATURAL JOIN ZESPOLY z JOIN ETATY e ON p.PLACA_POD BETWEEN e.PLACA_MIN AND e.PLACA_MAX WHERE p.ETAT IN ('ASYSTENT', 'ADIUNKT') AND p.PLACA_POD*12+NVL(p.PLACA_DOD, 0)>5500 ORDER BY p.NAZWISKO;
SELECT p.NAZWISKO, p.ID_PRAC, s.NAZWISKO AS SZEF, s.ID_PRAC AS ID_SZEFA FROM PRACOWNICY p JOIN PRACOWNICY s ON p.ID_SZEFA = s.ID_PRAC ORDER BY p.NAZWISKO;
SELECT p.NAZWISKO, p.ID_PRAC, s.NAZWISKO AS SZEF, s.ID_PRAC AS ID_SZEFA FROM PRACOWNICY p LEFT JOIN PRACOWNICY s ON p.ID_SZEFA = s.ID_PRAC ORDER BY p.NAZWISKO;
SELECT NAZWA, COUNT(NAZWISKO) AS LICZBA, AVG(PLACA_POD) FROM ZESPOLY LEFT JOIN PRACOWNICY ON ZESPOLY.ID_ZESP = PRACOWNICY.ID_ZESP GROUP BY NAZWA ORDER BY NAZWA;
SELECT s.NAZWISKO, COUNT(p.NAZWISKO) AS LICZBA FROM PRACOWNICY p JOIN PRACOWNICY s ON p.ID_SZEFA = s.ID_PRAC GROUP BY s.NAZWISKO ORDER BY LICZBA DESC;
SELECT p.NAZWISKO AS PRACOWNIK, p.ZATRUDNIONY AS ZATRUDNIONY, s.NAZWISKO AS SZEF, s.ZATRUDNIONY AS ZATRUDNIONY, FLOOR(MONTHS_BETWEEN(p.ZATRUDNIONY, s.ZATRUDNIONY)/12) AS LATA FROM PRACOWNICY p JOIN PRACOWNICY s ON p.ID_SZEFA = s.ID_PRAC WHERE FLOOR(MONTHS_BETWEEN(p.ZATRUDNIONY, s.ZATRUDNIONY)/12)<10 ORDER BY p.ZATRUDNIONY;
SELECT NAZWA FROM ETATY JOIN PRACOWNICY ON NAZWA = ETAT AND EXTRACT(YEAR FROM ZATRUDNIONY) = 1992 INTERSECT SELECT NAZWA FROM ETATY JOIN PRACOWNICY ON NAZWA = ETAT AND EXTRACT(YEAR FROM ZATRUDNIONY) = 1993;
SELECT ID_ZESP FROM ZESPOLY MINUS SELECT ID_ZESP FROM PRACOWNICY;
SELECT NAZWISKO, PLACA_POD, 'Poniżej 480 złotych' AS PROG FROM PRACOWNICY WHERE PLACA_POD<480 UNION SELECT NAZWISKO, PLACA_POD, 'Dokładnie 480 złotych' AS PROG FROM PRACOWNICY WHERE PLACA_POD=480 UNION SELECT NAZWISKO, PLACA_POD, 'Powyżej 480 złotych' AS PROG FROM PRACOWNICY WHERE PLACA_POD>480 ORDER BY PLACA_POD;