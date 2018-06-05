package main.java.de.Xiekang;

import main.java.de.Xiekang.Controllers.TreeStructure;

import javax.swing.*;
import javax.swing.tree.TreeNode;

public class main {

    public static void main(String[] args) {
        TreeStructure treeStructure = new TreeStructure();

        // Der Wurzelknoten wird hergestellt
        TreeNode root = (treeStructure.createTree());

        // Der Wurzelknoten wird dem neuen JTree im Konstruktor übergeben
        JTree tree = new JTree( root );

        // Ein Frame herstellen, um den Tree auch anzuzeigen
        JFrame frame = new JFrame( "Energy-Storage" );
        frame.add( new JScrollPane( tree ));

        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        frame.pack();
        frame.setLocationRelativeTo( null );
        frame.setVisible( true );
    }

//    public static TreeNode treeTest() {
//        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Layer 1");
//        DefaultMutableTreeNode leaves = new DefaultMutableTreeNode("Layer 2");
//        DefaultMutableTreeNode leaves1 = new DefaultMutableTreeNode("Layer 3");
//        DefaultMutableTreeNode leaves2 = new DefaultMutableTreeNode("Layer 4");
//
//        root.add(leaves);
//        leaves.add(leaves1);
//        leaves1.add(leaves2);
//
//        return root;
//    }
}

