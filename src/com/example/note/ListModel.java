package com.example.note;

import java.util.ArrayList;
import java.util.UUID;

import android.content.Context;
import android.util.Log;

public class ListModel {

	private Context context;
	private static ListModel sListModel;
	
	private ArrayList<RecordModel> mModels;
	
	//创建单例
	private ListModel(Context context){
		Log.d("wangbin","查看创建mModels");
		this.context = context;
		mModels = new ArrayList<RecordModel>();
		for(int i=0; i<10; i++){
			RecordModel model = new RecordModel(context);
			model.setTitle("样本" + (i+1));
			mModels.add(model);
		}
	}
	
	public static ListModel get(Context context) {
		if(sListModel == null){
			sListModel = new ListModel(context.getApplicationContext());
		}
		
		return sListModel;
	}
	
	public ArrayList<RecordModel> getModels() {
		return mModels;
	}
	
	public void addModel(RecordModel model){
		mModels.add(model);
	}
	
	public RecordModel getModel(UUID id) {
		for(RecordModel model : mModels){
			if(model.getId().equals(id)){
				return model;
			}
		}
		return null;
	}
	
}
