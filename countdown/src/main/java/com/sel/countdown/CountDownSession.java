package com.sel.countdown;

import android.os.Bundle;
import android.os.Message;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import com.sel.countdown.WeakRefHandler;

/**
 * Created by shangerle on 16/12/7.
 */

public class CountDownSession implements Serializable {

    public void clear(){
        list.clear();
    }

    private static final int WHAT_AUTO_REMOVE_TASK = 1001 ;
    private ConcurrentHashMap<String,ICountDownTask> list=new ConcurrentHashMap<>();
    private WeakRefHandler taskHandle = new _Handler(this);

    private static class _Handler extends WeakRefHandler<CountDownSession> {

        public _Handler(CountDownSession app) {
            super(app);
        }

        @Override
        protected void handleMessage(CountDownSession session, Message msg) {
            if (msg.what == WHAT_AUTO_REMOVE_TASK) {
                Bundle bundle = msg.getData();
                if (bundle != null) {
                    session.removeTask(msg.getData().getString("taskId"),true);
                }
            }
        }
    }

    /**
     *
     * @param taskId
     * @param task
     * @param duration 秒
     */
    public void addTask(String taskId, ICountDownTask task, long duration){
        list.put(taskId,task);
        Bundle bundle=new Bundle();
        bundle.putString("taskId", taskId);
        Message message=taskHandle.obtainMessage(WHAT_AUTO_REMOVE_TASK);
        message.setData(bundle);
        taskHandle.sendMessageDelayed(message,duration*1000);
    }
    public void removeTask(String taskId){
        removeTask(taskId,false);
    }
    public void removeTask(String taskId, boolean isAuto){
        ICountDownTask task=list.remove(taskId);
        if(task!=null) {
            if(isAuto){
                task.onFinished();
            }else {
                task.onCancle();
            }
        }
    }
    public void notifyTask(int continueTime){
        Iterator iter = list.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            ICountDownTask task = (ICountDownTask) entry.getValue();
            if(continueTime%task.period==0) {
                task.countDown();
            }
        }
    }
}
