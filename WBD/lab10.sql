DECLARE
  v_max NUMBER;
  v_nazwisko VARCHAR2(20);
  v_etat VARCHAR2(20);
BEGIN
  SELECT MAX(PLACA_POD) INTO v_max FROM PRACOWNICY;
  SELECT NAZWISKO INTO v_nazwisko FROM PRACOWNICY WHERE PLACA_POD=v_max;
  SELECT ETAT INTO v_etat FROM PRACOWNICY WHERE PLACA_POD=v_max;
  DBMS_OUTPUT.PUT_LINE('Najlepiej zarabia pracownik '||v_nazwisko);
  DBMS_OUTPUT.PUT_LINE('Pracuje on jako '||v_etat);
END;

DECLARE CURSOR kursor IS SELECT NAZWISKO, ZATRUDNIONY FROM PRACOWNICY WHERE ETAT='ASYSTENT';
v_nazwisko PRACOWNICY.NAZWISKO%TYPE;
v_zatrudniony PRACOWNICY.ZATRUDNIONY%TYPE;
BEGIN
  OPEN kursor;
  LOOP
    FETCH kursor INTO v_nazwisko, v_zatrudniony;
    EXIT WHEN kursor%NOTFOUND;
    DBMS_OUTPUT.PUT_LINE(v_nazwisko||' pracuje od '||v_zatrudniony);
  END LOOP;
  CLOSE kursor;
END;
