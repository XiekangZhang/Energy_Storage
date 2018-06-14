package test.java.de.Xiekang.Models;

import main.java.de.Xiekang.Models.Time;
import main.java.de.Xiekang.Models.TimeIntervalOption;
import org.junit.Test;

import static org.junit.Assert.*;

public class TimeTest {

    @Test
    public void timeCalculation() {
        TimeIntervalOption option = TimeIntervalOption.Two_Hour;
        int workingTime = 13;
        Time time = new Time(1, workingTime, option);
        assertEquals(2, time.TimeCalculation());
    }
}