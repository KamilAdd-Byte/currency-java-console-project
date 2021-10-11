<!-- in your header -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/devicon.min.css">

<!-- in your body -->
<i class="devicon-devicon-plain"></i>

## 'Waluta-Złoto-Wskaźniki' - aplikacja konsolowa java

Aplikacja oparta o usługi REST. Korzysta z nbp-api (http://api.nbp.pl/) by pozyskać dane o 30 ostatnich notowaniach złota (możliwość zapisu do pliku csv)
oraz wartości waluty z uwzględnieniem kodu tabeli, kodu waluty i daty.

Możesz ustawić:

- rodzaj tabeli (A,B,C)
- kod waluty(np: EUR - euro)
- oraz date (daty od poniedziałku do piątku)

Aplikacja zawiera też małą ściągę kodów (użyto jsoup)

http://api.nbp.pl/api/exchangerates/rates/{table}/code}/{date}/


## Użyte technologie:

- java 11
- jsoup;
- nbp-api connection;
- gson (convert json to POJO);
- jackson (create csv file);

## Instalacja i start programu

1. Ściągnij plik **REST-java-project.jar** z katalogu out/artifacts na swój komputer;
2. Otwórz terminal swojego systemu operacyjnego w folderze, do którego został pobrany plik (ok. 8 mb);
4. Wpisz w konsoli komende **java -jar REST-java-project.jar currency.App** ;
5. Program gotowy do używania.

# 
