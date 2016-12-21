package sel.countdown.example;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shuai.countdown.ICountDownTask;
import com.shuai.countdown.TimeHelper;

import java.util.Date;
import java.util.UUID;

/**
 * Created by shangerle on 16/12/21.
 */

public class ViewListItem extends LinearLayout {
    private boolean alreadyInflated_=false;
    private TextView currentTime;
    private TextView periodDesc;

    public ViewListItem(Context context) {
        super(context);
        init();
    }

    public ViewListItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    public ViewListItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        if (!alreadyInflated_) {
            alreadyInflated_ = true;
            View view=inflate(getContext(), R.layout.view_list_item, this);
            currentTime= (TextView) view.findViewById(R.id.currentTime);
            periodDesc= (TextView) view.findViewById(R.id.periodDesc);
        }
    }
    private UUID uuid;
    public void bindData(ExampleData data){
        if(uuid!=null) {
            /**
             * 移除任务
             */
            TimeHelper.getInstances().removeTask(uuid.toString());
        }
        uuid=UUID.randomUUID();
        periodDesc.setText(data.getDesc());
        updateCountTime();
        /**
         * 加入任务队列
         */
        TimeHelper.getInstances().putTask(uuid.toString(), new ICountDownTask(data.getPeriod()) {
            @Override
            public void onRefresh() {
                updateCountTime();
            }

            @Override
            public void onFinished() {
                finishCountTime();
            }

            @Override
            public void onCancle() {

            }
        }, data.getDuration());
    }

    private void finishCountTime() {
        currentTime.setText("已停止");
    }

    private void updateCountTime() {
        currentTime.setText(new Date().toString());
    }
}
