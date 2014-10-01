package com.jousterlabs.dailyroutine.commonutils;

import java.util.Calendar;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.EditText;
import android.widget.TimePicker;

@SuppressLint("ValidFragment")
public class TimePickerFragment extends DialogFragment implements
		OnTimeSetListener {
	public static String GET_TIME;
	String string_AMPM;
	EditText editText_TimePickerFragment_time;
	
	public TimePickerFragment(EditText mEditText_TimePickerFragment_time)
	{
		editText_TimePickerFragment_time=mEditText_TimePickerFragment_time;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// Use the current time as the default values for the picker

		final Calendar c = Calendar.getInstance();
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);

		// Create a new instance of TimePickerDialog and return it
		return new TimePickerDialog(getActivity(), this, hour, minute,
				DateFormat.is24HourFormat(getActivity()));
	}

	public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
		// Do something with the time chosen by the user

		if (hourOfDay > 12) {
			hourOfDay -= 12;
			string_AMPM = "PM";
		} else if (hourOfDay == 0) {
			hourOfDay += 12;
			string_AMPM = "AM";
		} else if (hourOfDay == 12)
			string_AMPM = "PM";
		else
			string_AMPM = "AM";

		GET_TIME = hourOfDay + ":" + minute + " " + string_AMPM;
		
		editText_TimePickerFragment_time.setText(GET_TIME);
	}
}