package org.beide.lg2xfix;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class WakeLockBroadcastReceiver extends BroadcastReceiver {
	
	@Override
	public void onReceive(Context context, Intent i) {
		
		SharedPreferences sp =  context.getSharedPreferences("config", 0);
		boolean onboot = sp.getBoolean("onboot", false);
		
		if(onboot) {
			Intent intent = new Intent(context, WakeLockService.class);
			context.startService(intent);
		}
	}
}