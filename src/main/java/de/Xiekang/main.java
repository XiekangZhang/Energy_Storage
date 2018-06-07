package main.java.de.Xiekang;

import main.java.de.Xiekang.Controllers.TreeStructure;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.List;

public class main {

    public static void main(String[] args) {
        TreeStructure treeStructure = new TreeStructure();

        // Der Wurzelknoten wird hergestellt
        TreeNode root = (treeStructure.createTree());
        //TreeNode root = treeTest();

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

    public static TreeNode treeTest() {
        List<DefaultMutableTreeNode> leavesArray = new ArrayList<>();
        for (int i = 0; i <= 3; i++) {
            leavesArray.add(new DefaultMutableTreeNode("Layer " + (i + 1)));
        }
        for (int i = 0; i < leavesArray.size() - 1; i++) {
            leavesArray.get(i).add(leavesArray.get(i + 1));
        }

        return leavesArray.get(0);
    }
}

