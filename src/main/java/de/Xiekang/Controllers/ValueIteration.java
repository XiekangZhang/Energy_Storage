package main.java.de.Xiekang.Controllers;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

/**
 * TODO
 * - implement value iteration algorithm
 * - Laufzeit testen
 * - Such mal im Internet
 * - Value will be calculated in this method
 */
public class ValueIteration extends TreeStructure {

    private double values;
    private HashMap<Double, State<Double, StateOfMarket<Integer, Integer, Double>>> path = new HashMap<>();
    private DefaultMutableTreeNode defaultMutableTreeNode;
    private TreeStructure treeStructure;

    public DefaultMutableTreeNode init() {
        treeStructure = new TreeStructure();
        treeStructure.createLeaves();
        defaultMutableTreeNode = treeStructure.createTree();
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
    }

    public HashMap calculateValue() {
        //initialize
        values = 0;
        path.put(values, treeStructure.getStates()[0].get(0));

        //first iteration
        return path;
    }

    public String toString() {
        for (State state : path.values()) {
            System.out.print(state);
        }
        return null;
    }
}
