package main.java.de.Xiekang.Models;

/**
 * TODO
 * - How a real battery looks
 * - optional: consider the efficiency after a certain time
 */
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
