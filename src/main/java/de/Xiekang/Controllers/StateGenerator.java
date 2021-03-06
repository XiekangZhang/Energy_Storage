package main.java.de.Xiekang.Controllers;

import main.java.de.Xiekang.Models.Battery;
import main.java.de.Xiekang.Models.DecisionsOption;
import main.java.de.Xiekang.Models.Market;
import main.java.de.Xiekang.Models.TimeIntervalOption;

import java.util.ArrayList;
import java.util.List;

public class StateGenerator {
    private List<State<Double, StateOfMarket<Integer, Integer, Double>>> stateList = new ArrayList<>();
    private State<Double, StateOfMarket<Integer, Integer, Double>>[] state;
    private State<Double, StateOfMarket<Integer, Integer, Double>>[] stateOld;
    private int index = 0;
    private int specialIndex = 0;
    private boolean decisionFlag = true;
    private int sum = 1;


    public StateGenerator() {}

    public List createStates(Market market, Battery battery, int time, TimeIntervalOption option) {
        int timeTemp = time;
        if (timeTemp > TimeIntervalOption.changeOptionToInt(option)) {
            createState(market, battery, TimeIntervalOption.changeOptionToInt(option));
            stateOld = new State[createNumberOfStates(market, TimeIntervalOption.changeOptionToInt(option))];
            stateOld = state;
            time -= TimeIntervalOption.changeOptionToInt(option);
            reset();

            while (time > TimeIntervalOption.changeOptionToInt(option)) {
                createInitialStates(market, battery, TimeIntervalOption.changeOptionToInt(option));
                time -= TimeIntervalOption.changeOptionToInt(option);
            }

            createInitialStates(market, battery, time);


        } else if (timeTemp <= TimeIntervalOption.changeOptionToInt(option)) {
            createState(market, battery, time);
        }
        this.transferArrayToList();
        return stateList;
    }

    public List createState(Market market, Battery battery, int time) {
        this.createInitialState(market, battery, time);
        this.createInitialStateAfterDecision(battery, specialIndex);
        this.createStateAfterTime(market, battery, time);
        this.transferArrayToList();
        return stateList;
    }

    public State[] createInitialState(Market market, Battery battery, int time) {
        //create the number of states are needed according to the time.
        state = new State[createNumberOfStates(market, time)];
        state[index] = new State(battery.getActualNumber(), new StateOfMarket<>(market.getDemand(), market.getStartsPrice(), 1));
        index++;

        return state;
    }

    public State[] createInitialStates(Market market, Battery battery, int time) {
        state = new State[createNumberOfStates(market, time)];
        int helpIndex = (int)Math.random() * 10 + 10;
        state[index] = new State<>(stateOld[helpIndex].getV1(), stateOld[helpIndex].getV2());
        Battery batteryUpdate = new Battery(battery.getCapacity(), stateOld[helpIndex].getV1());
        index++;
        createInitialStateAfterDecision(batteryUpdate, specialIndex);
        createStateAfterTime(market, batteryUpdate, time);
        return state;
    }

    public State[] createInitialStateAfterDecision(Battery battery, int specialIndex) {
        for (DecisionsOption decisionsOption : DecisionsOption.values()) {
            switch (decisionsOption) {
                case Buy: {
                    if (battery.getActualNumber() < battery.getCapacity()) {
                        state[index] = new State<>(state[specialIndex].getV1() + battery.getInputLimitation() * battery.getEfficiency(),
                                state[specialIndex].getV2());
                        index++;
                    } else {
                        state[index] = new State<>(state[specialIndex].getV1(), state[specialIndex].getV2());
                        index++;
                    }
                    break;
                }

                case Sell: {
                    if (battery.getActualNumber() > 0) {
                        state[index] = new State<>(state[specialIndex].getV1() - battery.getOutputLimitation() * battery.getEfficiency(),
                                state[specialIndex].getV2());
                        index++;
                    } else {
                        state[index] = new State<>(state[specialIndex].getV1(), state[specialIndex].getV2());
                        index++;
                    }
                    break;
                }
                case Stay: {

                        state[index] =  new State<>(state[specialIndex].getV1() * battery.getEfficiency(),
                                state[specialIndex].getV2());
                        index++;
                    break;
                }
                default:
                    System.out.println("Please choose either buy or sell or stay!");
            }
        }
        return state;
    }

    public State[] createStateAfterTime(Market market, Battery battery, int time) {

        while (time > 0) {
            updateMarket(market);
            for (int j = 0; j < needNumberOfStatesBeforeDecision(); j++) {
                for (int i : market.expectationMap.keySet()) {
                    state[index] = (state[specialIndex + 1] == null) ?
                            null :
                            new State(state[specialIndex + 1].getV1(), new StateOfMarket<>(market.getDemand(), i, market.findExpectation(state[specialIndex + 1].getV2().getPrice(), i)));
                    index++;
                }
                specialIndex++;
            }
            time--;
            createStateAfterDecision(market, battery);
        }
        return state;
    }

    public State[] createStateAfterDecision(Market market, Battery battery) {
        for (int i = 0; i < needNumberOfStatesBeforePrice(market); i++) {
            Battery batteryUpdate = (state[specialIndex + 1] == null) ? null :
                    new Battery(battery.getCapacity(), state[specialIndex + 1].getV1());
            createInitialStateAfterDecision(batteryUpdate, specialIndex + 1);
            specialIndex++;
        }
        return state;
    }

    public List transferArrayToList() {
        for (int i = 0; i < state.length; i++) {
            stateList.add(state[i]);
        }
        return stateList;
    }

    public int createNumberOfStates(Market market, int time) {
        int sum = 0;
        while (time >= 0) {
            sum += (int) Math.pow(market.expectationMap.size() * DecisionsOption.values().length, time) * (DecisionsOption.values().length + 1);
            time--;
        }
        return sum;
    }

    public int needNumberOfStatesBeforeDecision() {
        if (decisionFlag) {
            sum *= DecisionsOption.values().length;
            decisionFlag = false;
        }
        return sum;
    }

    public int needNumberOfStatesBeforePrice(Market market) {
        if (!decisionFlag) {
            sum *= market.expectationMap.size();
            decisionFlag = true;
        }
        return sum;
    }

    public Market updateMarket(Market market) {
        market.setDemand((int)(Math.random() * 10 + 1));

        /*
        TODO
        - Wahrscheinlichkeit update
         */
        return market;
    }

    public void reset() {
        index = 0;
        specialIndex = 0;
        decisionFlag = true;
        sum = 1;
    }

    @Override
    public String toString() {
        System.out.println(stateList.size());
        for (State state : stateList)
            System.out.println(state);
        return super.toString();
    }
}
