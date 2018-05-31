package test.java.de.Xiekang.Controllers;

import main.java.de.Xiekang.Controllers.DecisionTree;
import main.java.de.Xiekang.Models.Battery;
import main.java.de.Xiekang.Models.Decisions;
import main.java.de.Xiekang.Models.Market;
import org.junit.Test;

import static org.junit.Assert.*;

public class DecisionTreeTest {

    @Test
    public void createTreeTest() {
        Market market = new Market(1, 2);
        market.createExpectation(market.getStartsPrice(), market.getEndsPrice());
        Decisions decisions = new Decisions();
        DecisionTree tree = new DecisionTree();
        tree.createState(market, decisions, 0);

    }

}