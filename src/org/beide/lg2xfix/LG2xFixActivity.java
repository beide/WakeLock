package org.beide.lg2xfix;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;

public class LG2xFixActivity extends Activity
{
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		Intent intent = new Intent(this, LG2xFixService.class);
		startService(intent);
	}
}
