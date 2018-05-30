package main.java.de.Xiekang.Controllers;

import java.util.HashMap;
import java.util.Map;

public abstract class Expectation {

    protected Map<Integer, Map<Integer, Double>> expectationMap = new HashMap<>();
    protected Map<Integer, Double> expectationContents = new HashMap<>();
    protected Double expectation;

    public Map<Integer, Map<Integer, Double>> createExpectation(Integer startPrice, Integer endPrice) {
        expectation = 1 / Math.pow((endPrice - startPrice + 1), 2);
        for (Integer i = 1; i <= endPrice - startPrice + 1; i++) {
            expectationContents.put(i, expectation);
            expectationMap.put(i, expectationContents);
        }
        this.toString();
        return expectationMap;
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
