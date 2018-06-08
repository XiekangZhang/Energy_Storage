package main.java.de.Xiekang.Controllers;

import main.java.de.Xiekang.Models.Battery;
import main.java.de.Xiekang.Models.DecisionsOption;
import main.java.de.Xiekang.Models.Market;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class TreeStructure {

    private DefaultMutableTreeNode root;
    private List<DefaultMutableTreeNode>[] leaves;
    private List stateList = new ArrayList();
    private int specialIndex = 0;
    private int index = 1;
    private int index1 = 0;
    private int indexHelp = 1;
    private Market market;
    private Battery battery;
    private int time;

    public TreeStructure() {
        time = 2;
        market = new Market(1, 2, 1);
        market.createExpectation(market.getStartsPrice(), market.getEndsPrice());
        battery = new Battery(3, 2);
        StateGenerator stateGenerator = new StateGenerator();
        stateList = stateGenerator.createState(market, battery, time);
        createLeaves(time);
    }

    public TreeNode createTree() {
        for (int i = 0; i < leaves.length - 1; i++) {
            if (i % 2 == 0) {
                int d1 = 0;
                for (int j = 0; j < leaves[i].size(); j++) {
                    if (d1 < leaves[i + 1].size()) {
                        for (int d = 0; d < DecisionsOption.values().length; d++) {
                            leaves[i].get(j).add(leaves[i + 1].get(d + d1));
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
                        }
                        p1 += market.expectationMap.size();
                    }
                }
            }
        }
        root = leaves[0].get(0);
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
                        }
                    }
                }
            } else {
                for (int j = 0; j < leaves[i].size(); j += DecisionsOption.values().length) {
                    for (int k = j + DecisionsOption.values().length - 1; k > j; k--) {
                        if (leaves[i].get(j).toString().compareTo(leaves[i].get(k).toString()) == 0) {
                            leaves[i - 1].get(Math.floorDiv(j, DecisionsOption.values().length)).remove(leaves[i].get(j));
                        }
                    }
                }
            }
        }
        root = leaves[0].get(0);
        return root;
    }

    public List[] createLeaves(int time) {
        leaves = new ArrayList[(time + 1) * 2];

        for (int j = 0; j < leaves.length; j++) {
            leaves[j] = new ArrayList<>();
        }

        leaves[index1].add(new DefaultMutableTreeNode(stateList.get(specialIndex++)));
        index1++;
        validateTheLengthOfList();

        for (int i = 1; i <= 3; i++) {
            leaves[index1].add(new DefaultMutableTreeNode(stateList.get(specialIndex++)));
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
            do {
                leaves[index1].add(new DefaultMutableTreeNode(stateList.get(specialIndex++)));
            } while (leaves[index1].size() < number);
            index1++;
        }
        return leaves;
    }

    public int validateTheLengthOfList() {
        index *= (indexHelp % 2 != 0) ? DecisionsOption.values().length : market.expectationMap.size();
        indexHelp++;
        return index;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
