package test.java.de.Xiekang.Controllers;

import main.java.de.Xiekang.Controllers.ValueIteration;
import org.junit.Test;

import static org.junit.Assert.*;

public class ValueIterationTest {

    @Test
    public void calculateValue() {
        ValueIteration valueIteration = new ValueIteration();
        valueIteration.calculateValue();
        valueIteration.toString();
    }
}