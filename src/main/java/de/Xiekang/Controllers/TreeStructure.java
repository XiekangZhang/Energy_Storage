package main.java.de.Xiekang.Controllers;

import main.java.de.Xiekang.Models.Decisions;
import main.java.de.Xiekang.Models.DecisionsOption;
import main.java.de.Xiekang.Models.Market;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TreeStructure {

    private DefaultMutableTreeNode root;
    private List<State<Double, State<Double, Integer>>> stateList = new ArrayList<>();
    private State<Double, State<Double, Integer>> state;
    private HashMap<Integer, DefaultMutableTreeNode> stateTree;
    private boolean decisionFlag = true;
    private int index = 0;

    public TreeStructure() {

    }

    public List createState(Market market, Decisions decisions, int time) {
        if (time == 0) {
            createInitialState(market, decisions);
        } else if (time == 1) {
            createInitialState(market, decisions);
            createStateAfterTimePeriod(market);
        }
        return stateList;
    }

    /**
     * create the first state as root for the tree structure.
     * TODO
     *  - The actual price of market should be dynamic.
     * @param market
     * @param decisions
     * @return
     */
    public List createInitialState(Market market, Decisions decisions) {
        state = new State(decisions.getActualNumber(), new State(1, 1));
        stateList.add(index, state);
        incrementOfIndex();
        createInitialStateAfterDecision(market);
        return stateList;
    }

    public List createInitialStateAfterDecision(Market market) {
        for (DecisionsOption decisionsOption : DecisionsOption.values()) {
            Decisions decisions = new Decisions();
            decisions.decide(decisionsOption);
            stateList.add(index, new State<>(decisions.getActualNumber(), new State<>(market.findExpectation(1, 1, market.expectationMap), 1)));
            incrementOfIndex();
        }
        return stateList;
    }

    public List createStateAfterTimePeriod(Market market) {
        for (DecisionsOption decisionsOption : DecisionsOption.values()) {
            Decisions decisions = new Decisions();
            decisions.decide(decisionsOption);
            for (int i : market.expectationMap.keySet()) {
                state = new State<>(decisions.getActualNumber(), new State<>(market.findExpectation(1, i, market.expectationMap), i));
                stateList.add(index, state);
                incrementOfIndex();
            }
        }
        return stateList;
    }

    public TreeNode createTree(Market market, Decisions decisions, int time) {
        createState(market, decisions, time);
        int help = 0;
        root = new DefaultMutableTreeNode(stateList.get(0));
        if (time == 0) {
            for (int i = 1; i < stateList.size(); i++) {
                DefaultMutableTreeNode leaves = new DefaultMutableTreeNode(stateList.get(i));
                root.add(leaves);
            }
        } else if (time  == 1) {
            for (int i = 1; i < 4; i++) {
                DefaultMutableTreeNode leaves = new DefaultMutableTreeNode(stateList.get(i));
                DefaultMutableTreeNode newLeaves = new DefaultMutableTreeNode(stateList.get(i + 3 + help));
                DefaultMutableTreeNode newLeaves2 = new DefaultMutableTreeNode(stateList.get(i + 4 + help));
                leaves.add(newLeaves);
                leaves.add(newLeaves2);
                help ++;
                root.add(leaves);
            }
        }

        return root;
    }

    /**
     * Create the number of states based on the time period
     * @param market
     * @param time
     * @return
     */
    public int createNumberOfStates(Market market, int time) {
        int sum = 0;
        while (time >= 0) {
            sum += (int) Math.pow(market.expectationMap.size() * DecisionsOption.values().length, time) * (DecisionsOption.values().length + 1);
        }
        return sum;
    }

    public int incrementOfIndex() {
        return this.index ++;
    }
}
