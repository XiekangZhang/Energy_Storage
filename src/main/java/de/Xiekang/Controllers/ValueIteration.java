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
    private Node<State<Double, StateOfMarket<Integer, Integer, Double>>> node;

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
    }

    public void calculateValue() {
        //n : 0..3
        int n = defaultMutableTreeNode.getDepth();

        //the number of paths
        List<Double> values = new ArrayList<>();
        double value = 0;

        //initialize

    }

}
