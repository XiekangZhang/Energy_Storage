package main.java.de.Xiekang.Models;

public class Battery {

    private Integer capacity;
    private Double efficiency;
    private Integer outputLimitation;
    private Integer inputLimitation;

    public Battery(Integer capacity, Double efficiency, Integer outputLimitation, Integer inputLimitation) {
        this.capacity = capacity;
        this.efficiency = efficiency;
        this.inputLimitation = inputLimitation;
        this.outputLimitation = outputLimitation;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Double getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(Double efficiency) {
        this.efficiency = efficiency;
    }

    public Integer getOutputLimitation() {
        return outputLimitation;
    }

    public void setOutputLimitation(Integer outputLimitation) {
        this.outputLimitation = outputLimitation;
    }

    public Integer getInputLimitation() {
        return inputLimitation;
    }

    public void setInputLimitation(Integer inputLimitation) {
        this.inputLimitation = inputLimitation;
    }
}
