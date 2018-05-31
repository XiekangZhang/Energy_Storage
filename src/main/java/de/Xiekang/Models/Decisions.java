package main.java.de.Xiekang.Models;

/**
 * This class describes the decisions.
 * At this moment there is just three decisions, either buy one the energy or sell one energy or stay constant.
 */
public class Decisions extends Battery {

    public Decisions() {
        super(3, 2);
    }

    public double decide(DecisionsOption option) {
        if (super.getActualNumber() > 0 && super.getActualNumber() < super.getCapacity()) {
            switch (option) {
                case Buy: {
                    super.setActualNumber(super.getActualNumber() + super.getInputLimitation() * super.getEfficiency());
                    break;
                }
                case Sell: {
                    super.setActualNumber(super.getActualNumber() - super.getOutputLimitation() * super.getEfficiency());
                    break;
                }
                case Stay: {
                    super.setActualNumber(super.getActualNumber() * super.getEfficiency());
                    break;
                }
                default:
                    System.out.println("Please choose either buy or sell or stay!");
            }
        } else
            System.out.println("It is impossible to buy or sell energy!");
        System.out.println("The new number of energy is " + super.getActualNumber() + " .");
        return super.getActualNumber();
    }
}
