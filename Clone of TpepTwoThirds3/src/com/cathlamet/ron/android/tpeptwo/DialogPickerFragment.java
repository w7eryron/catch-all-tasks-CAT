package com.cathlamet.ron.android.tpeptwo;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;

public class DialogPickerFragment extends DialogFragment {
	public static final String EXTRA_DATE = 
			"com.cathlamet.ron.androd.tpeptwo.date";
	private Date mDate;
	
	public static DialogPickerFragment newInstance(Date date){
		Bundle args = new Bundle();
		args.putSerializable(EXTRA_DATE, date);
		
		DialogPickerFragment fragment = new DialogPickerFragment();
		fragment.setArguments(args);
		return fragment;
	}

	private void sendResult(int resultCode){
		if (getTargetFragment() == null)
			return;
		Intent i = new Intent();
		i.putExtra(EXTRA_DATE, mDate);
		getTargetFragment()
		.onActivityResult(getTargetRequestCode(),  resultCode,  i);
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState){
		
		mDate = (Date)getArguments().getSerializable(EXTRA_DATE);
		
		//create a calendar to get the year, month, and day
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(mDate);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(calendar.MONTH);
		int day = calendar.get(calendar.DAY_OF_MONTH);
		
		View v = getActivity().getLayoutInflater()
				.inflate(R.layout.dialog_date, null);
		
		DatePicker datePicker = (DatePicker)v.findViewById(R.id.dialog_date_datePicker);
		datePicker.init(year, month, day, new OnDateChangedListener(){
			public void onDateChanged(DatePicker view, int year, int month, int day){
				//translate year, month, day into a Date object using a calendar
				mDate = new GregorianCalendar(year, month, day).getTime();
				
				//update argument to preserve selected value n rotation
				getArguments().putSerializable(EXTRA_DATE, mDate);
			}
		});
		
		return new AlertDialog.Builder(getActivity())
		.setView(v)
		.setTitle(R.string.date_picker_title)
		.setPositiveButton(
				android.R.string.ok,
				new DialogInterface.OnClickListener(){
					public void onClick(DialogInterface dialog, int which){
						sendResult(Activity.RESULT_OK);
					}
				})
				.create();
	}
}
