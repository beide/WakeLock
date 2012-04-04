package org.beide.wakelock;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.PowerManager;


/**
 * Service to stop my LG 2x from randomly powering off.
 * I hope it also fixes yours :)
 * 
 * This is definately not an example of quality-code. I went with
 * the easy way of fixing the problem, not the qualitative way.
 */

public class WakeLockService extends Service {
	PowerManager.WakeLock wl;
	
	public int onStartCommand() {
		//The service should always run
		return START_STICKY;
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	public void onCreate() {
		PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
		wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "LG2xFixLock");
		wl.acquire();
	}
	
	public void onDestroy() {
		wl.release();
	}
}