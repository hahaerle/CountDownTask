package com.sel.countdown;

import java.io.Serializable;

/**
 * Created by shangerle on 16/12/21.
 */
public class CountDownObject implements Serializable{
    private long period;
    private long duration;

    /**
     *
     * @param period 秒
     * @param duration 秒
     */
    public CountDownObject(long period,long duration) {
        this.period = period;
        this.duration=duration;
    }

    public long getPeriod() {
        return period;
    }

    public long getDuration() {
        return duration;
    }
}
