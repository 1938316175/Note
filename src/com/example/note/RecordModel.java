package com.example.note;

import java.util.Calendar;
import java.util.UUID;

import android.content.Context;



public class RecordModel {

	private String mDate;
	private String mTitle="";
	private Context mContext;
	private UUID mId;
	private String mString;
	
	public RecordModel(Context context) {
		mContext = context;
		mId = UUID.randomUUID();
	}
	
	public UUID getId() {
		return mId;
	}

	public String getDate(){
		return mDate;
	}

	public void setDate(String mDate) {
		this.mDate = mDate;
	}
	
	public String setNowDate(){
	     Calendar ca = Calendar.getInstance();
	     int year = ca.get(Calendar.YEAR);//获取年份
	     int month=ca.get(Calendar.MONTH);//获取月份 
	     int day=ca.get(Calendar.DATE);//获取日
	     int minute=ca.get(Calendar.MINUTE);//分 
	     int hour=ca.get(Calendar.HOUR);//小时 
	     int second=ca.get(Calendar.SECOND);//秒
	     String nowDate = year + "-" + month + "-" + day + "," + hour + ":" + minute + ":" + second;
	     
	     return nowDate;
	}

	public String getTitle() {
		return mTitle;
	}

	public void setTitle(String mTitle) {
		this.mTitle = mTitle;
	}

	
	public String getString() {
		return mString;
	}
	

	public void setString(String mString) {
		this.mString = mString;
	}
	
	
	
}
