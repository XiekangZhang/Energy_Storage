package main.java.de.Xiekang.Models;

public class Battery {

    protected double capacity;
    protected double actualNumber;
    protected double efficiency;
    protected double outputLimitation;
    protected double inputLimitation;

    public Battery() {}

    public Battery(double capacity, double actualNumber) {
        this(capacity, 1, 1, 1, actualNumber);
    }

    /**
     * The constructor of class battery
     * @param capacity depicts the capacity of a battery
     * @param efficiency shows the efficiency of a battery
     * @param outputLimitation constraints the available sellable amount to the market
     * @param inputLimitation constraints the buyable amount from the market
     * @param actualNumber depicts the actual number of energy at a time point
     */
    public Battery(double capacity, double efficiency, double outputLimitation, double inputLimitation, double actualNumber) {
        this.capacity = capacity;
        this.efficiency = efficiency;
        this.inputLimitation = inputLimitation;
        this.outputLimitation = outputLimitation;
        this.actualNumber = actualNumber;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public double getActualNumber() {
        return actualNumber;
    }

    public void setActualNumber(double actualNumber) {
        this.actualNumber = actualNumber;
    }

    public double getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(double efficiency) {
        this.efficiency = efficiency;
    }

    public double getOutputLimitation() {
        return outputLimitation;
    }

    public void setOutputLimitation(double outputLimitation) {
        this.outputLimitation = outputLimitation;
    }

    public double getInputLimitation() {
        return inputLimitation;
    }

    public void setInputLimitation(double inputLimitation) {
        this.inputLimitation = inputLimitation;
    }
}
