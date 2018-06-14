package main.java.de.Xiekang.Models;

public class Time {
    private int day;
    private int workingTime;
    private TimeIntervalOption option;

    public Time() {
    }

    public Time(int day, int workingTime, TimeIntervalOption option) {
        this.day = day;
        this.workingTime = workingTime;
        this.option = option;
    }

    public int TimeCalculation() {
        int time = (this.getDay() - 1) * TimeIntervalOption.changeOptionToInt(this.getOption());

        switch (this.getOption()) {
            case One_Hour: {
                int workingHour = 0;
                for (int i = 9; i < this.getWorkingTime(); i++) {
                    workingHour++;
                }
                time += workingHour;
                break;
            }
            case Two_Hour: {
                int workingHour = 0;
                for (int i = 9; i < this.getWorkingTime(); i += 2) {
                    workingHour++;
                }
                time += workingHour;
                break;
            }
            case Four_Hour: {
                int workingHour = 0;
                for (int i = 9; i < this.getWorkingTime(); i += 4) {
                    workingHour++;
                }
                time += workingHour;
                break;
            }
            case Eight_Hour: {
                int workingHour = 0;
                for (int i = 9; i < this.getWorkingTime(); i += 8) {
                    workingHour++;
                }
                time += workingHour;
                break;
            }
            default: System.out.println("No one is working");
        }
        return time;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getWorkingTime() {
        return workingTime;
    }

    public void setWorkingTime(int workingTime) {
        this.workingTime = workingTime;
    }

    public TimeIntervalOption getOption() {
        return option;
    }

    public void setOption(TimeIntervalOption option) {
        this.option = option;
    }
}
