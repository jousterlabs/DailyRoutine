package com.jousterlabs.dailyroutine;

import java.util.ArrayList;

import com.jousterlabs.dailyroutine.adapter.AdapterViewTaskActivityListView;
import com.jousterlabs.dailyroutine.commonutils.GetterSetter;
import com.jousterlabs.dailyroutine.database.DailyroutineDataBaseClass;

import android.app.Activity;
import android.content.ContentValues;
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

public class ViewTaskActivity extends Activity implements OnClickListener {
	LinearLayout linearLayout_ViewTaskActivity_back;
	ProgressBar progressBar_ViewTaskActivity_loadData;
	ListView listView_ViewTaskActivity;
	DailyroutineDataBaseClass dailyroutineDataBaseClass;
	ArrayList<GetterSetter> arrayList_ViewTaskActivity;
	GetterSetter getterSetter;
	AdapterViewTaskActivityListView adapterViewTaskActivityListView;
	AsynTaskFatchTaskDetailsDB asynTaskFatchTaskDetailsDB;
	TextView textView_ViewTaskActivity_empty;
	ImageView imageView_ViewTaskActivity_grid,
			imageView_ViewTaskActivity_delete;
	AsynTaksClearCheckedItems asynTaksClearCheckedItems;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewtaskactivity);

		listView_ViewTaskActivity = (ListView) findViewById(R.id.listView_ViewTaskActivity);
		progressBar_ViewTaskActivity_loadData = (ProgressBar) findViewById(R.id.pgr_ViewTaskActivity_pleaseWait);
		linearLayout_ViewTaskActivity_back = (LinearLayout) findViewById(R.id.linlay_ViewTaskActivity_back);
		textView_ViewTaskActivity_empty = (TextView) findViewById(R.id.txt_ViewTaskActivity_empty);
		imageView_ViewTaskActivity_grid = (ImageView) findViewById(R.id.img_ViewTaskActivity_grid);
		imageView_ViewTaskActivity_delete = (ImageView) findViewById(R.id.img_ViewTaskActivity_delete);
		linearLayout_ViewTaskActivity_back.setOnClickListener(this);

		dailyroutineDataBaseClass = new DailyroutineDataBaseClass(
				getApplicationContext());
		dailyroutineDataBaseClass.db_Write();
		arrayList_ViewTaskActivity = new ArrayList<GetterSetter>();

		asynTaskFatchTaskDetailsDB = new AsynTaskFatchTaskDetailsDB();
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			asynTaskFatchTaskDetailsDB.executeOnExecutor(
					AsyncTask.THREAD_POOL_EXECUTOR, "async");
		} else {
			asynTaskFatchTaskDetailsDB.execute("async");
		}

	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		asynTaksClearCheckedItems = new AsynTaksClearCheckedItems();
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			asynTaksClearCheckedItems.executeOnExecutor(
					AsyncTask.THREAD_POOL_EXECUTOR, "async");
		} else {
			asynTaksClearCheckedItems.execute("async");
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.linlay_ViewTaskActivity_back:
			finish();
			asynTaksClearCheckedItems = new AsynTaksClearCheckedItems();
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
				asynTaksClearCheckedItems.executeOnExecutor(
						AsyncTask.THREAD_POOL_EXECUTOR, "async");
			} else {
				asynTaksClearCheckedItems.execute("async");
			}
			break;

		default:
			break;
		}
	}

	// ---Start to fatch List of data and set into GetterSetter Start
	// AsynTask---
	protected class AsynTaskFatchTaskDetailsDB extends
			AsyncTask<String, Void, Void> {
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			progressBar_ViewTaskActivity_loadData.setVisibility(View.VISIBLE);
			super.onPreExecute();
		}

		@Override
		protected Void doInBackground(String... params) {
			// TODO Auto-generated method stub
			String string_ViewTaskActivity_work = null, string_ViewTaskActivity_place, string_ViewTaskActivity_remark, string_ViewTaskActivity_workTime, string_ViewTaskActivity_workDate, string_ViewTaskActivity_timeStamp;
			Cursor cursor_resDetails;
			try {
				cursor_resDetails = dailyroutineDataBaseClass
						.db_fatch_dailyRoutine("ok");
				if (cursor_resDetails != null
						&& cursor_resDetails.getCount() > 0) {
					while (cursor_resDetails.moveToNext()) {
						string_ViewTaskActivity_work = cursor_resDetails
								.getString(2);
						string_ViewTaskActivity_workDate = cursor_resDetails
								.getString(3);
						string_ViewTaskActivity_workTime = cursor_resDetails
								.getString(4);
						string_ViewTaskActivity_place = cursor_resDetails
								.getString(5);
						string_ViewTaskActivity_remark = cursor_resDetails
								.getString(6);
						string_ViewTaskActivity_timeStamp = cursor_resDetails
								.getString(9);

						getterSetter = new GetterSetter();
						getterSetter
								.setString_ViewTaskActivity_work(string_ViewTaskActivity_work);
						getterSetter
								.setString_ViewTaskActivity_workDate(string_ViewTaskActivity_workDate);
						getterSetter
								.setString_ViewTaskActivity_workTime(string_ViewTaskActivity_workTime);
						getterSetter
								.setString_ViewTaskActivity_place(string_ViewTaskActivity_place);
						getterSetter
								.setString_ViewTaskActivity_remark(string_ViewTaskActivity_remark);
						getterSetter
								.setString_ViewTaskActivity_timeStamp(string_ViewTaskActivity_timeStamp);
						arrayList_ViewTaskActivity.add(getterSetter);
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
			progressBar_ViewTaskActivity_loadData.setVisibility(View.GONE);
			adapterViewTaskActivityListView = new AdapterViewTaskActivityListView(
					ViewTaskActivity.this, arrayList_ViewTaskActivity,
					imageView_ViewTaskActivity_grid,
					imageView_ViewTaskActivity_delete);
			listView_ViewTaskActivity
					.setAdapter(adapterViewTaskActivityListView);
			listView_ViewTaskActivity
					.setEmptyView(textView_ViewTaskActivity_empty);

			super.onPostExecute(result);
		}

	}

	// ---End to fatch List of data and set into GetterSetter Start AsynTask---

	// ---Start AsynTask for Clear the Checked Items---
	protected class AsynTaksClearCheckedItems extends
			AsyncTask<String, Void, Void> {

		@Override
		protected Void doInBackground(String... params) {
			// TODO Auto-generated method stub
			try {
				ContentValues contentValues = new ContentValues();
				for (int i = 0; i < arrayList_ViewTaskActivity.size(); i++) {
					String string_timeStamp = arrayList_ViewTaskActivity.get(i)
							.getString_ViewTaskActivity_timeStamp();
					contentValues.put("work_listCheckedStatus", "no");
					dailyroutineDataBaseClass
							.db_update_dailyRoutineCheckedItemStatus(
									contentValues, string_timeStamp);
				}

			} catch (Exception e) {
				// TODO: handle exception
			}
			return null;
		}

	}
	// ---End AsynTask for Clear the Checked Items---
}
