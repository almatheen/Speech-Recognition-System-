package com.example.speech_rec;

import com.example.speech_rec.Health_des.DesAsyncCall;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Comment extends Activity implements OnClickListener{
	EditText et;
	Button b;
	String res;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_comment);
		et=(EditText)findViewById(R.id.review);
		b=(Button)findViewById(R.id.review_ok);
		b.setOnClickListener(Comment.this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int id=v.getId();
		switch(id)
		{
		case R.id.review_ok:
			new AsyncCallW().execute();
		
		}
		
	}
	private class AsyncCallW extends AsyncTask<String, Void, Void>
	{

		@Override
		protected Void doInBackground(String... params) {
			// TODO Auto-generated method stub
			res = WebService.comment(et.getText().toString(), 
					Property.id,Ids.uid,Property.name, "Comment");

			return null;
		
		}
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
//			Toast.makeText(getApplicationContext(),
//					"registered successfully="+res, Toast.LENGTH_SHORT).show();
			try {
				if (res.equals("true")) {
					Toast.makeText(getApplicationContext(),
							"Review Submited", Toast.LENGTH_SHORT).show();
							
					startActivity(new Intent(getApplicationContext(),Prescription.class));
				} else {
					Toast.makeText(getApplicationContext(), res.toString(),
							Toast.LENGTH_SHORT).show();
					startActivity(new Intent(getApplicationContext(),Prescription.class));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
	}

	
	}


