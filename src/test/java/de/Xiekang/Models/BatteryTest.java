package test.java.de.Xiekang.Models;

import main.java.de.Xiekang.Models.Battery;
import main.java.de.Xiekang.Models.Decisions;
import org.junit.Test;

import static org.junit.Assert.*;

public class BatteryTest {
    @Test
    public void buyEnergy() {
        Battery battery = new Battery(3,2);
        Decisions decisions = new Decisions();
        assertEquals(battery.getActualNumber() + 1, decisions.decide(Decisions.Calculation.Buy), 0.00001);
    }

    @Test
    public void sellEnergy() {
        Battery battery = new Battery(3, 2);
        Decisions decisions = new Decisions();
        assertEquals(battery.getActualNumber() - 1, decisions.decide(Decisions.Calculation.Sell), 0.00001);
    }

    @Test
    public void stayEnergy() {
        Battery battery = new Battery(3, 2);
        Decisions decisions = new Decisions();
        assertEquals(battery.getActualNumber(), decisions.decide(Decisions.Calculation.Stay), 0.00001);
    }
}