package com.example.speech_rec;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Prescription extends Activity implements OnClickListener {
	TextView t1,t2,t3,t4;
	EditText et;
	String res;
	String nam1,date,et_tab;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_prescription);
		t1=(TextView)findViewById(R.id.nam1);
		t2=(TextView)findViewById(R.id.date);
		t4=(TextView)findViewById(R.id.pre_tab);
		//et=(EditText)findViewById(R.id.et_tab);
		t3=(TextView)findViewById(R.id.comment1);
		t3.setOnClickListener(Prescription.this);
		new AsyncCallW().execute();
	}

	
		
	
	private class AsyncCallW extends AsyncTask<String, Void, Void>
	{

		@Override
		protected Void doInBackground(String... params) {
			// TODO Auto-generated method stub
			res = WebService.prescription(Property.id,Ids.uid,"Prescription");
			return null;
		}
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
		        	 
		        	  nam1=obj.getString("name");
		        	 // Property.name=name;
		        	  et_tab=obj.getString("tablet");
		        	  date=obj.getString("date");
		        
		        	  
		        
		        	 t1.setText(nam1);
		        	 t2.setText(date);
		        	 t4.setText(et_tab);
		        	
		         }
				
		      
			}
			}catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int id=v.getId();
		switch(id)
		{
		case R.id.comment1:
			startActivity(new Intent(getApplicationContext(),Comment.class));
		}
		
		
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
	}

}
