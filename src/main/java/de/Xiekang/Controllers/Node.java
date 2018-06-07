package main.java.de.Xiekang.Controllers;


import javax.swing.tree.DefaultMutableTreeNode;
import java.util.Comparator;

@Deprecated
class Node extends DefaultMutableTreeNode {

    public Node() {
        super();
    }

    public Node(Object newChild) {
        super(newChild);
    }


    public void insert(DefaultMutableTreeNode newChild, int childIndex) {
        super.insert(newChild, childIndex);
    }

    protected static Comparator nodeComparator = new Comparator() {
        @Override
        public int compare(Object o1, Object o2) {
            return o1.toString().compareToIgnoreCase(o2.toString());
        }

        @Override
        public boolean equals(Object obj) {
            return false;
        }
    };

    @Override
    public String toString() {
        return super.toString();
    }
}
