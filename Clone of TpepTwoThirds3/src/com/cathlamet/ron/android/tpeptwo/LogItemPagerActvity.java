package com.cathlamet.ron.android.tpeptwo;

import java.util.ArrayList;
import java.util.UUID;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

public class LogItemPagerActvity extends FragmentActivity {
	private ViewPager mViewPager;
	private ArrayList<LogItem> mLogItems;
	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		mViewPager = new ViewPager(this);
		mViewPager.setId(R.id.viewPager);
		setContentView(mViewPager);
		
		mLogItems = LogList.get(this).getLogItems();
		
		FragmentManager fm = getSupportFragmentManager();
		mViewPager.setAdapter(new FragmentStatePagerAdapter(fm){
			@Override
			public int getCount(){
				return mLogItems.size();
			}
			@Override
			public Fragment getItem(int pos){
				LogItem logItem = mLogItems.get(pos);
				return LogItemFragment.newInstance(logItem.getId());
			}
			
		});
		
		UUID logItemId = (UUID)getIntent()
				.getSerializableExtra(LogItemFragment.EXTRA_LOGITEM_ID);
		for (int i = 0; i < mLogItems.size(); i++){
			if (mLogItems.get(i).getId().equals(logItemId)){
				mViewPager.setCurrentItem(i);
				break;
			}
		}
		
		
		mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener(){
			public void onPageScrollStateChanged(int state){}
			
			public void onPageScrolled(int pos, float postOffset, int postOffsetPixels){}
			
			public void onPageSelected(int pos){
				LogItem logItem = mLogItems.get(pos);
				if (logItem.getTitle() != null){
					setTitle(logItem.getTitle());
				}
			}
		});
		
	}
	

}
