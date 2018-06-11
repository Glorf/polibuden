DECLARE
  CURSOR prac(etate VARCHAR2) IS SELECT NAZWISKO FROM PRACOWNICY WHERE ETAT=etate;
  v_etat VARCHAR2(20) := 'PROFESOR';
BEGIN
  -- tylko żeby striggerować błąd:
  SELECT NAZWA INTO v_etat FROM ETATY WHERE NAZWA=v_etat;
  FOR p IN prac(v_etat) LOOP
    DBMS_OUTPUT.PUT_LINE(p.NAZWISKO);
  END LOOP;
EXCEPTION
  WHEN NO_DATA_FOUND THEN
    DBMS_OUTPUT.PUT_LINE('Nie istnieje etat o nazwie: '||v_etat);
END;

DECLARE
  CURSOR profesorowie IS SELECT ID_PRAC, NAZWISKO, PLACA_POD FROM PRACOWNICY WHERE ETAT='PROFESOR' FOR UPDATE;
  suma NUMBER;
BEGIN
  FOR profesor IN profesorowie LOOP
    SELECT SUM(PLACA_POD) INTO suma FROM PRACOWNICY WHERE ID_SZEFA = profesor.ID_PRAC;
    IF (0.1*suma)+profesor.PLACA_POD > 2000 THEN
      RAISE_APPLICATION_ERROR(-20010, 'Pensja po podwyżce przekroczyłaby 2000 zł!');
    END IF;
    UPDATE PRACOWNICY SET PLACA_POD=PLACA_POD+(0.1*suma) WHERE CURRENT OF profesorowie;
  END LOOP;
END;

DECLARE
  v_idprac NUMBER := 112;
  v_nazwisko VARCHAR2(20) := 'KOSZLAJDA';
  v_idzesp NUMBER :=30;
  v_placapod NUMBER := 500;
BEGIN
  INSERT INTO PRACOWNICY(ID_PRAC, NAZWISKO, PLACA_POD, ID_ZESP) VALUES (v_idprac, v_nazwisko, v_placapod, v_idzesp);
EXCEPTION
  WHEN OTHERS THEN
    CASE
      WHEN SQLCODE = -1 THEN DBMS_OUTPUT.PUT_LINE('ID pracownika już istnieje!');
      WHEN SQLCODE = -1400 THEN DBMS_OUTPUT.PUT_LINE('Brak identyfikatora użytkownika!');
      WHEN SQLCODE = -2290 THEN DBMS_OUTPUT.PUT_LINE('Zbyt niska płaca!');
      WHEN SQLCODE = -2291 THEN DBMS_OUTPUT.PUT_LINE('Zespół nie istnieje!');
    END CASE;
END;

DECLARE
  v_nazwisko VARCHAR2(20) := 'WEGLARZ';
  CURSOR kursor(naz VARCHAR2) IS SELECT ID_PRAC FROM PRACOWNICY WHERE NAZWISKO=naz;
  v_id NUMBER;
  v_dummy NUMBER;
  MY_EXCEPTION EXCEPTION;
  PRAGMA EXCEPTION_INIT(MY_EXCEPTION, -2292);
BEGIN
  OPEN kursor(v_nazwisko);
  FETCH kursor INTO v_id;
  IF kursor%NOTFOUND THEN RAISE_APPLICATION_ERROR(-20020, 'Nie istnieje taki pracownik!'); END IF;
  FETCH kursor INTO v_dummy;
  IF kursor%FOUND THEN RAISE_APPLICATION_ERROR(-20030, 'Niejednoznaczne wskazanie pracownika!'); END IF;
  DELETE FROM PRACOWNICY WHERE ID_PRAC=v_id;
  CLOSE kursor;
EXCEPTION
  WHEN MY_EXCEPTION THEN RAISE_APPLICATION_ERROR(-20040, 'Nie możesz usunąć przełożonego!');
END;
