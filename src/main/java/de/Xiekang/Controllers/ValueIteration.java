package main.java.de.Xiekang.Controllers;

import main.java.de.Xiekang.Models.DecisionsOption;
import main.java.de.Xiekang.Models.Market;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import java.util.*;

import static main.java.de.Xiekang.Controllers.Node.printTree;

/**
 * TODO
 * - implement value iteration algorithm
 * - Laufzeit testen
 * - Such mal im Internet
 * - Value will be calculated in this method
 */
public class ValueIteration extends TreeStructure {

    private DefaultMutableTreeNode defaultMutableTreeNode;
    private TreeStructure treeStructure;
    private Node<State<Double, StateOfMarket<Integer, Integer, Double>>> child;
    private List<State<Double, String>> path = new ArrayList<>();
    private List<Double> values = new ArrayList<>();
    private double value= 0;

    public DefaultMutableTreeNode init() {
        treeStructure = new TreeStructure();
        treeStructure.createLeaves();
        defaultMutableTreeNode = treeStructure.createTree();
        printTree(treeStructure.root1, " ");
        return defaultMutableTreeNode;
    }

    public void showTreeStructure() {
        JTree tree = new JTree( defaultMutableTreeNode );

        // Ein Frame herstellen, um den Tree auch anzuzeigen
        JFrame frame = new JFrame( "Energy-Storage" );
        frame.add( new JScrollPane( tree ));

        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.pack();
        frame.setLocationRelativeTo( null );
        frame.setVisible( true );
    }

    public ValueIteration() {
        init();
        child = treeStructure.getRoot1();

        findTheDepthChildren(child);
        this.toString();
    }

    public void findTheDepthChildren(Node<State<Double, StateOfMarket<Integer, Integer, Double>>> child) {
        if (!child.getChildren().isEmpty() && child.getChildren().get(0).getChildren().isEmpty()) {
            calculateValueIteration(child);
        } else {
            child.getChildren().forEach(c1 -> findTheDepthChildren(c1));
        }
    }

    public void calculateValueIteration(Node<State<Double, StateOfMarket<Integer, Integer, Double>>> child) {

        while (child.getParent() != null) {
            for (int i = 0; i < child.getChildren().size(); i++) {
                path.add(new State<>(value, child.getChildren().get(i).getData().toString()));
                values.add((child.getData().getV1() - child.getChildren().get(i).getData().getV1()) *
                        child.getData().getV2().getExpectation() *
                        child.getData().getV2().getPrice());
            }
            value = Collections.max(values);
            values.clear();

            child = child.getParent();
            calculateValueIteration(child);
        }
    }


    public void printTest(Node<State<Double, StateOfMarket<Integer, Integer, Double>>> child) {
        System.out.println(child.getData().toString());
    }

    @Override
    public String toString() {
        path.forEach(state -> System.out.println(state.toString()));
        System.out.println(path.size());
        return "calculating...";
    }
}
