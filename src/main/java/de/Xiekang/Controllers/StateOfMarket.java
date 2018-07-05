package main.java.de.Xiekang.Controllers;

public class StateOfMarket<D, P, E> {
    private int demand;
    private double expectation;
    private int price;

    public StateOfMarket(int demand, int price,  double expectation) {
        this.demand = demand;
        this.expectation = expectation;
        this.price = price;
    }

    public int getDemand() {
        return demand;
    }

    public void setDemand(int demand) {
        this.demand = demand;
    }

    public double getExpectation() {
        return expectation;
    }

    public void setExpectation(double expectation) {
        this.expectation = expectation;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "(" + demand + ", " + price + ", " + expectation + ")";
    }
}
