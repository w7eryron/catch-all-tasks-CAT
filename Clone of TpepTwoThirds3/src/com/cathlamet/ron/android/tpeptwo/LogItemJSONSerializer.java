package com.cathlamet.ron.android.tpeptwo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import android.content.Context;

public class LogItemJSONSerializer {
	
	private Context mContext;
	private String mFileName;
	
	public LogItemJSONSerializer(Context c, String f){
		mContext = c;
		mFileName = f;
	}
	
	public ArrayList<LogItem> loadLogItems() throws IOException, JSONException {
		ArrayList<LogItem> logItems = new ArrayList<LogItem>();
		BufferedReader reader = null;
		try {
			// open and read the file into a string builder
			InputStream in = mContext.openFileInput(mFileName);
			reader = new BufferedReader(new InputStreamReader(in));
			StringBuilder jsonString = new StringBuilder();
			String line = null;
			while((line = reader.readLine()) != null) {
				// line breaks are omitted and irrelevant
				jsonString.append(line);
				
			}
			//parse the JSON array using JSON Tokener
			JSONArray array = (JSONArray) new JSONTokener(jsonString.toString()).nextValue();
			//build the array of crimes from JSONObjects
			for (int i = 0 ; i < array.length(); i++){
				logItems.add(new LogItem(array.getJSONObject(i)));
			}
			
		} catch (FileNotFoundException e) {
				// ignore this one, it happens when starting fresh
		} finally 	{
				if (reader != null) reader.close();
			
		}
		return logItems;
	}
	
	public void saveLogItems(ArrayList<LogItem> logItems) throws JSONException, IOException{
		//build an array in JSON
		JSONArray array = new JSONArray();
		for (LogItem c : logItems)
			array.put(c.toJSON());
	
	
	//write the file to disk
	Writer writer = null;
	try {
		OutputStream out = mContext
				.openFileOutput(mFileName,  Context.MODE_PRIVATE);
		writer = new OutputStreamWriter(out);
		writer.write(array.toString());
	} finally {
		if (writer != null) writer.close();
	}

}
}
