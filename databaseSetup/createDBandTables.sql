CREATE DATABASE CountryStateDB;

USE CountryStateDb;

CREATE TABLE countries
(
    country     varchar(100) not null,
    countryCode varchar(3)   not null
        primary key,
    currency    varchar(3)   not null
);

CREATE TABLE states
(
    state           varchar(100) not null,
    stateCode       varchar(3)   not null,
    statePopulation int          not null,
    countryCode     varchar(3)   not null,
    FOREIGN KEY (countryCode) references countries (countryCode),
    PRIMARY KEY (countryCode, stateCode)
);

