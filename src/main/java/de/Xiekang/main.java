package main.java.de.Xiekang;

import main.java.de.Xiekang.Controllers.TreeStructure;
import main.java.de.Xiekang.Controllers.ValueIteration;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.List;

public class main {

    public static void main(String[] args) {
//        TreeStructure treeStructure = new TreeStructure();
//        //treeStructure.appendTreeStructure();
//        treeStructure.createTreeStructure();
//        // Der Wurzelknoten wird hergestellt
//        TreeNode root = (treeStructure.createTree());
//        //TreeNode root = treeTest();
//
//        // Der Wurzelknoten wird dem neuen JTree im Konstruktor übergeben
//        JTree tree = new JTree( root );
//
//        // Ein Frame herstellen, um den Tree auch anzuzeigen
//        JFrame frame = new JFrame( "Energy-Storage" );
//        frame.add( new JScrollPane( tree ));
//
//        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
//        frame.pack();
//        frame.setLocationRelativeTo( null );
//        frame.setVisible( true );
        ValueIteration valueIteration = new ValueIteration();
        valueIteration.showTreeStructure();
    }
}

