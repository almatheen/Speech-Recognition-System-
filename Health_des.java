package com.example.speech_rec;



import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;



import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.speech.RecognizerIntent;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class Health_des extends Activity implements OnClickListener{

	EditText des,timee;
	private ImageButton btnSpeak,btnSpeak1;
	
	Button b;
	String res;
	protected static final int RESULT_SPEECH = 1;
	protected static final int RESULT_SPEECH1 = 2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_health_des);
		des=(EditText)findViewById(R.id.et_desc);
		timee=(EditText)findViewById(R.id.timee);
		b=(Button)findViewById(R.id.btn_setapt);
		btnSpeak = (ImageButton) findViewById(R.id.btnSpeak);
		btnSpeak1=(ImageButton)findViewById(R.id.btnSpeak1);
		
		b.setOnClickListener(Health_des.this);
		btnSpeak.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent intent = new Intent(
						RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

				intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-US");

				try {
					startActivityForResult(intent, RESULT_SPEECH);
					des.setText("");
				} catch (ActivityNotFoundException a) {
					Toast t = Toast.makeText(getApplicationContext(),
							"Opps! Your device doesn't support Speech to Text",
							Toast.LENGTH_SHORT);
					t.show();
				}
			}
		});
		btnSpeak1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent intent = new Intent(
						RecognizerIntent.ACTION_RECOGNIZE_SPEECH);

				intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-US");

				try {
					startActivityForResult(intent, RESULT_SPEECH1);
					timee.setText("");
				} catch (ActivityNotFoundException a) {
					Toast t = Toast.makeText(getApplicationContext(),
							"Opps! Your device doesn't support Speech to Text",
							Toast.LENGTH_SHORT);
					t.show();
				}
			}
		});
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		switch (requestCode) {
		case RESULT_SPEECH: {
			if (resultCode == RESULT_OK && null != data) {

				ArrayList<String> text = data
						.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

				des.setText(text.get(0));
				
			}
			break;
		}
		case RESULT_SPEECH1: {
			if (resultCode == RESULT_OK && null != data) {

				ArrayList<String> text = data
						.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

				timee.setText(text.get(0));
				
			}
			break;
		}
		}
		

		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		int id=v.getId();
		switch(id)
		{
		case R.id.btn_setapt:
			new DesAsyncCall().execute();
			
			break;
		default:
			break;
			
			
		}
		
	}
	public class DesAsyncCall extends AsyncTask<String, Void, Void> {

		@Override
		protected Void doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			res = WebService.description(des.getText().toString(), 
					timee.getText().toString(),Property.id,Ids.uid,Property.name, "Description");

			return null;
			
		}
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
//			Toast.makeText(getApplicationContext(),
//					"registered successfully="+res, Toast.LENGTH_SHORT).show();
			try {
				if (res.equals("true")) {
					Toast.makeText(getApplicationContext(),
							"Appointment  successfully sent", Toast.LENGTH_SHORT).show();
							
					startActivity(new Intent(getApplicationContext(),Hom1.class));
				} else {
					Toast.makeText(getApplicationContext(), res.toString(),
							Toast.LENGTH_SHORT).show();
					startActivity(new Intent(getApplicationContext(),Hom1.class));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}


	

}
	
	public void Image(View v)
	{
		startActivity(new Intent(getApplicationContext(),Sendimage.class));
	}
	}
