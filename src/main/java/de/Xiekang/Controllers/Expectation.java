package main.java.de.Xiekang.Controllers;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO
 * The expectation is static.
 * The expectation will always change if the time proceeds.
 * Change that into dynamic maybe according to the actual market's price.
 */
public abstract class Expectation {

    protected Map<Integer, Map<Integer, Double>> expectationMap = new HashMap<>();
    protected Map<Integer, Double> expectationContents = new HashMap<>();
    protected double expectation;

    public Map<Integer, Map<Integer, Double>> createExpectation(int startPrice, int endPrice) {
        expectation = 1 / Math.pow((endPrice - startPrice + 1), 2);
        for (int i = startPrice; i <= endPrice; i++) {
            expectationContents.put(i, expectation);
            expectationMap.put(i, expectationContents);
        }
        this.toString();
        return expectationMap;
    }

    public double findExpectation(Map<Integer, Map<Integer, Double>> expectationMap) {
        return this.findExpectation(1, 1, expectationMap);
    }

    public double findExpectation(int fromPrice, int toPrice, Map<Integer, Map<Integer, Double>> expectationMap) {
        return expectationMap.get(fromPrice).get(toPrice);
    }


    public Map<Integer, Map<Integer, Double>> getExpectationMap() {
        return expectationMap;
    }

    public void setExpectationMap(Map<Integer, Map<Integer, Double>> expectationMap) {
        this.expectationMap = expectationMap;
    }

    @Override
    public String toString() {
        for (Integer i : expectationMap.keySet())
            System.out.println(i + " " + expectationMap.get(i) + " ");
        return null;
    }
}
