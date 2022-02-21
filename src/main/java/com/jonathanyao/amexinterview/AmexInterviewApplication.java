// Author: Jiasheng (Jonathan) Yao
package com.jonathanyao.amexinterview;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@SpringBootApplication
@RestController
public class AmexInterviewApplication {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(AmexInterviewApplication.class, args);
    }

    @GetMapping(path="/population", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllCountryPopulations() {

        String getAllCountryCodes = "SELECT countryCode, country FROM countries;";
        String getCountryPopulation = "SELECT countryCode, stateCode, statePopulation FROM states WHERE countryCode = ?";

        Map<String, String> codeCountryMap = new LinkedHashMap<>();

        jdbcTemplate.query(getAllCountryCodes, (rs, rowNum) -> codeCountryMap.put(rs.getString("countryCode"), rs.getString("country")));

        Map<String, Integer> countryPopulationMap = new LinkedHashMap<>();
        for (String countryCode: codeCountryMap.keySet()) {
            List<Integer> populationList = new ArrayList<>();
            jdbcTemplate.query(getCountryPopulation, new Object[]{countryCode}, (rs, rowNum) -> populationList.add(rs.getInt("statePopulation")));

            countryPopulationMap.put(codeCountryMap.get(countryCode), populationList.stream().mapToInt(Integer::intValue).sum());

        }
        return new ResponseEntity(countryPopulationMap, HttpStatus.OK);
    }

    @GetMapping(path="/currency", produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllCountryCurrencies() {
        String getAllCountries = "SELECT country, currency FROM countries;";

        Map<String, String> countryCurrencyMap = new LinkedHashMap<>();
        jdbcTemplate.query(getAllCountries,  (rs, rowNum) -> countryCurrencyMap.put(rs.getString("country"), rs.getString("currency")));
        return new ResponseEntity(countryCurrencyMap, HttpStatus.OK);
    }

    @GetMapping("/validateState")
    public boolean validateState(@RequestParam(value = "country_code") String country_code, @RequestParam(value = "state_code") String state_code) {
        String getCountryStateCodePair = "SELECT stateCode FROM states WHERE countryCode = ? AND stateCode = ?;";

        List<String> queryResult = new ArrayList<>();
        jdbcTemplate.query(getCountryStateCodePair, new Object[]{country_code, state_code}, (rs, rowNum) -> queryResult.add(rs.getString("stateCode")));
        return queryResult.size() > 0;
    }

    @PutMapping(path="/addstate",consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addState(@RequestBody State stateToAdd) {
        String validateCountryCodeExist = "SELECT countryCode FROM countries WHERE countryCode = ?;";
        String validateStateCodeNotDuplicate = "SELECT stateCode FROM states WHERE countryCode = ? AND stateCode = ?;";
        String insertIntoStates = "INSERT INTO states(state, stateCode, statePopulation, countryCode) VALUES(?, ?, ?, ?);";

        Map<String, String> responseMap = new LinkedHashMap<>();

        // validate if state_population is non-negative
        if (stateToAdd.getState_population() < 0) {
            responseMap.put("error", "state_population can not be negative.");
            return new ResponseEntity(responseMap, HttpStatus.BAD_REQUEST);
        }

        List<String> queryResult = new LinkedList<>();

        // validate if country code entered exists
        jdbcTemplate.query(validateCountryCodeExist, new Object[]{stateToAdd.getCountry_cd()}, (rs, rowNum) -> queryResult.add(rs.getString("countryCode")));
        if (queryResult.isEmpty()) {
            responseMap.put("error", "country_cd entered not exist.");
            return new ResponseEntity(responseMap, HttpStatus.BAD_REQUEST);
        }

        // validate if state code entered not exists
        queryResult.clear();
        jdbcTemplate.query(validateStateCodeNotDuplicate, new Object[]{stateToAdd.getCountry_cd(), stateToAdd.getState_cd()}, (rs, rowNum) -> queryResult.add(rs.getString("stateCode")));
        if (!queryResult.isEmpty()) {
            responseMap.put("error", "state_cd entered exists.");
            return new ResponseEntity(responseMap, HttpStatus.BAD_REQUEST);
        }

        // insert into database
        int result = jdbcTemplate.update(
                insertIntoStates,
                stateToAdd.getState_name(),
                stateToAdd.getState_cd(),
                stateToAdd.getState_population(),
                stateToAdd.getCountry_cd()
        );

        if (result <= 0) {
            responseMap.put("error", "query insert failed.");
            return new ResponseEntity(responseMap, HttpStatus.OK);
        }
        responseMap.put("message", String.format("A new state %s has been inserted.", stateToAdd.getState_name()));
        return new ResponseEntity(responseMap, HttpStatus.OK);
    }
}
