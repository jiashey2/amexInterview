USE CountryStateDB;

INSERT INTO countries(COUNTRY, COUNTRYCODE, CURRENCY) VALUES('Big Land', 'BLD', 'CHK');
INSERT INTO countries(COUNTRY, COUNTRYCODE, CURRENCY) VALUES('Mordor', 'MDR', 'GUL');
INSERT INTO countries(COUNTRY, COUNTRYCODE, CURRENCY) VALUES('Numberland', 'NUM', 'DIG');

INSERT INTO states(state, stateCode, statePopulation, countryCode) VALUES('Left Province', 'LEF', 10100, 'BLD');
INSERT INTO states(state, stateCode, statePopulation, countryCode) VALUES('RIGHT Province', 'RIG', 778030, 'BLD');
INSERT INTO states(state, stateCode, statePopulation, countryCode) VALUES('Topside', 'TSD', 2200340, 'BLD');
INSERT INTO states(state, stateCode, statePopulation, countryCode) VALUES('Center Province', 'CTR', 1340922, 'BLD');
INSERT INTO states(state, stateCode, statePopulation, countryCode) VALUES('Udun', 'UDN', 2000110, 'MDR');
INSERT INTO states(state, stateCode, statePopulation, countryCode) VALUES('Gorgoroth', 'GOR', 3120900, 'MDR');
INSERT INTO states(state, stateCode, statePopulation, countryCode) VALUES('Nurn', 'NRN', 1100000, 'MDR');
INSERT INTO states(state, stateCode, statePopulation, countryCode) VALUES('Kand', 'KND', 2500000, 'MDR');
INSERT INTO states(state, stateCode, statePopulation, countryCode) VALUES('One', 'ONE', 1150000, 'NUM');
INSERT INTO states(state, stateCode, statePopulation, countryCode) VALUES('Two', 'TWO', 25320000, 'NUM');
INSERT INTO states(state, stateCode, statePopulation, countryCode) VALUES('Three', 'TRE', 310000, 'NUM');
INSERT INTO states(state, stateCode, statePopulation, countryCode) VALUES('Four', 'FOR', 400000, 'NUM');