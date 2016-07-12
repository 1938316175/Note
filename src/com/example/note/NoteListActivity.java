package com.example.note;

import java.util.ArrayList;
import java.util.List;

import android.R.string;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class NoteListActivity extends Activity{
	
	private ArrayList<RecordModel> mModels;
	private Context context = NoteListActivity.this;
	public static final String NOTE_ID = "com.example.note.id";
	
	private ListView listView;
	private ModelAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

//		requestWindowFeature(Window.FEATURE_NO_TITLE); // 注意顺序  
		setContentView(R.layout.activity_notelist);
		 
     //   getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.title);
		
		mModels = ListModel.get(NoteListActivity.this).getModels();
	//	Log.d("wangbin", "mModels的个数"+mModels.size());
		adapter = new ModelAdapter(mModels);
		
		listView = (ListView)findViewById(R.id.note_list);
	//	listView.setAdapter(adapter);
		update();
		
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				RecordModel model = mModels.get(position);
				
				Intent intent = new Intent(context, RecordActivity.class);
				intent.putExtra(RecordActivity.NOTE_ID, model.getId());
				startActivity(intent);
			}
		});
	}


	private class ModelAdapter extends ArrayAdapter<RecordModel>{
	
		private int resource_2;
		
		public ModelAdapter(ArrayList<RecordModel> mModels) {
			super(context, 0, mModels);
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			if(convertView == null){
				convertView = NoteListActivity.this.getLayoutInflater().inflate(R.layout.list_item, null);
			}
			
			RecordModel model = getItem(position);
			
			TextView titleTextView = (TextView)convertView.findViewById(R.id.list_title_text);
			titleTextView.setText(model.getTitle());
			
			TextView stringTextView = (TextView)convertView.findViewById(R.id.list_string_text);
			stringTextView.setText(model.getString());
			
			TextView timeTextView = (TextView)convertView.findViewById(R.id.list_time_text);
			timeTextView.setText(model.getDate());
			
			return convertView;
		}
	
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.record, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
        	RecordModel model = new RecordModel(context);
        	ListModel.get(context).addModel(model);
        	Intent intent = new Intent(context, RecordActivity.class);
        	intent.putExtra(NOTE_ID, model.getId());
     //   	intent.putExtra("w", "2");
			startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void update(){
    	listView.setAdapter(adapter);
    }
    
    @Override
    protected void onStart() {
    	super.onStart();
    	update();
    }
}
