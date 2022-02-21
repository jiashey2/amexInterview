// Author: Jiasheng (Jonathan) Yao
package com.jonathanyao.amexinterview;

public class State {
    private String country_cd;
    private String state_cd;
    private String state_name;
    private Integer state_population;

    // getters and setters
    public String getCountry_cd() {
        return this.country_cd;
    }

    public String getState_cd() {
        return this.state_cd;
    }

    public String getState_name() {
        return this.state_name;
    }

    public Integer getState_population() {
        return this.state_population;
    }

    public void setCountry_cd(String country_cd) {
        this.country_cd = country_cd;
    }

    public void setState_cd(String state_cd) {
        this.state_cd = state_cd;
    }

    public void setState_name(String state_name) {
        this.state_name = state_name;
    }

    public void setState_population(Integer state_population) {
        this.state_population = state_population;
    }
}
