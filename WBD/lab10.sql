DECLARE
  v_tekst VARCHAR2(40) :=  'Witaj, świecie!';
  v_liczba NUMBER := 1000.456;
BEGIN
  v_tekst := v_tekst||' Witaj, nowy dniu!';
  v_liczba := v_liczba + POWER(10, 15);
  DBMS_OUTPUT.PUT_LINE('Zmienna v_tekst: '||v_tekst);
  DBMS_OUTPUT.PUT_LINE('Zmienna v_liczba: '||v_liczba);
END;

--ON SQLDEVELOPER ONLY
DECLARE
  v_licz1 NUMBER := &liczba1;
  v_licz2 NUMBER := &liczba2;
BEGIN
  DBMS_OUTPUT.PUT_LINE('Wynik dodawania '|| v_licz1 ||' i '|| v_licz2 || ': '||v_licz1+v_licz2);
END;

DECLARE
  PI CONSTANT NUMBER := 3.14;
  promien NUMBER := 5;
BEGIN
  DBMS_OUTPUT.PUT_LINE('Obwód koła o promieniu równym '||promien||': '||2*promien*PI);
  DBMS_OUTPUT.PUT_LINE('Pole koła o promieniu równym '||promien||': '||PI*POWER(promien,2));
END;

DECLARE
  v_nazwisko PRACOWNICY.NAZWISKO%TYPE;
  v_etat PRACOWNICY.ETAT%TYPE;
BEGIN
  SELECT NAZWISKO, ETAT INTO v_nazwisko, v_etat FROM PRACOWNICY WHERE PLACA_POD=(SELECT MAX(PLACA_POD) FROM PRACOWNICY);
  DBMS_OUTPUT.PUT_LINE('Najlepiej zarabia pracownik '||v_nazwisko);
  DBMS_OUTPUT.PUT_LINE('Pracuje on jako '||v_etat);
END;

DECLARE
  TYPE tpracownik IS RECORD(
    nazwisko PRACOWNICY.NAZWISKO%TYPE,
    etat PRACOWNICY.ETAT%TYPE);
  pracownik tpracownik;
BEGIN
  SELECT NAZWISKO, ETAT INTO pracownik FROM PRACOWNICY WHERE PLACA_POD=(SELECT MAX(PLACA_POD) FROM PRACOWNICY);
  DBMS_OUTPUT.PUT_LINE('Najlepiej zarabia pracownik '||pracownik.nazwisko);
  DBMS_OUTPUT.PUT_LINE('Pracuje on jako '||pracownik.etat);
END;

DECLARE
  TYPE PIENIADZE IS RECORD(
    placa NUMBER
  );
  pinionc PIENIADZE;
BEGIN
  SELECT PLACA_POD*12 INTO pinionc FROM PRACOWNICY WHERE NAZWISKO='SLOWINSKI';
  DBMS_OUTPUT.PUT_LINE('Pracownik SLOWINSKI zarabia rocznie: '||pinionc.placa);
END;

DECLARE
  czas BOOLEAN := TRUE;
BEGIN
  IF czas THEN
    DBMS_OUTPUT.PUT_LINE(TO_CHAR(CURRENT_TIMESTAMP, 'HH:MI:SS'));
  ELSE
    DBMS_OUTPUT.PUT_LINE(CURRENT_DATE);
  END IF;
END;

DECLARE
  czas BOOLEAN := FALSE;
BEGIN
  CASE WHEN czas THEN
    DBMS_OUTPUT.PUT_LINE(TO_CHAR(CURRENT_TIMESTAMP, 'HH:MI:SS'));
  ELSE
    DBMS_OUTPUT.PUT_LINE(CURRENT_DATE);
  END CASE;
END;

BEGIN
  LOOP
    EXIT WHEN TO_CHAR(CURRENT_TIMESTAMP, 'SS')='25';
  END LOOP;
  DBMS_OUTPUT.PUT_LINE('Nadeszła 25 sekunda!');
END;

DECLARE
  LIMIT NUMBER := 10;
  WYNIK NUMBER := 1;
BEGIN
  FOR LICZNIK IN 1 .. LIMIT LOOP
    WYNIK:=WYNIK*LICZNIK;
  END LOOP;

  DBMS_OUTPUT.PUT_LINE('Silnia dla n='||LIMIT||': '||WYNIK);
END;

DECLARE
  dzien DATE:=DATE'2001-01-01';
BEGIN
  LOOP
    IF TRIM(TO_CHAR(dzien, 'DAY'))='PIĄTEK' AND TRIM(TO_CHAR(dzien, 'DD'))='13' THEN
      DBMS_OUTPUT.PUT_LINE(DZIEN);
    END IF;
    EXIT WHEN dzien >= DATE'2100-12-31';
    dzien := dzien + INTERVAL '1' DAY;
  END LOOP;
END;