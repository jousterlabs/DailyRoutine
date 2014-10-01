package com.jousterlabs.dailyroutine.commonutils;

import java.util.Calendar;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.EditText;

@SuppressLint("ValidFragment")
public class DatePickerFragment extends DialogFragment implements
		OnDateSetListener {
	EditText editText_DatePickerFragment_date;

	public DatePickerFragment(EditText mEditText_DatePickerFragment_date) {
		editText_DatePickerFragment_date = mEditText_DatePickerFragment_date;
	}

	public static String GET_DATE;

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// Use the current date as the default date in the picker

		final Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);

		// Create a new instance of DatePickerDialog and return it
		return new DatePickerDialog(getActivity(), this, year, month, day);
	}

	public void onDateSet(DatePicker view, int year, int month, int day) {
		// Do something with the date chosen by the user
		GET_DATE = year + "/" + month + "/" + day;

		editText_DatePickerFragment_date.setText(GET_DATE);

	}
}