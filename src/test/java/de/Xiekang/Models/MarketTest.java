package test.java.de.Xiekang.Models;

import main.java.de.Xiekang.Models.Market;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class MarketTest {

    @Test
    public void createExpectation() {
        Map<Integer, Map<Integer, Double>> map = new HashMap<>();
        Map<Integer, Map<Integer, Double>> actual = new HashMap<>();
        Map<Integer, Double> mapContents = new HashMap<>();
        Market market = new Market(1, 5);
        mapContents.put(1, 0.04);
        mapContents.put(2, 0.04);
        mapContents.put(3, 0.04);
        mapContents.put(4, 0.04);
        mapContents.put(5, 0.04);
        map.put(1, mapContents);
        map.put(2, mapContents);
        map.put(3, mapContents);
        map.put(4, mapContents);
        map.put(5, mapContents);
        actual = market.createExpectation(market.getStartsPrice(), market.getEndsPrice());

        assertEquals(map, actual);
    }
}