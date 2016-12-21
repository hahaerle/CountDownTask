package com.shuai.countdown;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * Created by shangerle on 16/12/21.
 */

public class TimeHelper {
    private Timer timer;
    private CountDownSession countDownSession;
    private TimeHelper(){
        countDownSession=new CountDownSession();
    }
    static TimeHelper instances;
    public static void init(){
        if(instances==null){
            instances=new TimeHelper();
        }
    }
    public static TimeHelper getInstances(){
        if(instances==null){
            instances=new TimeHelper();
        }
        return instances;
    }
    int durationCount=0;
    public void startTimer(){
        if(timer!=null){
            timer.cancel();
        }
        if(countDownSession!=null) {
            countDownSession.clear();
        }

        timer=new Timer();
        durationCount=0;
        timer.schedule(new MyTask(), 0,TimeUnit.SECONDS.toMillis(1));
    }
    public void stopTimer(){
        if(timer!=null){
            timer.cancel();
            timer=null;
        }
        if(countDownSession!=null){
            countDownSession.clear();
        }
    }
    public static void release(){
        if(instances!=null){
            instances.clear();
            instances=null;
        }
    }

    private void clear() {
        stopTimer();
        countDownSession=null;
    }


    /**
     * 加入任务
     * @param taskId 任务id
     * @param iCountDownTask
     * @param duration
     */
    public void putTask(String taskId, ICountDownTask iCountDownTask, long duration) {
        countDownSession.addTask(taskId,iCountDownTask,duration);
    }
    public void removeTask(String taskId){
        countDownSession.removeTask(taskId);
    }
    private class MyTask extends TimerTask {
        @Override
        public void run() {
            countDownSession.notifyTask(durationCount++);
        }
    }
}
