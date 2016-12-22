package sel.countdown.example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.sel.countdown.Constants;
import com.sel.countdown.TimeHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private List<ExampleData> list=new ArrayList<>();
    private MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TimeHelper.init();
        initView();
        initData();
    }

    private void initView() {

        listView= (ListView) findViewById(R.id.listView);
        adapter=new MyAdapter();
        listView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        TimeHelper.getInstances().startTimer();
    }

    @Override
    protected void onPause() {
        super.onPause();
        TimeHelper.getInstances().stopTimer();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        TimeHelper.release();
    }

    private void initData(){
        list.add(new ExampleData(Constants.PERIOD_SECOND,"1秒,持续10秒", TimeUnit.SECONDS.toMillis(10)));
        list.add(new ExampleData(Constants.PERIOD_SECOND*2,"2秒,持续10秒",TimeUnit.SECONDS.toMillis(10)));
        list.add(new ExampleData(Constants.PERIOD_SECOND*3,"3秒,持续3分钟",TimeUnit.MINUTES.toMillis(3)));
        list.add(new ExampleData(Constants.PERIOD_MINUTE,"1分,持续3分钟",TimeUnit.MINUTES.toMillis(3)));
        list.add(new ExampleData(Constants.PERIOD_SECOND*30,"30秒,持续3分钟",TimeUnit.MINUTES.toMillis(3)));
        list.add(new ExampleData(Constants.PERIOD_SECOND*60,"60秒,持续3分钟",TimeUnit.MINUTES.toMillis(3)));
        adapter.notifyDataSetChanged();
    }
    private class MyAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewListItem item;
            if(view!=null){
                item=(ViewListItem)view;
            }else {
                item=new ViewListItem(MainActivity.this);
            }
            item.bindData(list.get(i));

            return item;
        }
    }
}
