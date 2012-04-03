package org.beide.lg2xfix;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class WakeLock extends Activity {
	
	private Context context;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		context = getApplicationContext();
		
		LinearLayout linlay = new LinearLayout(context);
		linlay.setOrientation(LinearLayout.HORIZONTAL);
		
		CheckBox c = new CheckBox(getApplicationContext());
		linlay.addView(c);
		
		TextView tv = new TextView(context);
		tv.setText("Enable wakelock");
		linlay.addView(tv);
		
		setContentView(linlay);
		
		c.setChecked(isMyServiceRunning());
		
		c.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton btn, boolean checked) {
				
				Intent intent = new Intent(context, WakeLockService.class);
				
				if(checked) {
					startService(intent);
				} else {
					stopService(intent);
				}
			}
		});
	}
	
	
	// From http://stackoverflow.com/questions/600207
	private boolean isMyServiceRunning() {
		ActivityManager manager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
		for (RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
			if ("org.beide.lg2xfix.WakeLockService".equals(service.service.getClassName())) {
				return true;
			}
		}
		return false;
	}
}
