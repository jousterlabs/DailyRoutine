package com.jousterlabs.dailyroutine;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SwitchboardActivity extends Activity implements OnClickListener {

	TextView textView_SwitchboardActivity_listTask,
			textView_SwitchboardActivity_viewTask,
			textView_SwitchboardActivity_historyTask;
	Typeface typeface_SwitchboardActivity;
	LinearLayout linearLayout_SwitchboardActivity_listTask,
			linearLayout_SwitchboardActivity_viewTask,
			linearLayout_SwitchboardActivity_historyTask;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.switchboardactivity);

		typeface_SwitchboardActivity = Typeface.createFromAsset(getAssets(),
				"fonts/Roboto-Light.ttf");

		textView_SwitchboardActivity_listTask = (TextView) findViewById(R.id.textView_switchboardactivity_listTask);
		textView_SwitchboardActivity_viewTask = (TextView) findViewById(R.id.textView_switchboardactivity_viewTask);
		textView_SwitchboardActivity_historyTask = (TextView) findViewById(R.id.textView_switchboardactivity_historyTask);
		linearLayout_SwitchboardActivity_listTask = (LinearLayout) findViewById(R.id.linly_switchboardactivity_listTask);
		linearLayout_SwitchboardActivity_viewTask = (LinearLayout) findViewById(R.id.linly_switchboardactivity_viewTask);
		linearLayout_SwitchboardActivity_historyTask = (LinearLayout) findViewById(R.id.linly_switchboardactivity_historyTask);
		linearLayout_SwitchboardActivity_listTask.setOnClickListener(this);
		linearLayout_SwitchboardActivity_viewTask.setOnClickListener(this);
		linearLayout_SwitchboardActivity_historyTask.setOnClickListener(this);
		textView_SwitchboardActivity_listTask
				.setTypeface(typeface_SwitchboardActivity);
		textView_SwitchboardActivity_viewTask
				.setTypeface(typeface_SwitchboardActivity);
		textView_SwitchboardActivity_historyTask
				.setTypeface(typeface_SwitchboardActivity);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.linly_switchboardactivity_listTask:
			Intent intent_listActivity = new Intent(SwitchboardActivity.this,
					ListTaskActivity.class);
			startActivity(intent_listActivity);

			break;
		case R.id.linly_switchboardactivity_viewTask:
			Intent intent_viewTaskActivity = new Intent(SwitchboardActivity.this,
					ViewTaskActivity.class);
			startActivity(intent_viewTaskActivity);

			break;
		case R.id.linly_switchboardactivity_historyTask:
			Intent intent_historyTaskActivity = new Intent(SwitchboardActivity.this,
					HistoryTaskActivity.class);
			startActivity(intent_historyTaskActivity);
			break;

		default:
			break;
		}
	}

}
