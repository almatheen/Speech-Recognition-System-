package com.example.speech_rec;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;



import android.media.JetPlayer.OnJetEventListener;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Appointment extends Activity implements OnClickListener {
	String res;
	String dname,apt_time,status;
	TextView t1,t2,t3;
	Button b1,b2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_appointment);
		t1=(TextView)findViewById(R.id.dname);
		t2=(TextView)findViewById(R.id.apt_time);
		t3=(TextView)findViewById(R.id.status);
		b1=(Button)findViewById(R.id.ok);
		b2=(Button)findViewById(R.id.cancel);
		b1.setOnClickListener(Appointment.this);
		b2.setOnClickListener(Appointment.this);
		Toast.makeText(getApplicationContext(), Property.id, Toast.LENGTH_LONG).show();
		new AsyncCallW().execute();
	}
	private class AsyncCallW extends AsyncTask<String, Void, Void>
	{
		
		
		@Override
		protected Void doInBackground(String... params) {
			res = WebService.apt_details(Property.id,Ids.uid,"Appointment");
			return null;
		}
		@Override
		protected void onPostExecute(Void result)
		{
			
		
			
			try {
				if(res.equals("false"))
				{
					Toast.makeText(getApplicationContext(), "not able to get info", Toast.LENGTH_SHORT).show();
					
				}
				else
				{
					
		         JSONArray jsonArray=new JSONArray(res);
					
		         ArrayList<String> user=new ArrayList<String>();
		         for(int i=0;i<jsonArray.length();i++)
		         {
		        	 JSONObject obj=jsonArray.getJSONObject(i);
		        	
		        	 user.add(obj.getString("name"));
		        	 
		        	  dname=obj.getString("name");
		        	 // Property.name=name;
		        	  apt_time=obj.getString("time");
		        	  status=obj.getString("status");
		        
		        	  
		        
		        	 t1.setText(dname);
		        	 t2.setText(apt_time);
		        	 t3.setText(status);
		        	
		         }
				
		      
			}
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.appointment, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int id=v.getId();
		switch(id)
		{
		case R.id.ok:
			startActivity(new Intent(getApplicationContext(),Hom1.class));
			break;
		case R.id.cancel:
			new CancelAsyncCallW().execute();
			
		}
		
	}
	private class CancelAsyncCallW extends AsyncTask<String, Void, Void>
	{
		
		
		@Override
		protected Void doInBackground(String... params) {
			res = WebService.cancel_apt(Property.id,Ids.uid,"cancel_apt");
			return null;
		}
		@Override
		protected void onPostExecute(Void result)
		{
			try {
				if(res.equals("false"))
				{
					Toast.makeText(getApplicationContext(), "not able to get info", Toast.LENGTH_SHORT).show();
					
				}
				else
				{
					
					Toast.makeText(getApplicationContext(), "Appointment Cancelled",
							Toast.LENGTH_SHORT).show();
					
		        	startActivity(new Intent(getApplicationContext(),Hom1.class));
		         }
				
		      
			
			}catch(Exception e)
			{
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
