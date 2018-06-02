package main.java.de.Xiekang.Controllers;

import main.java.de.Xiekang.Models.Decisions;
import main.java.de.Xiekang.Models.DecisionsOption;
import main.java.de.Xiekang.Models.Market;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * TODO
 * - Separate the temp into statesList
 * - Test what happens when the time increases
 */
public class DecisionLists {
    private HashMap<Integer, ArrayList<State<Double, State<Double, Integer>>>> allListsOfStates = new HashMap<>();
    private ArrayList<State<Double, State<Double, Integer>>> statesList = new ArrayList<>();
    private ArrayList<State<Double, State<Double, Integer>>> statesListTemp = new ArrayList<>();
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

    public State createStateBeforeDecision(Market market, Decisions decisions, int time){
        if (time == 0) {
            state = new State<>(decisions.getActualNumber(), new State<>(market.expectation, market.getStartsPrice()));
            createStateAfterDecision();
            startState = state;
            splitLists(time);
        } else {
            for (int i : market.expectationContents.keySet()) {
                state = new State<>(decisions.getActualNumber(), new State<>(market.expectation, i));
                createStateAfterDecision();
                startState = state;
                splitLists(time);
            }
        }
        return state;
    }

    public State createStateAfterDecision() {
        if (state != null) {
            statesListTemp.add(state);
            for (DecisionsOption decisionsOption : DecisionsOption.values()) {
                Decisions decisions = new Decisions();
                decisions.decide(decisionsOption);
                state.setV1(decisions.getActualNumber());
                statesListTemp.add(state);
            }
        }
        return state;
    }

    public ArrayList splitLists(int time) {

            Iterator<State<Double, State<Double, Integer>>> iterator = statesListTemp.iterator();
            while (iterator.hasNext()) {
                if (flag) {
                    statesList.add(iterator.next());
                    if (validateOfList(time)) {
                        //System.out.println(statesList.get(0) + " " + statesList.get(1));
                        createStatesLists();
                        //System.out.println(statesList.get(0) + " " + statesList.get(1));
                        statesList.clear();
                        statesList.add(startState);
                        //System.out.println(statesList.get(0));
                    }
                    flag = false;
                } else if (startState.equals(iterator.next())) {
                    flag = true;
                    iterator.remove();
                    splitLists(time);
                }
            }
        return statesList;
    }

    public HashMap createStatesLists() {
        System.out.println(statesList.get(0) + " " + statesList.get(1));
        allListsOfStates.put(key, statesList);
        //System.out.println(allListsOfStates.get(key) + " " + allListsOfStates.get(key).size());
        key ++;
        return allListsOfStates;
    }

    public boolean validateOfList(int time) {
        if (statesList.size() == 2 * (time + 1))
            return true;
        else return false;
    }


    public HashMap<Integer, ArrayList<State<Double, State<Double, Integer>>>> getAllListsOfStates() {
        return allListsOfStates;
    }

    public void setAllListsOfStates(HashMap<Integer, ArrayList<State<Double, State<Double, Integer>>>> allListsOfStates) {
        this.allListsOfStates = allListsOfStates;
    }

    public ArrayList<State<Double, State<Double, Integer>>> getStatesList() {
        return statesList;
    }

    public void setStatesList(ArrayList<State<Double, State<Double, Integer>>> statesList) {
        this.statesList = statesList;
    }

    public ArrayList<State<Double, State<Double, Integer>>> getStatesListTemp() {
        return statesListTemp;
    }

    public void setStatesListTemp(ArrayList<State<Double, State<Double, Integer>>> statesListTemp) {
        this.statesListTemp = statesListTemp;
    }

    public State<Double, State<Double, Integer>> getState() {
        return state;
    }

    public void setState(State<Double, State<Double, Integer>> state) {
        this.state = state;
    }

    public State<Double, State<Double, Integer>> getStartState() {
        return startState;
    }

    public void setStartState(State<Double, State<Double, Integer>> startState) {
        this.startState = startState;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }
}
