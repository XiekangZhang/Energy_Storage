package main.java.de.Xiekang.Controllers;

import main.java.de.Xiekang.Models.*;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.List;

public class TreeStructure {

    private DefaultMutableTreeNode root;
    private List<DefaultMutableTreeNode>[] leaves;
    private List<State<Double, StateOfMarket<Integer, Integer, Double>>> stateList = new ArrayList();
    @Deprecated
    protected List<State<Double, StateOfMarket<Integer, Integer, Double>>>[] states;
    private int specialIndex = 0;
    private int index = 1;
    private int index1 = 0;
    private int indexHelp = 1;
    protected Market market;
    private Battery battery;
    private int time;
    private StateGenerator stateGenerator;

    protected Node<State<Double, StateOfMarket<Integer, Integer, Double>>> root1;
    protected List<Node<State<Double, StateOfMarket<Integer, Integer, Double>>>>[] leaves1;


    public TreeStructure() {
        Time newTime = new Time(1, 10, TimeIntervalOption.Four_Hour);
        this.time = newTime.TimeCalculation();

        this.market = new Market(1, 2, 1);
        this.market.createExpectation(market.getStartsPrice(), market.getEndsPrice());

        this.battery = new Battery(3, 2);
        this.stateGenerator = new StateGenerator();
        stateList = this.stateGenerator.createState(market, battery, time);
    }

    @Deprecated
    public void appendTreeStructure() {

        stateList = this.stateGenerator.createStates(market, battery, time, TimeIntervalOption.Four_Hour);

        createLeaves();
    }

    /**
     * Crate tree used for visualization
     * @return
     */
    public DefaultMutableTreeNode createTree() {

        for (int i = 0; i < leaves.length - 1; i++) {
            if (i % 2 == 0) {
                int d1 = 0;
                for (int j = 0; j < leaves[i].size(); j++) {
                    if (d1 < leaves[i + 1].size()) {
                        for (int d = 0; d < DecisionsOption.values().length; d++) {
                            leaves[i].get(j).add(leaves[i + 1].get(d + d1));
                            leaves1[i].get(j).addChild(leaves1[i + 1].get(d + d1));
                        }
                        d1 += DecisionsOption.values().length;
                    }

                }
            } else {
                int p1 = 0;
                for (int j = 0; j < leaves[i].size(); j++) {
                    if (p1 < leaves[i + 1].size()) {
                        for (int p = 0; p < market.expectationMap.size(); p++) {
                            leaves[i].get(j).add(leaves[i + 1].get(p + p1));
                            leaves1[i].get(j).addChild(leaves1[i + 1].get(p + p1));
                        }
                        p1 += market.expectationMap.size();
                    }
                }
            }
        }
        root = leaves[0].get(0);
        root1 = leaves1[0].get(0);
        deleteDuplicateChildren();
        return root;
    }

    /**
     * Todo
     * - Frage wie kann ich den Track suchen nach des Merges?
     *
     * @return
     */

    public TreeNode deleteDuplicateChildren() {

        /*for (int i = 0; i < leaves.length; i++) {
            for (int j = 0; j < leaves[i].size(); j++) {
                for (int k = leaves[i].size() - 1; k > j; k--) {
                    if (leaves[i].get(j).toString().compareTo(leaves[i].get(k).toString()) == 0) {
                        if (i % 2 == 0) {
                            if (leaves[i - 1].get(Math.floorDiv(k, market.expectationMap.size())).isNodeChild(leaves[i].get(k))) {
                                leaves[i - 1].get(Math.floorDiv(k, market.expectationMap.size())).remove(leaves[i].get(k));
                            }
                        } else {
                            if (leaves[i - 1].get(Math.floorDiv(k, DecisionsOption.values().length)).isNodeChild(leaves[i].get(k)))
                                leaves[i - 1].get(Math.floorDiv(k, DecisionsOption.values().length)).remove(leaves[i].get(k));
                        }

                    }
                }
            }
        }*/

        for (int i = 1; i < leaves.length; i++) {
            if (i % 2 == 0) {
                for (int j = 0; j < leaves[i].size(); j += market.expectationMap.size()) {
                    for (int k = j + market.expectationMap.size() - 1; k > j; k--) {
                        if (leaves[i].get(j).toString().compareTo(leaves[i].get(k).toString()) == 0) {
                            leaves[i - 1].get(Math.floorDiv(j, market.expectationMap.size())).remove(leaves[i].get(j));
                            leaves1[i - 1].get(Math.floorDiv(j, market.expectationMap.size())).remove(leaves1[i].get(j));
                            states[i].remove(j);
                        }
                    }
                }
            } else {
                for (int j = 0; j < leaves[i].size(); j += DecisionsOption.values().length) {
                    for (int k = j + DecisionsOption.values().length - 1; k > j; k--) {
                        if (leaves[i].get(j).toString().compareTo(leaves[i].get(k).toString()) == 0) {
                            leaves[i - 1].get(Math.floorDiv(j, DecisionsOption.values().length)).remove(leaves[i].get(j));
                            leaves1[i - 1].get(Math.floorDiv(j, market.expectationMap.size())).remove(leaves1[i].get(j));
                            states[i].remove(j);
                        }
                    }
                }
            }
        }
        return root;
    }

    public List[] createLeaves() {
        leaves = new ArrayList[(time + 1) * 2];
        leaves1 = new ArrayList[(time + 1) * 2];
        states = new ArrayList[(time + 1) * 2];

        for (int j = 0; j < leaves.length; j++) {
            leaves[j] = new ArrayList<>();
            leaves1[j] = new ArrayList<>();
            states[j] = new ArrayList<>();
        }

        leaves[index1].add(new DefaultMutableTreeNode(stateList.get(specialIndex)));
        leaves1[index1].add(new Node<>(stateList.get(specialIndex)));
        states[index1].add(stateList.get(specialIndex));
        index1++;
        specialIndex++;
        validateTheLengthOfList();

        for (int i = 1; i <= 3; i++) {
            leaves[index1].add(new DefaultMutableTreeNode(stateList.get(specialIndex)));
            states[index1].add(stateList.get(specialIndex));
            leaves1[index1].add(new Node<>(stateList.get(specialIndex)));
            specialIndex++;
        }

        index1++;
        while (time > 0) {
            createLeavesIterator();
            time--;
        }
        return leaves;
    }

    public List[] createLeavesIterator() {
        for (int i = 0; i <= 1; i++) {
            int number = validateTheLengthOfList();
            while (leaves[index1].size() < number && specialIndex < stateList.size()) {
                leaves[index1].add(new DefaultMutableTreeNode(stateList.get(specialIndex)));
                leaves1[index1].add(new Node<>(stateList.get(specialIndex)));
                states[index1].add(stateList.get(specialIndex));
                specialIndex++;
            }
            index1++;
        }
        return leaves;
    }

    public int validateTheLengthOfList() {
        index *= (indexHelp % 2 != 0) ? DecisionsOption.values().length : market.expectationMap.size();
        indexHelp++;
        return index;
    }

//    public List[] clearDuplicate() {
//        for (int i = 1; i < states.length; i++) {
//            if (i % 2 == 0) {
//                for (int j = 0; j < states[i].size(); j += market.expectationMap.size()) {
//
//                }
//            }
//        }
//    }
//

    @Override
    public String toString() {
        for (int i = 0; i < states.length; i++) {
            for (int j = 0; j < states[i].size(); j++) {
                System.out.print(states[i].get(j));
            }
            System.out.println();
        }

        return null;
    }

    public List<State<Double, StateOfMarket<Integer, Integer, Double>>>[] getStates() {
        return states;
    }

    public Market getMarket() {
        return market;
    }

    public Node<State<Double, StateOfMarket<Integer, Integer, Double>>> getRoot1() {
        return root1;
    }

    public void setRoot1(Node<State<Double, StateOfMarket<Integer, Integer, Double>>> root1) {
        this.root1 = root1;
    }

    public List<Node<State<Double, StateOfMarket<Integer, Integer, Double>>>>[] getLeaves1() {
        return leaves1;
    }

    public void setLeaves1(List<Node<State<Double, StateOfMarket<Integer, Integer, Double>>>>[] leaves1) {
        this.leaves1 = leaves1;
    }
}
