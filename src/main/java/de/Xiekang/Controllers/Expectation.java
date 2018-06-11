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
        int r = 0;
        int l = 0;
        int index = 0;

        createNumberOfExpectation(startPrice, endPrice);

        for (int i = 0; i < expectationContents.length; i++) {
            for (int j = startPrice; j <= endPrice; j++) {
                expectationContents[i].put(j, expectation[r][l]);
                l++;
            }
            r++;
            l = 0;
        }

        for (int k = startPrice; k <= endPrice; k++) {
            expectationMap.put(k, expectationContents[index++]);
        }
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
        return this.findExpectation(1, 1);
    }

    public double findExpectation(int fromPrice, int toPrice) {
        return this.expectationMap.get(fromPrice).get(toPrice);
    }


    public Map<Integer, Map<Integer, Double>> getExpectationMap() {
        return expectationMap;
    }

    public void setExpectationMap(Map<Integer, Map<Integer, Double>> expectationMap) {
        this.expectationMap = expectationMap;
    }

    public double[][] getExpectation() {
        return expectation;
    }

    public void setExpectation(double[][] expectation) {
        this.expectation = expectation;
    }

    @Override
    public String toString() {

        return null;
    }
}
