package com.example.note;

import java.util.ArrayList;
import java.util.UUID;

import android.R.string;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class RecordActivity extends Activity {

	private EditText editText;
	private Button cancel_button;
	private Spinner more_spinner;
	private Button finish_button;
	
	public static final String NOTE_ID = "com.example.note.id";
	private RecordModel model;
	private ArrayList<RecordModel> models;
	private Context context = RecordActivity.this;
	
	private static final int FUNCTION_IFDIALOG = 1;
	private static final int DIALOG_YES = 2;
	private static final int DIALOG_NO = 3;
	private static final int DIALOG_TITLE = 4;
	private static final int DIALOG_TIME = 5;
	private EditText editText_dialog;
	
	private Handler handler = new Handler(){
		public void handleMessage(Message message) {
			switch (message.what) {
			case 1:
				ifDialog();
				break;
			case 2:
				Log.d("wangbin", "δ���ñ���");
				model.setTitle("δ���ñ���");
				Intent intent1 = new Intent(context, NoteListActivity.class);
				startActivity(intent1);
				break;
			case 3:
				Log.d("wangbin", "���ñ���");
				model.setTitle(editText_dialog.getText().toString());
				Intent intent2 = new Intent(context, NoteListActivity.class);
				startActivity(intent2);
				break;
				
			case 4:
				Log.d("wangbin", "Spinner���ñ���");
				final EditText editText_title_dialog;
				AlertDialog.Builder builder  = new Builder(RecordActivity.this);
				builder.setTitle("��������⣺" ) ;
				editText_title_dialog = new EditText(context);
				builder.setView(editText_title_dialog);
				builder.setPositiveButton("ȷ��" ,  new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						model.setTitle(editText_title_dialog.getText().toString());
						Toast.makeText(context, "���ñ���ɹ���", 1000);
					}
				} );
				builder.show();
				break;
			case 5:
				Log.d("wangbin", "Spinner����ʱ��");
				break;
			default:
				break;
			}
		}
	};
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        
        editText = (EditText)findViewById(R.id.editText);
        cancel_button = (Button)findViewById(R.id.cancel_button);
        finish_button = (Button)findViewById(R.id.finish_button);
        cancel_button.setOnClickListener(listener);
        finish_button.setOnClickListener(listener);
        more_spinner = (Spinner)findViewById(R.id.spinner);
        more_spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, 
                    int pos, long id) {
            	Log.d("wangbin", "�����Spinner");
            	switch (pos) {
				case 0:
					Message message = new Message();
					message.what = DIALOG_TITLE;
					handler.sendMessage(message);
					break;
				case 1:
					Message message1 = new Message();
					message1.what = DIALOG_TIME;
					handler.sendMessage(message1);
					break;
				case 2:
					Toast.makeText(context, "�������þ����ڴ���", 1500);
					break;
				default:
					break;
				}
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });
        
        UUID modelId = (UUID)RecordActivity.this.getIntent().getSerializableExtra(NOTE_ID);
    //    String i = (String)RecordActivity.this.getIntent().getSerializableExtra("w");
     //   Log.d("wangbin", i);
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
		
		if(model.getTitle() == ""){
			
			Log.d("wangbin", "����Ӧ����ʾû�����ñ���");
			AlertDialog.Builder builder  = new Builder(RecordActivity.this);
			builder.setTitle("��ʾ" ) ;
			builder.setMessage("δ���ñ��⣬�Ƿ������˳���" ) ;
			builder.setPositiveButton("��" ,  new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
				//	Log.d("wangbin", "�����ޱ���");
					Message message = new Message();
					message.what = 2;
					handler.sendMessage(message);
					
				}
			} );
			builder.setNegativeButton("����", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					Log.d("wangbin", "����Ӧ��������һ���Ի����");
					AlertDialog.Builder builder1  = new Builder(RecordActivity.this);
					builder1.setTitle("��������⣺" ) ;
					editText_dialog = new EditText(context);
					builder1.setView(editText_dialog);
					builder1.setPositiveButton("���" ,  new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							Message message = new Message();
							message.what = 3;
							handler.sendMessage(message);
						}
					} );
					builder1.show();
				}
			});
			builder.show();
				
		} 
		else {
			Intent intent = new Intent(context, NoteListActivity.class);
			startActivity(intent);
		}
		
	}

	
	@Override
	protected void onPause() {
		super.onPause();
		Log.d("wangbin", "��ͣ������");
	}

	
	@Override
	protected void onStop() {
		super.onStop();
		Log.d("wangbin", "Stop������");
	}

	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.d("wangbin", "���ٱ�����");
	}
    
	
}
