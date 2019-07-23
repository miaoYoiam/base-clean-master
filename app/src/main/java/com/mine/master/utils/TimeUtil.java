package com.mine.master.utils;

public class TimeUtil {
    private static volatile TimeUtil Instance = null;

    public static TimeUtil getInstance() {
        TimeUtil instance = Instance;
        if (instance == null) {
            synchronized (TimeUtil.class) {
                instance = Instance;
                if (instance == null) {
                    Instance = instance = new TimeUtil();
                }
            }
        }
        return instance;
    }

    private long startTime, endTime;

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public long calculaTeTime() {
        return endTime - startTime;
    }
}
