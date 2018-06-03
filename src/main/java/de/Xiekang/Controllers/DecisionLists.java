package main.java.de.Xiekang.Controllers;

import main.java.de.Xiekang.Models.Decisions;
import main.java.de.Xiekang.Models.DecisionsOption;
import main.java.de.Xiekang.Models.Market;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * TODO
 * - Separate the temp into statesList
 * - Test what happens when the time increases
 */
public class DecisionLists {
    private List<State<Double, State<Double, Integer>>>[] allListsOfStates;
    private List<State<Double, State<Double, Integer>>> statesListTemp = new ArrayList<>();
    private List<State<Double, State<Double, Integer>>> statesList = new ArrayList<>();
    private State<Double, State<Double, Integer>> state;
    private State<Double, State<Double, Integer>> startState;
    private boolean flag = true;
    private int key = 0;

    public DecisionLists() {}

    public void createDecision(Market market, int time) {
        createLists(market, time);

    }

    public int createLists(Market market, int time) {
        return (int) Math.pow(market.expectationMap.keySet().size() * DecisionsOption.values().length, time) * DecisionsOption.values().length;
    }

    public void createStateBeforeDecision(Market market, Decisions decisions, int time){
        if (time == 0) {
            state = new State<>(decisions.getActualNumber(), new State<>(market.expectation, market.getStartsPrice()));
            startState = state;
            createStateAfterDecision(market, time);
        } else {
            for (int i : market.expectationContents.keySet()) {
                state = new State<>(decisions.getActualNumber(), new State<>(market.expectation, i));
                startState = state;
                createStateAfterDecision(market, time);
            }
        }
    }

    public State<Double, State<Double,Integer>> createStateAfterDecision(Market market, int time) {
        if (state != null) {
            statesListTemp.add(state);
            for (DecisionsOption decisionsOption : DecisionsOption.values()) {
                Decisions decisions = new Decisions();
                decisions.decide(decisionsOption);
                state.setV1(decisions.getActualNumber());
                statesListTemp.add(new State<>(state.getV1(), state.getV2()));
                statesListTemp.add(startState);
            }
        }
        createStatesLists(market, statesListTemp, time);
        //statesListTemp.forEach(state1 -> System.out.println(state1));
        return state;
    }

    public List[] createStatesLists(Market market, List<State<Double, State<Double, Integer>>> list, int time) {
        allListsOfStates = new List[createLists(market, time)];
        for (int i = 0; i < allListsOfStates.length; i++) {
            allListsOfStates[i] = new ArrayList<>();
            allListsOfStates[i].add(list.get(key));
            allListsOfStates[i].add(list.get(key + 1));
            key += 2;
        }

        return allListsOfStates;
    }


    @Override
    public String toString() {
        //statesListTemp.forEach(state1 -> System.out.println(state1));
        //statesList.forEach(state1 -> System.out.println(state1));
        for (int i = 0; i < allListsOfStates.length; i++) {
            System.out.println(allListsOfStates[i]);
        }
        return null;
    }

}
