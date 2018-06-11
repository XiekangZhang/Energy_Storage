package test.java.de.Xiekang.Controllers;

import main.java.de.Xiekang.Controllers.StateGenerator;
import main.java.de.Xiekang.Controllers.TreeStructure;
import main.java.de.Xiekang.Models.Battery;
import main.java.de.Xiekang.Models.Market;
import main.java.de.Xiekang.Models.Time;
import org.junit.Test;

public class StateGeneratorTest {
  @Test
  public void generateInitialState() {
      StateGenerator stateGenerator = new StateGenerator();
      Market market = new Market(1, 2, 1);
      market.createExpectation(market.getStartsPrice(), market.getEndsPrice());
      Battery battery = new Battery(3, 2);
      Time newTime = new Time(1, "12:00");
      stateGenerator.createStates(market, battery, newTime.TimeCalculation());
      stateGenerator.toString();
  }

  @Test
    public void createTreeTest() {
      TreeStructure treeStructure = new TreeStructure();
      treeStructure.createTree();
      treeStructure.toString();
  }

}