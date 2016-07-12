package com.example.note;

import java.util.ArrayList;
import java.util.UUID;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class RecordActivity extends Activity {

	private EditText editText;
	private Button cancel_button;
	private Button more_button;
	private Button finish_button;
	
	public static final String NOTE_ID = "com.example.note.id";
	private RecordModel model;
	private ArrayList<RecordModel> models;
	private Context context = RecordActivity.this;
	
	private Handler handler = new Handler(){
		public void handleMessage(Message message) {
			if(message.what == 1){
				ifDialog();
			}
		}
	};
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        
        editText = (EditText)findViewById(R.id.editText);
        cancel_button = (Button)findViewById(R.id.cancel_button);
        more_button = (Button)findViewById(R.id.more_button);
        finish_button = (Button)findViewById(R.id.finish_button);
        cancel_button.setOnClickListener(listener);
        more_button.setOnClickListener(listener);
        finish_button.setOnClickListener(listener);
        
        UUID modelId = (UUID)RecordActivity.this.getIntent().getSerializableExtra(NOTE_ID);
        model = ListModel.get(context).getModel(modelId);
        
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
        	RecordActivity.this.getActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
    
    OnClickListener listener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.cancel_button:
				
				break;
				
			case R.id.more_button:
					Toast.makeText(RecordActivity.this, "more!!!!", Toast.LENGTH_SHORT).show();
				break;
							
			case R.id.finish_button:
				
				break;
			default:
				break;
			}
		}
	};

	
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch (item.getItemId()) {
		case android.R.id.home:
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					Message message = new Message();
					message.what = 1;
					handler.sendMessage(message);
				}
			}).start();
			//Intent intent = new Intent(context, NoteListActivity.class);
			//startActivity(intent);
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
		
	}
	
	private void ifDialog() {
		Log.d("wangbin", "这里应该提示没有设置标题");
		AlertDialog.Builder builder  = new Builder(RecordActivity.this);
		builder.setTitle("提示" ) ;
		builder.setMessage("未设置标题，是否不设置退出？" ) ;
		builder.setPositiveButton("是" ,  null );
		builder.setNegativeButton("否", null);
		builder.show();
	}

	
	@Override
	protected void onPause() {
		super.onPause();
		Log.d("wangbin", "暂停被调用");
	}

	
	@Override
	protected void onStop() {
		super.onStop();
		Log.d("wangbin", "Stop被调用");
	}

	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.d("wangbin", "销毁被调用");
	}
    
	
}
