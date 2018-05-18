package main.java.de.Xiekang.Models;

import main.java.de.Xiekang.Controllers.Expectation;

public class Market extends Expectation {

    private Integer startsPrice;
    private Integer endsPrice;

    public Market() {}

    public Market(Integer startsPrice, Integer endsPrice) {
        this.startsPrice = startsPrice;
        this.endsPrice = endsPrice;
    }

    public Integer getStartsPrice() {
        return startsPrice;
    }

    public void setStartsPrice(Integer startsPrice) {
        this.startsPrice = startsPrice;
    }

    public Integer getEndsPrice() {
        return endsPrice;
    }

    public void setEndsPrice(Integer endsPrice) {
        this.endsPrice = endsPrice;
    }
}
