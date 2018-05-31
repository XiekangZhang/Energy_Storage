package main.java.de.Xiekang.Controllers;

import main.java.de.Xiekang.Models.Decisions;
import main.java.de.Xiekang.Models.DecisionsOption;
import main.java.de.Xiekang.Models.Market;

import java.util.ArrayList;

/**
 * TODO
 * - Separate the temp into statesList
 * - Test what happens when the time increases
 */
public class DecisionTree {
    private ArrayList<State<Double, Integer>>[] statesLists;
    private ArrayList<State<Double, Integer>> temp = new ArrayList<>();
    private ArrayList<State<Double, Integer>> statesList = new ArrayList<>();
    private State<Double, Integer> stateVor;
    private State<Double, Integer> stateAfter;

    public DecisionTree() {
    }

    public int createNumberOfStatesLists(Market market, int time) {
        return (int)Math.pow(market.expectationMap.keySet().size() * DecisionsOption.values().length, time +1);
    }

    public State<Double, Integer> createState(Market market, Decisions decisions, int time) {
        statesLists = new ArrayList[createNumberOfStatesLists(market, time)];

        for (int i : market.expectationMap.keySet()) {
            stateVor = new State<>(decisions.getActualNumber(), i);
            stateAfter = stateVor;
            temp.add(stateVor);
            makeDecision();
        }

        for (int i = 0; i < temp.size(); i++)
            System.out.println(temp.get(i));
        return stateVor;
    }

    public void makeDecision() {
        for (DecisionsOption decisionsOption : DecisionsOption.values()) {
            Decisions decisions = new Decisions();
            decisions.decide(decisionsOption);
            stateAfter.setV1(decisions.getActualNumber());
            System.out.println(stateAfter.getV1());
            temp.add(new State<>(stateAfter.getV1(), stateAfter.getV2()));
        }
    }

}
