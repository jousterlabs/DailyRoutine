package com.jousterlabs.dailyroutine;

import com.jousterlabs.dailyroutine.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.widget.TextView;

public class SplashScreenActivity extends Activity {

	public static final int INT_SPLASH_TIMING = 3000;
	Handler handler;
	TextView textView_SplashScreenActivity_appName,
			textView_SplashScreenActivity_appPoweredby;
	Typeface typeface_SplashScreenActivity;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.splashscreenactivity);
		typeface_SplashScreenActivity = Typeface.createFromAsset(getAssets(),
				"fonts/Roboto-Light.ttf");
		textView_SplashScreenActivity_appName = (TextView) findViewById(R.id.textView_splashscreenactivity_appName);
		textView_SplashScreenActivity_appPoweredby = (TextView) findViewById(R.id.textView_splashscreenactivity_appPoweredBy);
		textView_SplashScreenActivity_appName.setTypeface(typeface_SplashScreenActivity);
		textView_SplashScreenActivity_appPoweredby.setTypeface(typeface_SplashScreenActivity);

		handler = new Handler();
		handler.postDelayed(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Intent intent_walkthrough = new Intent(
						SplashScreenActivity.this, SwitchboardActivity.class);
				intent_walkthrough.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent_walkthrough);
				finish();

			}
		}, INT_SPLASH_TIMING);

	}

}
