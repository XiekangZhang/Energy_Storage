package main.java.de.Xiekang.Models;

import main.java.de.Xiekang.Controllers.Expectation;

public class Market extends Expectation {

    private Integer startsPreis;
    private Integer endsPreis;

    public Market() {}

    public Market(Integer startsPreis, Integer endsPreis) {
        this.startsPreis = startsPreis;
        this.endsPreis = endsPreis;
    }

    public Integer getStartsPreis() {
        return startsPreis;
    }

    public void setStartsPreis(Integer startsPreis) {
        this.startsPreis = startsPreis;
    }

    public Integer getEndsPreis() {
        return endsPreis;
    }

    public void setEndsPreis(Integer endsPreis) {
        this.endsPreis = endsPreis;
    }
}
