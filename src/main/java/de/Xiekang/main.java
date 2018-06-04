package main.java.de.Xiekang;

import main.java.de.Xiekang.Controllers.TreeStructure;
import main.java.de.Xiekang.Models.Market;

import javax.swing.*;
import javax.swing.tree.TreeNode;

public class main {

    public static void main(String[] args) {
        Market market = new Market(1, 2);
        market.createExpectation(market.getStartsPrice(), market.getEndsPrice());

        // Der Wurzelknoten wird hergestellt
        TreeStructure treeStructure = new TreeStructure();
        //TreeNode root = (treeStructure.createTree(market, decisions, 1));

        // Der Wurzelknoten wird dem neuen JTree im Konstruktor übergeben
        //JTree tree = new JTree( root );

        // Ein Frame herstellen, um den Tree auch anzuzeigen
        JFrame frame = new JFrame( "Energy-Storage" );
        //frame.add( new JScrollPane( tree ));

        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.pack();
        frame.setLocationRelativeTo( null );
        frame.setVisible( true );
    }
}

