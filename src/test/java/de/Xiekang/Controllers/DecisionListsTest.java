package test.java.de.Xiekang.Controllers;

import main.java.de.Xiekang.Controllers.DecisionLists;
import main.java.de.Xiekang.Controllers.State;
import main.java.de.Xiekang.Models.Decisions;
import main.java.de.Xiekang.Models.Market;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class DecisionListsTest {

    Market market = new Market(1, 2);
    Decisions decisions = new Decisions();
    DecisionLists decisionLists = new DecisionLists();

    @Test
    public void createDecision() {
    }

    @Test
    public void createLists() {
        market.createExpectation(market.getStartsPrice(),market.getEndsPrice());
        System.out.println(decisionLists.createLists(market, 0)+" lists are needed!");
    }

    @Test
    public void createStateBeforeDecision() {
        market.createExpectation(market.getStartsPrice(),market.getEndsPrice());
        decisionLists.createStateBeforeDecision(market, decisions, 0);
        decisionLists.toString();
    }
}