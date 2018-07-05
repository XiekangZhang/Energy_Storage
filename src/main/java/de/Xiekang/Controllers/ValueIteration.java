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
    private State<Double, StateOfMarket<Integer, Integer, Double>>[][] path;
    private List<State<Double, StateOfMarket<Integer, Integer, Double>>> paths = new ArrayList<>();
    private List<Double> values = new ArrayList<>();
    private double value = 0;

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
        path = new State[defaultMutableTreeNode.getLeafCount()][defaultMutableTreeNode.getDepth() + 1];

        for (int i = 0; i < path.length; i++) {
            for (int j = 0; j < path[i].length; j++) {
                path[i][j] = new State<>();
            }
        }
        findTheDepthChildren(child);
        createPathArray();
//        findTheOptimalWithTime();
        findTheOptimal(1.7);
    }

    public void findTheDepthChildren(Node<State<Double, StateOfMarket<Integer, Integer, Double>>> child) {
        if (child.getChildren().isEmpty()) {
            iterateChildren(child);
//            System.out.println(child.getData().toString());
        } else {
            child.getChildren().forEach(c1 -> findTheDepthChildren(c1));
        }
    }

    public void iterateChildren(Node<State<Double, StateOfMarket<Integer, Integer, Double>>> child) {
//        System.out.println(child.getData().toString());
        paths.add(child.getData());
        if (child.getParent() != null) {
            child = child.getParent();
            iterateChildren(child);
        }
    }

    public void createPathArray(){
        for (int row = 0; row < path.length; row++) {
            for (int limit = 0; limit < path[row].length; limit++) {
                path[row][limit] = paths.get((row) * 4 + limit);
            }
        }
        calculateValueIteration();
    }

    public void calculateValueIteration() {
        for (int i = 0; i < path.length; i++) {
            value = 0;
            for (int j = 1; j < path[i].length; j++) {
                value += (path[i][j].getV1() - path[i][j-1].getV1()) * path[i][j].getV2().getPrice() * path[i][j].getV2().getExpectation();
            }
            values.add(value);
        }
    }

    public void findTheOptimalWithTime() {
        value = Collections.max(values);
        int index = values.indexOf(value);
        for (int j = 0; j < path[index].length; j++) {
            System.out.print(path[index][j] + " <- ");
        }
        System.out.print(value);
    }

    public void findTheOptimal(double profit) {
        List<Double> v1 = new ArrayList<>();
        for (double v : values) {
            v1.add(Math.abs((profit - v) / v));
        }

        value = Collections.min(v1);
        int index = v1.indexOf(value);
        for (int j = 0; j < path[index].length; j++) {
            System.out.print(path[index][j] + " <- ");
        }
        System.out.print(profit);
    }


    @Override
    public String toString() {
        for (int i = 0; i < path.length; i++) {
            System.out.print(i + " ");
            for (int j= 0; j < path[i].length; j++) {
                System.out.print(path[i][j] + " ");
            }
            System.out.println();
        }
        values.forEach(v -> System.out.println(v));
//        paths.forEach(stateNode -> System.out.println(stateNode));
//        System.out.println(paths.size());
        return "calculating...";
    }
}
