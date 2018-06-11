package main.java.de.Xiekang.Models;

public class Time {
    private int day;
    private String timeInterval;

    public Time() {
    }

    public Time(int day, String timeInterval) {
        this.day = day;
        this.timeInterval = timeInterval;
    }

    public int TimeCalculation() {
        int time = (this.getDay() - 1) * 8;

        switch (this.getTimeInterval()) {
            case "10:00": {
                time += 1;
                break;
            }
            case "11:00": {
                time += 2;
                break;
            }
            case "12:00": {
                time += 3;
                break;
            }
            case "13:00": {
                time += 4;
                break;
            }
            case "14:00": {
                time += 5;
                break;
            }
            case "15:00": {
                time += 6;
                break;
            }
            case "16:00": {
                time += 7;
                break;
            }
            case "17:00": {
                time += 8;
                break;
            }
        }

        return time;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getTimeInterval() {
        return timeInterval;
    }

    public void setTimeInterval(String timeInterval) {
        this.timeInterval = timeInterval;
    }
}
