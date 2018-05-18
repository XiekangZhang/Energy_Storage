package main.java.de.Xiekang.Controllers;

import java.util.HashMap;
import java.util.Map;

public abstract class Expectation {

    protected Map<Integer, Double> expectationMap = new HashMap<Integer, Double>();
    protected Double expectation;

    /**
     * to create expectation of price or demand
     * @param start start price or demand
     * @param end end price or demand
     * @return an expectation matrix
     */
    public Map<Integer, Double> createExpectation(Integer start, Integer end) {
        expectation = 1.0 / (end - start + 1.0);
        for (int i = start; i <= end; i++) {
            expectationMap.put(i, expectation);
        }
        this.toString();
        return expectationMap;
    }

    public String toString() {
        for (Integer i : expectationMap.keySet())
            System.out.println("The key is " + i + " and the expectation is " + expectationMap.get(i));
        return null;
    }
}
