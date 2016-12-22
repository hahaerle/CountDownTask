package sel.countdown.example;

import com.sel.countdown.CountDownObject;

import java.io.Serializable;

/**
 * Created by shangerle on 16/12/21.
 */

public class ExampleData implements Serializable {
    private CountDownObject object;
    private String desc;

    public ExampleData(long period, String desc,long duration) {
        this.object = new CountDownObject(period,duration);
        this.desc = desc;
    }


    public String getDesc() {
        return desc;
    }

    public long getPeriod() {
        return object.getPeriod();
    }

    public long getDuration() {
        return object.getDuration();
    }
}
