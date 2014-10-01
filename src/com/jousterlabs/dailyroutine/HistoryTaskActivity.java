package com.jousterlabs.dailyroutine;

import java.util.ArrayList;

import com.jousterlabs.dailyroutine.adapter.AdapterHistoryTaskActivityListView;
import com.jousterlabs.dailyroutine.commonutils.GetterSetter;
import com.jousterlabs.dailyroutine.database.DailyroutineDataBaseClass;


import android.app.Activity;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class HistoryTaskActivity extends Activity implements OnClickListener {
	LinearLayout linearLayout_HistoryTaskActivity_back;
	ProgressBar progressBar_HistoryTaskActivity_loadData;
	ListView listView_HistoryTaskActivity;
	DailyroutineDataBaseClass dailyroutineDataBaseClass;
	ArrayList<GetterSetter> arrayList_HistoryTaskActivity;
	GetterSetter getterSetter;
	AdapterHistoryTaskActivityListView adapterHistoryTaskActivityListView;
	AsynTaskFatchHistoryTaskDetailsDB asynTaskFatchHistoryTaskDetailsDB;
	TextView textView_HistoryTaskActivity_empty;
	ImageView imageView_HistoryTaskActivity_delete;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.historytaskactivity);

		listView_HistoryTaskActivity = (ListView) findViewById(R.id.listView_HistoryTaskActivity);
		progressBar_HistoryTaskActivity_loadData = (ProgressBar) findViewById(R.id.pgr_HistoryTaskActivity_pleaseWait);
		linearLayout_HistoryTaskActivity_back = (LinearLayout) findViewById(R.id.linlay_HistoryTaskActivity_back);
		textView_HistoryTaskActivity_empty = (TextView) findViewById(R.id.txt_HistoryTaskActivity_empty);
		imageView_HistoryTaskActivity_delete = (ImageView) findViewById(R.id.img_HistoryTaskActivity_delete);
		linearLayout_HistoryTaskActivity_back.setOnClickListener(this);

		dailyroutineDataBaseClass = new DailyroutineDataBaseClass(
				getApplicationContext());
		dailyroutineDataBaseClass.db_Write();
		arrayList_HistoryTaskActivity = new ArrayList<GetterSetter>();

		asynTaskFatchHistoryTaskDetailsDB = new AsynTaskFatchHistoryTaskDetailsDB();
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			asynTaskFatchHistoryTaskDetailsDB.executeOnExecutor(
					AsyncTask.THREAD_POOL_EXECUTOR, "async");
		} else {
			asynTaskFatchHistoryTaskDetailsDB.execute("async");
		}

	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.linlay_HistoryTaskActivity_back:
			finish();
			
			break;

		default:
			break;
		}
	}

	// ---Start to fatch List of data and set into GetterSetter Start
	// AsynTask---
	protected class AsynTaskFatchHistoryTaskDetailsDB extends
			AsyncTask<String, Void, Void> {
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			progressBar_HistoryTaskActivity_loadData.setVisibility(View.VISIBLE);
			super.onPreExecute();
		}

		@Override
		protected Void doInBackground(String... params) {
			// TODO Auto-generated method stub
			String string_HistoryTaskActivity_work = null, string_HistoryTaskActivity_place, string_HistoryTaskActivity_remark, string_HistoryTaskActivity_workTime, string_HistoryTaskActivity_workDate, string_HistoryTaskActivity_timeStamp;
			Cursor cursor_resDetails;
			try {
				cursor_resDetails = dailyroutineDataBaseClass
						.db_fatch_dailyRoutine("deleted");
				if (cursor_resDetails != null
						&& cursor_resDetails.getCount() > 0) {
					while (cursor_resDetails.moveToNext()) {
						string_HistoryTaskActivity_work = cursor_resDetails
								.getString(2);
						string_HistoryTaskActivity_workDate = cursor_resDetails
								.getString(3);
						string_HistoryTaskActivity_workTime = cursor_resDetails
								.getString(4);
						string_HistoryTaskActivity_place = cursor_resDetails
								.getString(5);
						string_HistoryTaskActivity_remark = cursor_resDetails
								.getString(6);
						string_HistoryTaskActivity_timeStamp = cursor_resDetails
								.getString(9);

						getterSetter = new GetterSetter();
						getterSetter
								.setString_ViewTaskActivity_work(string_HistoryTaskActivity_work);
						getterSetter
								.setString_ViewTaskActivity_workDate(string_HistoryTaskActivity_workDate);
						getterSetter
								.setString_ViewTaskActivity_workTime(string_HistoryTaskActivity_workTime);
						getterSetter
								.setString_ViewTaskActivity_place(string_HistoryTaskActivity_place);
						getterSetter
								.setString_ViewTaskActivity_remark(string_HistoryTaskActivity_remark);
						getterSetter
								.setString_ViewTaskActivity_timeStamp(string_HistoryTaskActivity_timeStamp);
						arrayList_HistoryTaskActivity.add(getterSetter);
					}

				}

			} catch (Exception e) {
				// TODO: handle exception
				// Log.d("resName", "resName"+e.getMessage());
			}

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			progressBar_HistoryTaskActivity_loadData.setVisibility(View.GONE);
			adapterHistoryTaskActivityListView = new AdapterHistoryTaskActivityListView(
					HistoryTaskActivity.this, arrayList_HistoryTaskActivity,
					imageView_HistoryTaskActivity_delete);
			listView_HistoryTaskActivity
					.setAdapter(adapterHistoryTaskActivityListView);
			listView_HistoryTaskActivity
					.setEmptyView(textView_HistoryTaskActivity_empty);

			super.onPostExecute(result);
		}

	}

	
}
