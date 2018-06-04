package main.java.de.Xiekang.Controllers;

import main.java.de.Xiekang.Models.Battery;
import main.java.de.Xiekang.Models.DecisionsOption;
import main.java.de.Xiekang.Models.Market;
import java.util.ArrayList;
import java.util.List;

public class StateGenerator {
    private List<State<Double, State<Double, Integer>>> stateList = new ArrayList<>();
    private State<Double, State<Double, Integer>>[] state;
    private int index = 0;
    private int specialIndex = 0;
    private boolean decisionFlag = true;
    private int sum = 1;

    public StateGenerator() {}

    public void createState(Market market, Battery battery, int time) {
        createInitialState(market, battery, time);
        createInitialStateAfterDecision(market, battery, specialIndex, time);
        createStateAfterTime(market, battery, time);
    }

    public State[] createInitialState(Market market, Battery battery, int time) {
        state = new State[createNumberOfStates(market, time)];
        System.out.println(state.length);
        state[index] = new State(battery.getActualNumber(), new State(market.findExpectation(market.expectationMap), 1));
        index++;
        return state;
    }

    public State[] createInitialStateAfterDecision(Market market, Battery battery, int specialIndex, int time) {
        for (DecisionsOption decisionsOption : DecisionsOption.values()) {
            switch (decisionsOption) {
                case Buy: {
                    if (battery.getActualNumber() < battery.getCapacity()) {
                        state[index] = new State<>(state[specialIndex].getV1() + battery.getInputLimitation() * battery.getEfficiency(),
                                state[specialIndex].getV2());
                        index++;
                    } else {
                        state[index] = new State<>(0.0, new State<>(0.0, 0));
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
                        state[index] = new State<>(0.0, new State<>(0.0, 0));
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
            for (int j = 0; j < needNumberOfStatesBeforeDecision(); j++) {
                for (int i : market.expectationMap.keySet()) {
                    state[index] = new State(state[specialIndex + 1].getV1(), new State<>(market.findExpectation(1, i, market.expectationMap), i));
                    index++;
                }
                specialIndex++;
            }
            time--;
            createStateAfterDecision(market, battery, time);
        }
        return state;
    }

    public State[] createStateAfterDecision(Market market, Battery battery, int time) {
        for (int i = 0; i < needNumberOfStatesBeforePrice(market); i++) {
            Battery batteryUpdate = new Battery(battery.getCapacity(),
                    state[specialIndex + 1].getV1());
            createInitialStateAfterDecision(market, batteryUpdate, specialIndex + 1, time);
            specialIndex++;
        }
        createStateAfterTime(market, battery, time);
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

    @Override
    public String toString() {
        stateList.forEach(state -> System.out.println(state));
        System.out.println(specialIndex);
        return super.toString();
    }
}