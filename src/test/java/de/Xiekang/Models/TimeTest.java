package test.java.de.Xiekang.Models;

import main.java.de.Xiekang.Models.Time;
import org.junit.Test;

import static org.junit.Assert.*;

public class TimeTest {

    @Test
    public void timeCalulation() {
        Time time = new Time(2, "13:00");
        assertEquals(12, time.TimeCalculation());
    }
}