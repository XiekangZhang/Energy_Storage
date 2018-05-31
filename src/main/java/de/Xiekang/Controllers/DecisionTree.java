package main.java.de.Xiekang.Controllers;

import main.java.de.Xiekang.Models.Decisions;
import main.java.de.Xiekang.Models.Market;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * TODO
 * Create a decision tree
 */
public class DecisionTree {
    private ArrayList<ArrayList<HashMap<Double, Integer>>> statesLists = new ArrayList<>();
    private ArrayList<HashMap<Double, Integer>> statesList = new ArrayList<>();
    private HashMap<Double, Integer> state = new HashMap<>();
    private int time = 0;

    public DecisionTree() {}

    public void createTree(Market market, Decisions decisions) {
        initialState(market, decisions);
    }

    public void initialState(Market market, Decisions decisions) {
        for (int i : market.expectationMap.keySet()) {
            state.put(decisions.getActualNumber(), i);
            statesList.add(time, state);
            resetState();
        }
    }

    public HashMap<Double, Integer> resetState() {
        if (!this.state.isEmpty())
            state.clear();
        return state;
    }
}
