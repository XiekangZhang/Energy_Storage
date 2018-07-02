package main.java.de.Xiekang.Controllers;

public class StateOfMarket<D, P, E> {
    private D demand;
    private E expectation;
    private P price;

    public StateOfMarket(D demand, P price,  E expectation) {
        this.demand = demand;
        this.expectation = expectation;
        this.price = price;
    }

    public D getDemand() {
        return demand;
    }

    public void setDemand(D demand) {
        this.demand = demand;
    }

    public E getExpectation() {
        return expectation;
    }

    public void setExpectation(E expectation) {
        this.expectation = expectation;
    }

    public P getPrice() {
        return price;
    }

    public void setPrice(P price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "(" + demand + ", " + price + ", " + expectation + ")";
    }
}
