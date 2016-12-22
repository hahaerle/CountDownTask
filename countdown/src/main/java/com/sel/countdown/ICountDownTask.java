package com.sel.countdown;

import android.os.Looper;
import android.os.Message;

import java.util.concurrent.TimeUnit;

/**
 * Created by shangerle on 16/12/7.
 */
public abstract class ICountDownTask {
    private static final int WHAT_COUNT_DOWN = 10001;

    public ICountDownTask(long period) {
        this.period=period;
    }
    public ICountDownTask() {
    }

    public abstract void onRefresh();
    public abstract void onFinished();
    public abstract void onCancle();
    long period= TimeUnit.SECONDS.toSeconds(1);

    WeakRefHandler<ICountDownTask> handler=new UIHandler(this);
    public static class UIHandler extends WeakRefHandler<ICountDownTask>{

        public UIHandler(ICountDownTask iCountDownTask) {
            super(iCountDownTask, Looper.getMainLooper());
        }

        @Override
        protected void handleMessage(ICountDownTask iCountDownTask, Message msg) {
            if(msg.what==WHAT_COUNT_DOWN) {
                iCountDownTask.onRefresh();
            }
        }
    }
    public void countDown() {
        handler.sendEmptyMessage(WHAT_COUNT_DOWN);
    }
}
