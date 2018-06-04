package main.java.de.Xiekang.Controllers;

import main.java.de.Xiekang.Models.DecisionsOption;
import main.java.de.Xiekang.Models.Market;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.List;

public class TreeStructure {

    private DefaultMutableTreeNode root;
    private DefaultMutableTreeNode[] afterDecisionLeaves;
    private DefaultMutableTreeNode[] afterTimeLeaves;
    private List<State<Double, State<Double, Integer>>> stateList = new ArrayList<>();
    private State<Double, State<Double, Integer>> state;
    private boolean decisionFlag = true;
    private int indexOfDecisionLeaves = 0;
    private int indexOfTimeLeaves = 0;

    public TreeStructure() {

    }




    public TreeNode createTree(Market market, int time) {

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

    public int createNumberOfAfterDecisionLeaves(Market market, int time) {
        int sum = 0;
        while (time >= 0) {
            sum += (int) Math.pow(market.expectationMap.size() * DecisionsOption.values().length, time) * (DecisionsOption.values().length);
        }
        return sum;
    }

}
