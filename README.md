"Console application based on REST API services. Uses nbp-api (http://api.nbp.pl/) to retrieve data on the last 30 gold quotes (writable to csv file) and currency values including table code, currency code and date."

Detailed description in Polish:

# 'Waluta-Złoto-Wskaźniki' - aplikacja konsolowa java

Aplikacja oparta o usługi REST. Korzysta z nbp-api (http://api.nbp.pl/) by pozyskać dane o 30 ostatnich notowaniach złota (możliwość zapisu do pliku csv)
oraz wartości waluty z uwzględnieniem kodu tabeli, kodu waluty i daty. 
Aplikacja to wybór opcji (switch). 

## Waluta

Możesz ustawić:

- rodzaj tabeli (A,B,C);
- kod waluty(np: EUR - euro);
- oraz date (daty od poniedziałku do piątku).

http://api.nbp.pl/api/exchangerates/rates/{table}/code}/{date}/


## Ściąga kodów walut

- wyswietlanie na konsoli wszystkich kodów walut w formie listy (użyto jsoup).


## Wartość złota (30 ostatnich notowań)

- wyswietlanie na konsoli 30 ostatnich notowań złota (1g w próbie 1000 wartośc w złotówkach);
- możliwośc wyświetlenia lub zapisu do osobnego pliku csv, którego nazwę sam możesz nadać.

# Użyte technologie:

- java 11
- jsoup;
- nbp-api connection;
- gson (convert json to POJO);
- jackson (create csv file);

# Instalacja i start programu:

1. Ściągnij plik **REST-java-project.jar** z katalogu out/artifacts na swój komputer;
2. Otwórz terminal swojego systemu operacyjnego w folderze, do którego został pobrany plik (ok. 8 mb);
4. Wpisz w konsoli komende **java -jar REST-java-project.jar currency.App** ;
5. Program gotowy do używania.

