package org.beide.wakelock;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;



public class WakeLock extends Activity {
	
	Context context;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		context = getApplicationContext();
		
		LinearLayout linlay = new LinearLayout(context);
		linlay.setOrientation(LinearLayout.VERTICAL);
		
		LinearLayout linlayactive = new LinearLayout(context);
		linlayactive.setOrientation(LinearLayout.HORIZONTAL);
		linlay.addView(linlayactive);
		
		LinearLayout linlayonboot = new LinearLayout(context);
		linlayonboot.setOrientation(LinearLayout.HORIZONTAL);
		linlay.addView(linlayonboot);
		
		CheckBox active = new CheckBox(getApplicationContext());
		linlayactive.addView(active);
		CheckBox onboot = new CheckBox(getApplicationContext());
		linlayonboot.addView(onboot);
		
		TextView tv = new TextView(context);
		tv.setText("Enable wakelock");
		linlayactive.addView(tv);
		
		tv = new TextView(context);
		tv.setText("Start on boot");
		linlayonboot.addView(tv);
		
		setContentView(linlay);
		
		active.setChecked(isMyServiceRunning());
		
		active.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton btn, boolean checked) {
				
				Intent intent = new Intent(context, WakeLockService.class);
				
				if(checked) {
					startService(intent);
				} else {
					stopService(intent);
				}
			}
		});
		
		SharedPreferences sp = getSharedPreferences("config", 0);
		onboot.setChecked(sp.getBoolean("onboot", false));
		
		onboot.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton btn, boolean checked) {
				SharedPreferences sp = getSharedPreferences("config", 0);
				SharedPreferences.Editor spe = sp.edit();
				spe.putBoolean("onboot", checked);
				spe.commit();
			}
		});
	}
	
	
	// From http://stackoverflow.com/questions/600207
	private boolean isMyServiceRunning() {
		ActivityManager manager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
		for (RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
			if ("org.beide.wakelock.WakeLockService".equals(service.service.getClassName())) {
				return true;
			}
		}
		return false;
	}
}
