package sel.countdown.example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.sel.countdown.TimerHelper;

import java.util.Date;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		TimerHelper.init();
		initView();
		initData();
	}

	private void initView() {
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	private void initData() {

		TimerHelper.schedule(new TimerTask() {
			@Override
			public void run() {
				Log.d("TEST", "do noce task 1");
			}
		});
		TimerHelper.schedule(new TimerTask() {
			@Override
			public void run() {
				Log.d("TEST", "do noce task 2 delay 1000");
			}
		}, 1000);
		TimerHelper.schedule(new TimerTask() {
			@Override
			public void run() {
				Log.d("TEST", "do noce task 3 period 1000");
			}
		}, 0, 1000*60);
		TimerHelper.schedule(new TimerTask() {
			@Override
			public void run() {
				Log.d("TEST", "do noce task 4 delay 1000,period 1000");
			}
		}, new Date(System.currentTimeMillis() + 1000), 1000);
	}

}
