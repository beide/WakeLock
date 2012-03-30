package org.beide.lg2xfix;

import android.app.Service;
import android.widget.Toast;
import android.content.Intent;
import android.content.Context;

import java.lang.Thread;
import java.lang.Runnable;
import java.lang.Exception;
import java.lang.String;

/**
 * Service to stop my LG 2x from randomly powering off.
 * I hope it also fixes yours :)
 * 
 * This is definately not an example of quality-code. I went with
 * the easy way of fixing the problem, not the qualitative way.
 */

public class LG2xFixActivity extends Service
{
	
	Thread thr;
	
	public int onStartCommand() {
		return START_NOT_STICKY;
	}
	
	public void onCreate() {
		thr = new Thread(new Runnable() {
			public void run() {
				try {
					while(true) {
						Thread.sleep(10L);
					}
				} catch (InterruptedException iex) {
					stopService();
				}
			}
		});
		
		thr.start();
		
		
		
		String ns = Context.NOTIFICATION_SERVICE;
		NotificationManager mNotificationManager = (NotificationManager) getSystemService(ns);
		
		
		Context context = getApplicationContext();
		CharSequence contentTitle = "LG2xFix running";
		CharSequence contentText = "Let's hope it works";
		Intent notificationIntent = new Intent(this, MyClass.class);
		PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
		
		notification.setLatestEventInfo(context, contentTitle, contentText, contentIntent);
		
		mNotificationManager.notify(1, notification);
		
		startForeground(notification);
	}
	
	public void onDestroy() {
		try {
			thr.interrupt();
		} catch (Exception e) {}
		
		
		String ns = Context.NOTIFICATION_SERVICE;
		NotificationManager mNotificationManager = (NotificationManager) getSystemService(ns);
		
		
		Context context = getApplicationContext();
		CharSequence contentTitle = ":'(";
		CharSequence contentText = "LG2xFix has stopped working :(";
		Intent notificationIntent = new Intent(this, LG2xFixService.class);
		PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
		
		notification.setLatestEventInfo(context, contentTitle, contentText, contentIntent);
		
		mNotificationManager.notify(1, notification);
		
		stopForeground(false);
	}
}