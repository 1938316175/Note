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
	     int year = ca.get(Calendar.YEAR);//��ȡ���
	     int month=ca.get(Calendar.MONTH);//��ȡ�·� 
	     int day=ca.get(Calendar.DATE);//��ȡ��
	     int minute=ca.get(Calendar.MINUTE);//�� 
	     int hour=ca.get(Calendar.HOUR);//Сʱ 
	     int second=ca.get(Calendar.SECOND);//��
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
