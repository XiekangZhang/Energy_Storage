package test.java.de.Xiekang.Models;

import main.java.de.Xiekang.Models.Battery;
import main.java.de.Xiekang.Models.Decisions;
import main.java.de.Xiekang.Models.DecisionsOption;
import org.junit.Test;

import static org.junit.Assert.*;

public class BatteryTest {
    @Test
    public void buyEnergy() {
        Battery battery = new Battery(3,2);
        Decisions decisions = new Decisions();
        assertEquals(battery.getActualNumber() + 1, decisions.decide(DecisionsOption.Buy), 0.00001);
    }

    @Test
    public void sellEnergy() {
        Battery battery = new Battery(3, 2);
        Decisions decisions = new Decisions();
        assertEquals(battery.getActualNumber() - 1, decisions.decide(DecisionsOption.Sell), 0.00001);
    }

    @Test
    public void stayEnergy() {
        Battery battery = new Battery(3, 2);
        Decisions decisions = new Decisions();
        assertEquals(battery.getActualNumber(), decisions.decide(DecisionsOption.Stay), 0.00001);
    }
}