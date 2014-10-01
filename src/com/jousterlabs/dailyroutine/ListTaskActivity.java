package com.jousterlabs.dailyroutine;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.jousterlabs.dailyroutine.commonutils.DatePickerFragment;
import com.jousterlabs.dailyroutine.commonutils.TimePickerFragment;
import com.jousterlabs.dailyroutine.database.DailyroutineDataBaseClass;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.telephony.TelephonyManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

@SuppressLint("SimpleDateFormat")
public class ListTaskActivity extends FragmentActivity implements
		OnClickListener {

	EditText editText_ListTaskActivity_work, editText_ListTaskActivity_date,
			editText_ListTaskActivity_time, editText_ListTaskActivity_place,
			editText_ListTaskActivity_remark;
	ImageView imageView_ListTaskActivity_datePicker,
			imageView_ListTaskActivity_timePicker;
	Button button_ListTaskActivity_attTolist, button_ListTaskActivity_clear;
	LinearLayout linearLayout_ListTaskActivity_back;
	DailyroutineDataBaseClass dailyroutineDataBaseClass;
	TelephonyManager telephonyManager;
	String string_ListTaskActivity_IMEINumber, string_AMPM,
			string_ListTaskActivity_workTime, string_ListTaskActivity_workDate,
			string_ListTaskActivity_timeStamp;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listtaskactivity);

		editText_ListTaskActivity_work = (EditText) findViewById(R.id.et_ListTaskActivity_work);
		editText_ListTaskActivity_date = (EditText) findViewById(R.id.et_ListTaskActivity_date);
		editText_ListTaskActivity_time = (EditText) findViewById(R.id.et_ListTaskActivity_time);
		editText_ListTaskActivity_place = (EditText) findViewById(R.id.et_ListTaskActivity_place);
		editText_ListTaskActivity_remark = (EditText) findViewById(R.id.et_ListTaskActivity_remark);
		imageView_ListTaskActivity_datePicker = (ImageView) findViewById(R.id.img_ListTaskActivity_datePic);
		imageView_ListTaskActivity_timePicker = (ImageView) findViewById(R.id.img_ListTaskActivity_timePic);
		linearLayout_ListTaskActivity_back = (LinearLayout) findViewById(R.id.linlay_ListTaskActivity_back);
		button_ListTaskActivity_attTolist = (Button) findViewById(R.id.btn_ListTaskActivity_addToList);
		button_ListTaskActivity_clear = (Button) findViewById(R.id.btn_ListTaskActivity_viewTask);
		
		editText_ListTaskActivity_date.setKeyListener(null);
		editText_ListTaskActivity_time.setKeyListener(null);

		button_ListTaskActivity_attTolist.setOnClickListener(this);
		button_ListTaskActivity_clear.setOnClickListener(this);
		linearLayout_ListTaskActivity_back.setOnClickListener(this);
		imageView_ListTaskActivity_datePicker.setOnClickListener(this);
		imageView_ListTaskActivity_timePicker.setOnClickListener(this);

		// ---Start call DataBase---
		dailyroutineDataBaseClass = new DailyroutineDataBaseClass(
				getApplicationContext());
		dailyroutineDataBaseClass.db_Write();
		// ---End call DataBase---

		// ---Start get The IMEI Number---
		telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		string_ListTaskActivity_IMEINumber = ""
				+ telephonyManager.getDeviceId();
		// ---End get The IMEI Number---

		// ---To get SystemTime and Date---

		final Calendar c = Calendar.getInstance();

		// System.out.println(new Timestamp(date.getTime()));
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);

		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);

		if (hour > 12) {
			hour -= 12;
			string_AMPM = "PM";
		} else if (hour == 0) {
			hour += 12;
			string_AMPM = "AM";
		} else if (hour == 12)
			string_AMPM = "PM";
		else
			string_AMPM = "AM";

		string_ListTaskActivity_workDate = year + "/" + month + "/" + day;
		string_ListTaskActivity_workTime = hour + ":" + minute;
		// ---Start to set the date and time into edittext---
		editText_ListTaskActivity_date
				.setText(string_ListTaskActivity_workDate);
		editText_ListTaskActivity_time
				.setText(string_ListTaskActivity_workTime);
		// ---End to set the date and time into edittext---

		// ---editText_ListTaskActivity_work Length Check---
		editText_ListTaskActivity_work
				.addTextChangedListener(new TextWatcher() {

					@Override
					public void onTextChanged(CharSequence s, int start,
							int before, int count) {
						// TODO Auto-generated method stub

					}

					@Override
					public void beforeTextChanged(CharSequence s, int start,
							int count, int after) {
						// TODO Auto-generated method stub

					}

					@Override
					public void afterTextChanged(Editable s) {
						// TODO Auto-generated method stub
						String string = s.toString();
						if (string.length() > 0) {
							editText_ListTaskActivity_work.setError(null);
						}

					}
				});
		// ---editText_ListTaskActivity_work Length Check---
		editText_ListTaskActivity_place
				.addTextChangedListener(new TextWatcher() {

					@Override
					public void onTextChanged(CharSequence s, int start,
							int before, int count) {
						// TODO Auto-generated method stub

					}

					@Override
					public void beforeTextChanged(CharSequence s, int start,
							int count, int after) {
						// TODO Auto-generated method stub

					}

					@Override
					public void afterTextChanged(Editable s) {
						// TODO Auto-generated method stub
						String string = s.toString();
						if (string.length() > 0) {
							editText_ListTaskActivity_place.setError(null);
						}
					}
				});

		// ---editText_ListTaskActivity_date Length Check---
		editText_ListTaskActivity_date
				.addTextChangedListener(new TextWatcher() {

					@Override
					public void onTextChanged(CharSequence s, int start,
							int before, int count) {
						// TODO Auto-generated method stub

					}

					@Override
					public void beforeTextChanged(CharSequence s, int start,
							int count, int after) {
						// TODO Auto-generated method stub

					}

					@Override
					public void afterTextChanged(Editable s) {
						// TODO Auto-generated method stub
						String string = s.toString();
						if (string.length() > 0) {
							editText_ListTaskActivity_date.setError(null);
						}
					}
				});

		// ---editText_ListTaskActivity_time Length Check---
		editText_ListTaskActivity_time
				.addTextChangedListener(new TextWatcher() {

					@Override
					public void onTextChanged(CharSequence s, int start,
							int before, int count) {
						// TODO Auto-generated method stub

					}

					@Override
					public void beforeTextChanged(CharSequence s, int start,
							int count, int after) {
						// TODO Auto-generated method stub

					}

					@Override
					public void afterTextChanged(Editable s) {
						// TODO Auto-generated method stub
						String string = s.toString();
						if (string.length() > 0) {
							editText_ListTaskActivity_time.setError(null);
						}
					}
				});

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_ListTaskActivity_addToList:
			insertListedDataInToDb();
			break;
		case R.id.btn_ListTaskActivity_viewTask:
			Intent intent_ListTaskActivity = new Intent(ListTaskActivity.this,
					ViewTaskActivity.class);
			startActivity(intent_ListTaskActivity);
			break;
		case R.id.linlay_ListTaskActivity_back:
			finish();
			break;
		case R.id.img_ListTaskActivity_datePic:
			showDatePickerDialog(editText_ListTaskActivity_date);
			break;
		case R.id.img_ListTaskActivity_timePic:
			showTimePickerDialog(editText_ListTaskActivity_time);
			break;

		default:
			break;
		}
	}

	// ---Start Data Insert into DataBase---
	protected void insertListedDataInToDb() {
		string_ListTaskActivity_timeStamp = "";
		SimpleDateFormat s = new SimpleDateFormat("ddMMyyyyhhmmss");
		String format = s.format(new Date());
		string_ListTaskActivity_timeStamp = format.toString();

		ContentValues contentValues = new ContentValues();
		String string_ListTaskActivity_work = null, string_ListTaskActivity_place, string_ListTaskActivity_remark;

		string_ListTaskActivity_work = editText_ListTaskActivity_work.getText()
				.toString();
		string_ListTaskActivity_workDate = editText_ListTaskActivity_date
				.getText().toString();
		string_ListTaskActivity_workTime = editText_ListTaskActivity_time
				.getText().toString();
		string_ListTaskActivity_place = editText_ListTaskActivity_place
				.getText().toString();
		string_ListTaskActivity_remark = editText_ListTaskActivity_remark
				.getText().toString();

		if (string_ListTaskActivity_work.length() > 0
				&& string_ListTaskActivity_workDate.length() > 0
				&& string_ListTaskActivity_workTime.length() > 0
				&& string_ListTaskActivity_place.length() > 0) {
			if (string_ListTaskActivity_remark.length() <= 0) {
				string_ListTaskActivity_remark = "Not Updated";
			}
			if (contentValues != null) {
				contentValues.put("imei_number",
						string_ListTaskActivity_IMEINumber);
				contentValues.put("work_name", string_ListTaskActivity_work);
				contentValues
						.put("work_date", string_ListTaskActivity_workDate);
				contentValues
						.put("work_time", string_ListTaskActivity_workTime);
				contentValues.put("work_place", string_ListTaskActivity_place);
				contentValues
						.put("work_remark", string_ListTaskActivity_remark);
				contentValues.put("work_listStatus", "ok");
				contentValues.put("work_listCheckedStatus", "no");
				contentValues.put("work_timeStamp",
						string_ListTaskActivity_timeStamp);

				dailyroutineDataBaseClass
						.db_Insert_dailyRoutine_addToList(contentValues);

				editText_ListTaskActivity_work.setText("");
				editText_ListTaskActivity_place.setText("");
				editText_ListTaskActivity_date.setText("");
				editText_ListTaskActivity_time.setText("");
				editText_ListTaskActivity_remark.setText("");
				Toast.makeText(getApplicationContext(),
						"Add to the Task list Successfully ",
						Toast.LENGTH_SHORT).show();

			}

		} else {
			if (string_ListTaskActivity_work.length() > 0) {
				if (string_ListTaskActivity_workDate.length() > 0) {
					if (string_ListTaskActivity_workTime.length() > 0) {
						if (string_ListTaskActivity_place.length() <= 0) {
							editText_ListTaskActivity_place
									.setError("Please Enter Place");
						}

					} else {
						editText_ListTaskActivity_time
								.setError("Please Enter Time");
					}

				} else {
					editText_ListTaskActivity_date
							.setError("Please Enter Date");
				}

			} else {
				editText_ListTaskActivity_work.setError("Please Enter Work");
			}

		}

	}

	// ---End Data Insert into DataBase---

	// ---Start to get theDate---
	public void showDatePickerDialog(EditText editText_Date) {
		DialogFragment newFragment = new DatePickerFragment(editText_Date);
		newFragment.show(getSupportFragmentManager(), "datePicker");

	}

	// ---End to get theDate---
	// ---Start to get theTime---
	public void showTimePickerDialog(EditText editText_Time) {
		DialogFragment newFragment = new TimePickerFragment(editText_Time);
		newFragment.show(getSupportFragmentManager(), "timePicker");

	}
	// ---End to get theTime---

}
