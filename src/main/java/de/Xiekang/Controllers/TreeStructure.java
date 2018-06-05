package main.java.de.Xiekang.Controllers;

import main.java.de.Xiekang.Models.Battery;
import main.java.de.Xiekang.Models.DecisionsOption;
import main.java.de.Xiekang.Models.Market;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import java.util.ArrayList;
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
    private boolean decision = false;

    public TreeStructure() {
        time = 2;
        market = new Market(1, 2);
        market.createExpectation(market.getStartsPrice(), market.getEndsPrice());
        battery = new Battery(3, 2);
        StateGenerator stateGenerator = new StateGenerator();
        stateList = stateGenerator.createState(market, battery, time);
        createLeaves(time);
    }

    public TreeNode createTree() {
        root = new DefaultMutableTreeNode(leaves[0].get(0));
        for (int i = 0; i < 3; i++) {
            root.add(leaves[1].get(i));
        }
        int k = 0;
        int p = 0;
        int z = 2;

        for (int i = 1; i < leaves.length; i++) {

            for (int j = 0; j < leaves[i].size(); j++) {

                if (i % 2 != 0 && i < leaves.length - 1) {
                    leaves[i].get(j).add(new DefaultMutableTreeNode(leaves[z].get(k)));
                    leaves[i].get(j).add(new DefaultMutableTreeNode(leaves[z].get(k + 1)));
                    k += 2;
                }

                if (i % 2 == 0 && i < leaves.length){

                    leaves[i].get(j).add(new DefaultMutableTreeNode(leaves[z + 1].get(p)));
                    //System.out.println(z + 1 + " " + p + " " + leaves[z + 1].get(p));
                    leaves[i].get(j).add(new DefaultMutableTreeNode(leaves[z + 1].get(p + 1)));
                    //System.out.println(z + 1 + " " + (p + 1) + " " + leaves[z + 1].get(p + 1));
                    leaves[i].get(j).add(new DefaultMutableTreeNode(leaves[z + 1].get(p + 2)));
                    //System.out.println(z + 1 + " " + p + 2 + " " + leaves[z + 1].get(p + 2));
                    p += 3;
                }

            }
        }
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
        if (time != 0) {
            createLeavesIterator();
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
        for (int i = 0; i < leaves.length; i++) {
            leaves[i].forEach(node -> System.out.print(node));
            System.out.println();
        }
        return super.toString();
    }
}
