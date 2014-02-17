package com.cathlamet.ron.android.tpeptwo;
import java.util.ArrayList;
import java.util.UUID;

import android.content.Context;
import android.text.format.DateFormat;
import android.util.Log;



public class LogList {
	private static final String TAG = "LogList"; 
	private static final String FILENAME = "LogList.json";
	
	private ArrayList<LogItem> mLogItems;
	private LogItemJSONSerializer mSerializer;
	
	
	private static LogList sLogList;
	private Context mAppContext;
	
	private LogList(Context appContext){
		mAppContext = appContext;
		mLogItems = new ArrayList<LogItem>();
		mSerializer = new LogItemJSONSerializer(mAppContext, FILENAME);
		
		try {
			mLogItems = mSerializer.loadLogItems();
		} catch (Exception e) {
			mLogItems = new ArrayList<LogItem>();
			Log.e(TAG, "Error loading LogItems: ", e);
		}
		/**
		for (int i=0;i<5;i++){
			LogItem c = new LogItem();
			c.setTitle("LogItem #" + i);
			c.setCriteria("0.1");
			c.setCriteriaNum(1.1);
			c.setDescription("description #" + i + " for this next item in this tester for the entire set of itmes.");
			c.setHours(""+ i + ".25");
			c.setDocTitle("Photo in Picasa");
			c.setDocLink("https://plus.google.com/photos/114244627993337341351/albums/5682060770739138417");
			c.setPhoto("not yet");
			mLogItems.add(c);
		}*/
	}
	
public static LogList get(Context c){
	if (sLogList == null){
		sLogList = new LogList(c.getApplicationContext());
	}
	return sLogList;
}

public void addLogItem(LogItem c){
	mLogItems.add(c);
}
/**
public String getItemString(){
	
	String dateFormat = "EEE, MM, dd";
	String stringOut = "";
	
	for (int i = 0; i < mLogItems.size(); i++){
	String dateString = DateFormat.format(dateFormat, mLogItems.get(i).getDate()).toString();
	stringOut = "++++++++++++++++++++++++++++++++++++++++++++++++++++++\n";
	stringOut = stringOut + dateString + "                   " + mLogItems.get(i).getCriteria() + "\n";
	stringOut = stringOut + mLogItems.get(i).getTitle() + "\n";
	}
	return stringOut;
}*/

public boolean saveLogItems(){
	try {
		mSerializer.saveLogItems(mLogItems);
		Log.d(TAG, "LogItems save to file");
		return true;
	} catch (Exception e) {
		Log.e(TAG, "Error saving LogItems: ", e);
		return false;
	}
}

public ArrayList<LogItem> getLogItems(){
	return mLogItems;
}
public LogItem getLogItem(UUID id){
	for (LogItem c: mLogItems){
		if (c.getId().equals(id))
			return c;
	}
	return null;
}
}
