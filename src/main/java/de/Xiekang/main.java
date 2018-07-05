package main.java.de.Xiekang;

import main.java.de.Xiekang.Controllers.Node;
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

    private static Node<String> creatTree() {
        Node<String> root = new Node<>("root");
        Node<String> node1 = root.addChild(new Node<String>("node 1"));

        Node<String> node11 = node1.addChild(new Node<String>("node 11"));
        Node<String> node111 = node11.addChild(new Node<String>("node 111"));
        Node<String> node112 = node11.addChild(new Node<String>("node 112"));

        Node<String> node12 = node1.addChild(new Node<String>("node 12"));

        Node<String> node2 = root.addChild(new Node<String>("node 2"));

        Node<String> node21 = node2.addChild(new Node<String>("node 21"));
        Node<String> node211 = node2.addChild(new Node<String>("node 22"));
        return root;
    }

    private static <T> void printTree(Node<T> node, String appender) {
        System.out.println(appender + node.getData());
        node.getChildren().forEach(each -> printTree(each, appender + appender));
    }
}

