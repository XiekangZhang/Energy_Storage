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
    protected Map<Integer, Double> expectationContents[];
    protected double[][] expectation;

    public Map<Integer, Map<Integer, Double>> createExpectation(int startPrice, int endPrice) {
        int j = 0;
        int t = 0;
        int index = 0;

        createNumberOfExpectation(startPrice, endPrice);

        expectationContents[0].put(startPrice, expectation[0][0]);
        expectationContents[0].put(endPrice, expectation[0][1]);

        expectationContents[1].put(startPrice, expectation[1][0]);
        expectationContents[1].put(endPrice, expectation[1][1]);

        for (int k = startPrice; k <= endPrice; k++) {
            expectationMap.put(k, expectationContents[index++]);
        }

        this.toString();
        return expectationMap;
    }

    public void createNumberOfExpectation(int startPrice, int endPrice) {
        expectation = new double[endPrice - startPrice + 1][endPrice - startPrice + 1];
        expectationContents = new HashMap[endPrice - startPrice + 1];
        for (int i = 0; i < expectationContents.length; i++) {
            expectationContents[i] = new HashMap<>();
        }
        createExpectationArray(expectation);
    }

    public double[][] createExpectationArray(double[][] expectation) {
        expectation[0][0] = 0.2;
        expectation[0][1] = 0.8;
        expectation[1][0] = 0.4;
        expectation[1][1] = 0.6;
        return expectation;
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
