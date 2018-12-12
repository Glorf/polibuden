INSERT INTO Dzien (id, nazwa) VALUES (nextval('dzien_seq'), 'Poniedziałek');
INSERT INTO Dzien (id, nazwa) VALUES (nextval('dzien_seq'), 'Wtorek');
INSERT INTO Dzien (id, nazwa) VALUES (nextval('dzien_seq'), 'Środa');
INSERT INTO Dzien (id, nazwa) VALUES (nextval('dzien_seq'), 'Czwartek');
INSERT INTO Dzien (id, nazwa) VALUES (nextval('dzien_seq'), 'Piątek');

INSERT INTO Stopien (id, nazwa) VALUES (nextval('stopien_seq'), 'mag. inż.');
INSERT INTO Stopien (id, nazwa) VALUES (nextval('stopien_seq'), 'dr');
INSERT INTO Stopien (id, nazwa) VALUES (nextval('stopien_seq'), 'dr hab.');
INSERT INTO Stopien (id, nazwa) VALUES (nextval('stopien_seq'), 'prof.');

INSERT INTO Typ_zajec (id, nazwa) VALUES (nextval('typ_zajec_seq'), 'Wykład');
INSERT INTO Typ_zajec (id, nazwa) VALUES (nextval('typ_zajec_seq'), 'Ćwiczenia');
INSERT INTO Typ_zajec (id, nazwa) VALUES (nextval('typ_zajec_seq'), 'Laboratoria');
INSERT INTO Typ_zajec (id, nazwa) VALUES (nextval('typ_zajec_seq'), 'Seminarium');

INSERT INTO Budynek (id, nazwa, adres) VALUES (nextval('budynek_seq'), 'Centrum wykładowe', 'Piotrowo 2');
INSERT INTO Budynek (id, nazwa, adres) VALUES (nextval('budynek_seq'), 'Biblioteka techniczna', 'Piotrowo 2');
INSERT INTO Budynek (id, nazwa, adres) VALUES (nextval('budynek_seq'), 'Centrum dydaktyczne wydziału technologii chemicznej', 'Berdychowo 4');

INSERT INTO Sala (id, budynek, nazwa, ile_miejsc) VALUES (nextval('sala_seq'), 1, '8 CW', 140);
INSERT INTO Sala (id, budynek, nazwa, ile_miejsc) VALUES (nextval('sala_seq'), 1, 'lab. 44', 16);
INSERT INTO Sala (id, budynek, nazwa, ile_miejsc) VALUES (nextval('sala_seq'), 2, 'L053 BT', 200);
INSERT INTO Sala (id, budynek, nazwa, ile_miejsc) VALUES (nextval('sala_seq'), 2, 'lab. 1.6.22', 16);
INSERT INTO Sala (id, budynek, nazwa, ile_miejsc) VALUES (nextval('sala_seq'), 3, '214A', 30);

INSERT INTO Wydzial (id, nazwa) VALUES (nextval('wydzial_seq'), 'Wydział Informatyki');
INSERT INTO Wydzial (id, nazwa) VALUES (nextval('wydzial_seq'), 'Wydział Architektury');
INSERT INTO Wydzial (id, nazwa) VALUES (nextval('wydzial_seq'), 'Wydział Elektryczny');
INSERT INTO Wydzial (id, nazwa) VALUES (nextval('wydzial_seq'), 'Wydział Budowy Maszyn i Zarządzania');

--Nie możesz tak zrobić - pole wydział nie jest stringiem, a kluczem obcym - można go wydobyć podzapytaniem (będzie niżej), albo wpisać "na sztywno" (polecam)
INSERT INTO Kierunek (id, wydzial, nazwa) VALUES (nextval('kierunek_seq'), 1, 'Informatyka');
INSERT INTO Kierunek (id, wydzial, nazwa) VALUES (nextval('kierunek_seq'), 1, 'Automatyka i Robotyka');
INSERT INTO Kierunek (id, wydzial, nazwa) VALUES (nextval('kierunek_seq'), 3, 'Elektrotechnika');
INSERT INTO Kierunek (id, wydzial, nazwa) VALUES (nextval('kierunek_seq'), 4, 'Mechatronika');

--Nazwa pola w klasie nie równa się nazwie kolumny. Patrz na adnotacje @Column(name=ddsds), one zawierają nazwy kolumn w bazie
INSERT INTO Grupa (id, nazwa_grupy, nazwa_podgrupy, kierunek) VALUES (nextval('grupa_seq'), 'I1', 'A', 1);
INSERT INTO Grupa (id, nazwa_grupy, nazwa_podgrupy, kierunek) VALUES (nextval('grupa_seq'), 'I1', 'B', 1);
INSERT INTO Grupa (id, nazwa_grupy, nazwa_podgrupy, kierunek) VALUES (nextval('grupa_seq'), 'E1', 'A', 3);
INSERT INTO Grupa (id, nazwa_grupy, nazwa_podgrupy, kierunek) VALUES (nextval('grupa_seq'), 'E1', 'B', 3);
INSERT INTO Grupa (id, nazwa_grupy, nazwa_podgrupy, kierunek) VALUES (nextval('grupa_seq'), 'AiR1', 'A', 2);
INSERT INTO Grupa (id, nazwa_grupy, nazwa_podgrupy, kierunek) VALUES (nextval('grupa_seq'), 'AiR1', 'B', 2);

--Tutaj jeden przykład klucza wydobytego podzapytaniem - żebyś wiedziała czemu wpisuje liczby, ale nie polecam tak robić bo to upierdliwe, a nam tu chodzi tylko o wypełnienie bazy
INSERT INTO Student (id, podgrupa, imie, nazwisko) VALUES (nextval('student_seq'), 1, 'Jan', 'Kowalski');
INSERT INTO Student (id, podgrupa, imie, nazwisko) VALUES (nextval('student_seq'), 1, 'Anna', 'Lewandowska');
INSERT INTO Student (id, podgrupa, imie, nazwisko) VALUES (nextval('student_seq'), 1, 'Janusz', 'Przybysz');
INSERT INTO Student (id, podgrupa, imie, nazwisko) VALUES (nextval('student_seq'), 1, 'Henryk', 'Krawczyk');
INSERT INTO Student (id, podgrupa, imie, nazwisko) VALUES (nextval('student_seq'), 2, 'Krzysztof', 'Nowak');
INSERT INTO Student (id, podgrupa, imie, nazwisko) VALUES (nextval('student_seq'), 2, 'Mariusz', 'Majewski');
INSERT INTO Student (id, podgrupa, imie, nazwisko) VALUES (nextval('student_seq'), 2, 'Julia', 'Kozłowska');
INSERT INTO Student (id, podgrupa, imie, nazwisko) VALUES (nextval('student_seq'), 2, 'Jan', 'Kaczmarek');

INSERT INTO Przedmiot (id, nazwa, ects) VALUES (nextval('przedmiot_seq'), 'Analiza matematyczna', 6);
INSERT INTO Przedmiot (id, nazwa, ects) VALUES (nextval('przedmiot_seq'), 'Programowanie obiektowe', 4);
INSERT INTO Przedmiot (id, nazwa, ects) VALUES (nextval('przedmiot_seq'), 'Fizyka', 3);
INSERT INTO Przedmiot (id, nazwa, ects) VALUES (nextval('przedmiot_seq'), 'Podstawy automatyki', 4);
INSERT INTO Przedmiot (id, nazwa, ects) VALUES (nextval('przedmiot_seq'), 'Statystyka', 5);

INSERT INTO Literatura (id, isbn, autor, tytul, wydawnictwo) VALUES (nextval('literatura_seq'), 123400, 'Tyszer', 'Układy cyfrowe', 'Wydawnictwo Politechnik Poznańskiej');
INSERT INTO Literatura (id, isbn, autor, tytul, wydawnictwo) VALUES (nextval('literatura_seq'), 454009, 'Cormen', 'Wprowadzenie do algorytmów', 'PWN');

INSERT INTO Zespol (id, wydzial, nazwa) VALUES (nextval('zespol_seq'), 1, 'Algorytmy');
INSERT INTO Zespol (id, wydzial, nazwa) VALUES (nextval('zespol_seq'), 1, 'Systemy rozproszone');
INSERT INTO Zespol (id, wydzial, nazwa) VALUES (nextval('zespol_seq'), 1, 'Badania operacyjne');
INSERT INTO Zespol (id, wydzial, nazwa) VALUES (nextval('zespol_seq'), 2, 'Planowanie miast i osiedli');
INSERT INTO Zespol (id, wydzial, nazwa) VALUES (nextval('zespol_seq'), 3, 'Telekomunikacja');

INSERT INTO Wykladowca(id, zespol, imie, nazwisko, pensja, zatrudniony, stopien) VALUES (nextval('wykladowca_seq'), 1, 'Jacek', 'Błażewicz', 2000, '1989-04-13', 4);
INSERT INTO Wykladowca(id, zespol, imie, nazwisko, pensja, zatrudniony, stopien) VALUES (nextval('wykladowca_seq'), 1, 'Małgorzata', 'Sterna', 2000, '1997-04-09', 4);
INSERT INTO Wykladowca(id, zespol, imie, nazwisko, pensja, zatrudniony, stopien) VALUES (nextval('wykladowca_seq'), 5, 'Marek', 'Wojciechowski', 2000, '1986-04-13', 2);
INSERT INTO Wykladowca(id, zespol, imie, nazwisko, pensja, zatrudniony, stopien) VALUES (nextval('wykladowca_seq'), 5, 'Barbara', 'Szymańska', 2000, '2000-03-23', 3);
INSERT INTO Wykladowca(id, zespol, imie, nazwisko, pensja, zatrudniony, stopien) VALUES (nextval('wykladowca_seq'), 3, 'Joanna', 'Józefowska', 2000, '1994-08-20', 4);

CREATE OR REPLACE FUNCTION staz (id_prac bigint)
RETURNS integer
AS '
BEGIN
DECLARE wynik integer;
BEGIN
	SELECT (EXTRACT(YEAR FROM CURRENT_DATE) - EXTRACT(YEAR FROM w.zatrudniony)) INTO wynik
	FROM Wykladowca w
	WHERE w.id = id_prac;
    RETURN wynik;
END;
END;'
LANGUAGE 'plpgsql';

CREATE OR REPLACE FUNCTION get_osoby (id_grupy bigint)
RETURNS integer
AS '
BEGIN
DECLARE wynik integer;
BEGIN
  wynik := 0;
	CALL ile_osob(id_grupy, wynik);
  RETURN wynik;
END;
END;'
LANGUAGE 'plpgsql';

CREATE OR REPLACE PROCEDURE
ile_osob (IN id_grupy bigint)
AS '
BEGIN
SELECT COUNT(*) FROM Student
WHERE podgrupa = id_grupy;
END;'
LANGUAGE plpgsql;

