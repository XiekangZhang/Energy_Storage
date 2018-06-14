package main.java.de.Xiekang.Models;

public enum TimeIntervalOption {
    One_Hour, Two_Hour, Four_Hour, Eight_Hour;

    public static int changeOptionToInt(TimeIntervalOption option) {
        switch (option) {
            case One_Hour: {
                return 8;
            }
            case Two_Hour: {
                return 4;
            }
            case Four_Hour: {
                return 2;
            }
            case Eight_Hour: {
                return 1;
            }
        }
        return -1;
    }
}