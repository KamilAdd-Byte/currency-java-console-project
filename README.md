
# currency-java-console-project

Search and write exchange rate with nbp-api (http://api.nbp.pl/).

you set:
 - table
 - currency(code)
 - date
 
 http://api.nbp.pl/api/exchangerates/rates/{table}/code}/{date}/
 
and java console give you dto object and rates.

## Use:

- jsoup;
- nbp-api connection;
- gson (convert json to POJO);
- jackson (create csv file);
