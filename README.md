# AMEX Java Coding Challenge
### Author: Jiasheng (Jonathan) Yao
- [Requested Objects](#requested-objects)
- [Instruction and Project Info](#instruction-and-project-info)
  - [Setup database](#setup-database)
  - [Api Endpoints](#api-endpoints)
## Requested objects
1. [Source code for your solution to the above requests](src/main/java/com/jonathanyao/amexinterview)
2. [Schema of the database defined to hold the above data](databaseSetup/createDBandTables.sql)
3. [The mechanism used to populate the initial values in the database](databaseSetup/InsertValues.sql)
4. [Spring Boot Jar we can run in tomcat to test your code](Jars)

## Instruction and Project Info
### Setup database
1. run [createDBandTables.sql](databaseSetup/createDBandTables.sql) to create a database called CountryStateDB and set up tables
2. run [insertValues.sql](databaseSetup/InsertValues.sql) to insert all the values into table
3. run [createUser.sql](databaseSetup/createUser.sql) to create a user call 'user' identified by 'password'. 

### Api Endpoints
 - getAllCountryPopulations() HTTP GET request (path = /population) 
 - getAllCountryCurrencies() HTTP GET request (path = /currency)
 - validateState(String country_code, String state_code) HTTP GET request (path = /validateState)
 - addState(String country_cd, String state_cd, String state_name, Integer state_population) HTTP Put request (path = /addstate)

