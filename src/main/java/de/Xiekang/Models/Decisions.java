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
        switch (option) {
            case Buy: {
                if (super.actualNumber < super.getCapacity())
                    super.setActualNumber(super.getActualNumber() + super.getInputLimitation() * super.getEfficiency());
                System.out.println("The new number of energy after buying is " + super.getActualNumber() + " .");
                break;
            }
            case Sell: {
                if (super.actualNumber > 0)
                    super.setActualNumber(super.getActualNumber() - super.getOutputLimitation() * super.getEfficiency());
                System.out.println("The new number of energy after selling is " + super.getActualNumber() + " .");
                break;
            }
            case Stay: {
                super.setActualNumber(super.getActualNumber() * super.getEfficiency());
                System.out.println("The new number of energy after staying is " + super.getActualNumber() + " .");
                break;
            }
            default:
                System.out.println("Please choose either buy or sell or stay!");
        }
        return super.getActualNumber();
    }
}
