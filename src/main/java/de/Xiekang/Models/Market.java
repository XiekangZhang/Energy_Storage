package main.java.de.Xiekang.Models;

import main.java.de.Xiekang.Controllers.Expectation;

/**
 * Market's price at a time point is included in this class
 * This class inherits the class of expectation to create expectation table
 * TODO
 * - Consider the demand
 * - use price database
 * - Time based on real time (Hour)
 */
public class Market extends Expectation {

    private int startsPrice;
    private int endsPrice;
    private int demand;

    public Market() {}

    public Market(int startsPrice, int endsPrice) {
        this.startsPrice = startsPrice;
        this.endsPrice = endsPrice;
    }

    public int getStartsPrice() {
        return startsPrice;
    }

    public void setStartsPrice(int startsPrice) {
        this.startsPrice = startsPrice;
    }

    public int getEndsPrice() {
        return endsPrice;
    }

    public void setEndsPrice(int endsPrice) {
        this.endsPrice = endsPrice;
    }

    public int getDemand() {
        return demand;
    }

    public void setDemand(int demand) {
        this.demand = demand;
    }
}
