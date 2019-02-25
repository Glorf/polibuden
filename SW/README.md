# Aplikacja na SW
## Architektura
* Uruchamiany jest **server** pod adresem <ADRES_SERWERA>
* Uruchamiany jest **sterownik** na urządzeniu z beaglebonem i servem. Otwierane jest połączenie TCP z <ADRES_SERWERA>
* Użytkownik pobiera aplikację **client** na telefon z Androidem
* Użytkownik robi zdjęcie - aplikacja **client** wysyła zdjęcie zapytaniem POST na <ADRES_SERWERA>/validate
* Serwer weryfikuje tożsamość użytkownika - porównując go z danymi zapisanymi w bazie sqlite - przesyła wiadomość statusu w odpowiedzi do aplikacji **client**, która tę wiadomość wyświetla na ekranie użytkownikowi
* Jeżeli tożsamość została zweryfikowana z sukcesem, **server** wysyła do **sterownika** polecenie akcji - "otwarcia bramki" - ruszenia serwem

## TODO:
* Doklepać kod serwera (Michał)
* Dodać wysyłanie zdjęcia POSTem i odbieranie odpowiedzi w kliencie (Martyna)
* Napisać sterownik (razem w labie?)
