package com.cathlamet.ron.android.tpeptwo;


import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class LogListFragment extends ListFragment {
	private ArrayList<LogItem> mLogItems;
	private static final String TAG = "LogListFragment";
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		getActivity().setTitle(R.string.logItems_title);
		mLogItems = LogList.get(getActivity()).getLogItems();
		
		if (mLogItems.size() == 0) {
			Log.d(TAG, "LogList is empty");
			Toast toast = Toast.makeText(getActivity(), "LogList Is Empty ... Click NEW in the MENU to start your list", Toast.LENGTH_LONG);
			toast.show();
			}
		
		LogItemAdapter adapter = new LogItemAdapter(mLogItems);
		setListAdapter(adapter);
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id){
		
		LogItem c = ((LogItemAdapter)getListAdapter()).getItem(position);
		
		//start a logItem Pager Activity
		Intent i = new Intent(getActivity(), LogItemPagerActvity.class);
		i.putExtra(LogItemFragment.EXTRA_LOGITEM_ID,  c.getId());
		startActivity(i);
		
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater ){
		super.onCreateOptionsMenu(menu,  inflater);
		inflater.inflate(R.menu.fragment_logitem_list, menu);
	}
	
	public String getItemString(){
		
		String dateFormat = "EEE: MM/dd";
		String stringOut = "";
		int i = 0;
		//for (int i = 0; i < mLogItems.size(); i++){
		String dateString = DateFormat.format(dateFormat, mLogItems.get(i).getDate()).toString();
		stringOut = "++++++++++++++++++++++++++++++++++++++++++++++++++++++\n";
		stringOut = stringOut + dateString + "                            " + mLogItems.get(i).getCriteria() + "\n";
		stringOut = stringOut + mLogItems.get(i).getTitle() + "\n";
		stringOut = stringOut + mLogItems.get(i).getDescription();
		//}
		return stringOut;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item){
		switch (item.getItemId()){
			case R.id.menu_item_new_log_item:
				LogItem logItem = new LogItem();
				LogList.get(getActivity()).addLogItem(logItem);
				Intent i = new Intent(getActivity(), LogItemPagerActvity.class);
				i.putExtra(LogItemFragment.EXTRA_LOGITEM_ID, logItem.getId());
				startActivityForResult(i, 0);
				return true;
				
			case R.id.menu_stats_item:
				Toast.makeText(getActivity(), R.string.stats_toast, Toast.LENGTH_SHORT).show();
				return true;
				
			case R.id.menu_toggle_item:
				Toast.makeText(getActivity(), R.string.toggle_toast, Toast.LENGTH_SHORT).show();
				return true;
				
			case R.id.menu_send_item:
				Toast.makeText(getActivity(), R.string.send_toast, Toast.LENGTH_SHORT).show();
				Intent j = new Intent(Intent.ACTION_SEND);
				j.setType("text/plain");
				j.putExtra(Intent.EXTRA_TEXT, getItemString());
				startActivity(j);
				return true;
				
			
				
			default:
				return super.onOptionsItemSelected(item);
		
		}
	}
	
	
	
	@Override
	public void onResume(){
		super.onResume();
		((LogItemAdapter)getListAdapter()).notifyDataSetChanged();
	}
	
private class LogItemAdapter extends ArrayAdapter<LogItem>{
	
	public LogItemAdapter(ArrayList<LogItem> logItems){
		super(getActivity(), 0, logItems);
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent){
		
		// if we weren't given a view, inflate one
		if (convertView == null){
			convertView = getActivity().getLayoutInflater()
					.inflate(R.layout.list_item_logitem, null);
		}
		
		// configure the view for this logItem
		LogItem c = getItem(position);
		TextView titleTextView = 
				(TextView)convertView.findViewById(R.id.log_item_list_item_titleTextView);
		titleTextView.setText(c.getTitle());
		
		TextView criteriaTextView = 
				(TextView)convertView.findViewById(R.id.log_item_list_item_criteriaTextView);
		criteriaTextView.setText(c.getCriteria());
		
		TextView dateTextView = 
				(TextView)convertView.findViewById(R.id.log_item_list_item_dateTextView);
		dateTextView.setText(c.getDate().toString());
		
		return convertView;
		
	}
	}
}

