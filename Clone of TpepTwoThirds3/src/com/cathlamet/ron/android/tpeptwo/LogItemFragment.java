package com.cathlamet.ron.android.tpeptwo;

import java.util.Date;
import java.util.UUID;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class LogItemFragment extends Fragment {
	public static final String EXTRA_LOGITEM_ID = 
			"com.cathlamet.ron.android.tpeptwo.log_item_id";
private LogItem mLogItem;
private EditText mTitleField;
private EditText mDetailsField;
private EditText mCriteriaField;
private EditText mHoursField;
private EditText mLinkField;
private Button mDateButton;
private Button mSaveButton;
private Button mPhotoButton;
private static final String DIALOG_DATE = "date";
private static final int REQUEST_DATE = 0;


public static LogItemFragment newInstance(UUID logItemId){
	Bundle args = new Bundle();
	args.putSerializable(EXTRA_LOGITEM_ID,  logItemId);
	LogItemFragment fragment = new LogItemFragment();
	fragment.setArguments(args);
	return fragment;
}

private void updateDate(){
	mDateButton.setText(mLogItem.getDate().toString());
}
/**
public String getItemString(){
	
	String dateFormat = "EEE, MM, dd";
	String stringOut = "";
	String dateString = DateFormat.format(dateFormat, mLogItem.getDate()).toString();
	stringOut = "++++++++++++++++++++++++++++++++++++++++++++++++++++++\n";
	stringOut = stringOut + dateString + "                   " + mLogItem.getCriteria() + "\n";
	stringOut = stringOut + mLogItem.getTitle() + "\n";
	return stringOut;
}
*/

@Override
public void onActivityResult(int requestCode, int resultCode, Intent data){
	if (resultCode != Activity.RESULT_OK) return;
	if (requestCode == REQUEST_DATE){
		Date date = (Date)data
				.getSerializableExtra(DialogPickerFragment.EXTRA_DATE);
		mLogItem.setDate(date);
		updateDate();
	}
}

@Override
public void onPause(){
	super.onPause();
	LogList.get(getActivity()).saveLogItems();
}


@Override
public void onCreate(Bundle savedInstanceState){
	super.onCreate(savedInstanceState);
	
	UUID logItemId = (UUID)getArguments()
			.getSerializable(EXTRA_LOGITEM_ID);
	mLogItem = LogList.get(getActivity()).getLogItem(logItemId);
}



@Override
public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
View v = inflater.inflate(R.layout.fragment_log_item, parent, false);

//+++++++++++ Title ++++++++++++++++++ 

mTitleField = (EditText)v.findViewById(R.id.log_item_title);
mTitleField.setText(mLogItem.getTitle());
mTitleField.addTextChangedListener(new TextWatcher(){
	public void onTextChanged(
			CharSequence c, int start, int before, int count){
		mLogItem.setTitle(c.toString());
	}
	
	public void beforeTextChanged(
		CharSequence c, int start, int count, int after){
	// this space intentionally left blank
			}
	public void afterTextChanged(Editable c){
		// this one too
	}

});


//+++++++++++ Details ++++++++++++++++++ 

mDetailsField = (EditText)v.findViewById(R.id.log_item_details);
mDetailsField.setText(mLogItem.getDescription());
mDetailsField.addTextChangedListener(new TextWatcher(){
	public void onTextChanged(
			CharSequence c, int start, int before, int count){
		mLogItem.setDescription(c.toString());
	}
	
	public void beforeTextChanged(
		CharSequence c, int start, int count, int after){
	// this space intentionally left blank
			}
	public void afterTextChanged(Editable c){
		// this one too
	}

});
/**
//+++++++++++ Documentation Link ++++++++++++++++++ 

mLinkField = (EditText)v.findViewById(R.id.log_item_doc_link);
mLinkField.setText(mLogItem.getDocLink());
mLinkField.addTextChangedListener(new TextWatcher(){
	public void onTextChanged(
			CharSequence c, int start, int before, int count){
		mLogItem.setDocLink(c.toString());
	}
	
	public void beforeTextChanged(
		CharSequence c, int start, int count, int after){
	// this space intentionally left blank
			}
	public void afterTextChanged(Editable c){
		// this one too
	}

});
*/
//+++++++++++ Hours ++++++++++++++++++ 

mHoursField = (EditText)v.findViewById(R.id.log_item_hours);
mHoursField.setText(mLogItem.getHours());
mHoursField.addTextChangedListener(new TextWatcher(){
	public void onTextChanged(
			CharSequence c, int start, int before, int count){
		mLogItem.setHours(c.toString());
	}
	
	public void beforeTextChanged(
		CharSequence c, int start, int count, int after){
	// this space intentionally left blank
			}
	public void afterTextChanged(Editable c){
		// this one too
	}

});

//+++++++++++ Criteria ++++++++++++++++++ 

mCriteriaField = (EditText)v.findViewById(R.id.log_item_criteria);
mCriteriaField.setText(mLogItem.getCriteria());
mCriteriaField.addTextChangedListener(new TextWatcher(){
	public void onTextChanged(
			CharSequence c, int start, int before, int count){
		mLogItem.setCriteria(c.toString());
	}
	
	public void beforeTextChanged(
		CharSequence c, int start, int count, int after){
	// this space intentionally left blank
			}
	public void afterTextChanged(Editable c){
		// this one too
	}

});


//+++++++++++ Date ++++++++++++++++++ 

mDateButton = (Button)v.findViewById(R.id.log_item_date);
updateDate();
mDateButton.setOnClickListener(new View.OnClickListener() {
	public void onClick(View v){
		FragmentManager fm = getActivity()
				.getSupportFragmentManager();
		DialogPickerFragment dialog =  DialogPickerFragment
				.newInstance(mLogItem.getDate());
		dialog.setTargetFragment(LogItemFragment.this, REQUEST_DATE);
		dialog.show(fm, DIALOG_DATE);
	}
});


//+++++++++++ Take or show a photo ++++++++++++++++++ 

mPhotoButton = (Button)v.findViewById(R.id.log_item_photo);

mPhotoButton.setOnClickListener(new View.OnClickListener() {
	public void onClick(View v){
		
		Toast.makeText(getActivity(), R.string.photo_toast, Toast.LENGTH_SHORT).show();
	}
});

//+++++++++++ Save the file ++++++++++++++++++ 

mSaveButton = (Button)v.findViewById(R.id.log_item_save);

mSaveButton.setOnClickListener(new View.OnClickListener() {
	public void onClick(View v){
		Toast.makeText(getActivity(), R.string.save_toast, Toast.LENGTH_SHORT).show();
		LogList.get(getActivity()).saveLogItems();
	}
});

return v;
}
}
