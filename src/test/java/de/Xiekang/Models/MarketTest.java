package test.java.de.Xiekang.Models;

import main.java.de.Xiekang.Models.Market;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class MarketTest {

    @Test
    public void createExpectation() {
        Map<Integer, Double> map = new HashMap<>();
        Map<Integer, Double> actual = new HashMap<>();
        Market market = new Market(1, 5);
        map.put(1, 0.2);
        map.put(2, 0.2);
        map.put(3, 0.2);
        map.put(4, 0.2);
        map.put(5, 0.2);
        actual = market.createExpectation(market.getStartsPrice(), market.getEndsPrice());

        assertEquals(map, actual);
    }
}