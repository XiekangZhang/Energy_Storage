package main.java.de.Xiekang;

import java.util.Hashtable;

public class main {

    public static void main(String[] args) {
        Hashtable test = new Hashtable();
        test.put(1, 3);
        test.put(2, 4);
        for (Object i : test.keySet()) {
            System.out.println("The key is " + i + ". The value is " + test.get(i) + ". ");
        }

    }
}
