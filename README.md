## 'Waluta-Złoto-Wskaźniki' - aplikacja konsolowa java

Aplikacja oparta o usługi REST. Korzysta z nbp-api (http://api.nbp.pl/) by pozyskac dane o 30 ostatnich notowaniach złota (możliwośc zapisu do pliku csv)
oraz wartości waluty z uwzględnieniem tabeli, kodu waluty i daty.

Możesz ustawić:

- rodzaj tabeli (A,B,C)
- kod waluty(np: EUR - euro)
- oraz date (daty od poniedziałku do piątku)

Aplikacja zawiera też małą ściągę kodów

http://api.nbp.pl/api/exchangerates/rates/{table}/code}/{date}/

and java console give you dto object and rates.

## use:

- jsoup;
- nbp-api connection;
- gson (convert json to POJO);
- jackson (create csv file);

## install and project lunch

1. Download zip or tar.gz with CODE tab (Windows, linux..) on your computer,
2. Unpack the file,
3. Open terminal and use commend $ mvn clean istall
4. Next buid package $ mvn package
5. Use $ cd target
6. Execute commend $ java -cp REST-java-project-1.0-SNAPSHOT.jar currencyapp/App
