package com.sel.countdown;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * Created by shangerle on 17/8/26.
 */

public class TimerHelper {
	private Timer timer;
	private static TimerHelper instance;

	public static void init() {
		if (instance == null) {
			instance = new TimerHelper();
		}
	}

	private TimerHelper() {
		timer = new Timer();
		TimerTask periodTask = new TimerTask() {
			@Override
			public void run() {
				//do nothing
			}
		};
		timer.schedule(periodTask, 0, TimeUnit.MINUTES.toMillis(5));
	}

	public static void schedule(TimerTask timerTask) {
		if (instance == null) {
			return;
		}
		instance.timer.schedule(timerTask, 0);
	}

	public static void schedule(TimerTask timerTask, long delay) {
		if (instance == null) {
			return;
		}
		instance.timer.schedule(timerTask, delay);
	}

	public static void schedule(TimerTask timerTask, long delay, long period) {
		if (instance == null) {
			return;
		}
		instance.timer.schedule(timerTask, delay, period);
	}

	public static void schedule(TimerTask timerTask, Date time) {
		if (instance == null) {
			return;
		}
		instance.timer.schedule(timerTask, time);
	}

	public static void schedule(TimerTask timerTask, Date time, long period) {
		if (instance == null) {
			return;
		}
		instance.timer.schedule(timerTask, time, period);
	}
}
