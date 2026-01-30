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

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Doc_details extends Activity implements OnClickListener{
	String res;
	String name,spcl,qual,exp,hos,mob,time1,time2,time3;
	TextView t1,t2,t3,t4,t5,t6,t7,t8,t9;
	Button b1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_doc_details);
		t1=(TextView)findViewById(R.id.name);
		t2=(TextView)findViewById(R.id.specialization);
		t3=(TextView)findViewById(R.id.qualificaation);
		t4=(TextView)findViewById(R.id.expereince);
		t5=(TextView)findViewById(R.id.hospital);
		t6=(TextView)findViewById(R.id.mobile);
		t7=(TextView)findViewById(R.id.tim1);
		t8=(TextView)findViewById(R.id.tim2);
		t9=(TextView)findViewById(R.id.tim3);
		b1=(Button)findViewById(R.id.aptbutton);
		b1.setOnClickListener(this);
		
		
		new AsyncCallW().execute();
		
	}
	


	private class AsyncCallW extends AsyncTask<String, Void, Void>
	{
		
		
		@Override
		protected Void doInBackground(String... params) {
			res = WebService.doc_details(Property.id,"doc_details");
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
		        	 
		        	  name=obj.getString("name");
		        	 Property.name=name;
		        	  name=obj.getString("name");
		        	  spcl=obj.getString("specialization");
		        	  qual=obj.getString("qualification");
		        	  exp=obj.getString("experience");
		        	  hos=obj.getString("hospital");
		        	  mob=obj.getString("mobile");
		        	  time1=obj.getString("time1");
		        	  time2=obj.getString("time2");
		        	  time3=obj.getString("time3");
		        	  
		        
		        	 t1.setText(name);
		        	 t2.setText(spcl);
		        	 t3.setText(qual);
		        	 t4.setText(exp);
		        	 t5.setText(hos);
		        	 t6.setText(mob);
		        	 t7.setText(time1);
		        	 t8.setText(time2);
		        	 t9.setText(time3);
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
Toast.makeText(getApplicationContext(), "Appointment  ", Toast.LENGTH_SHORT).show();
startActivity(new Intent(getApplicationContext(),Health_des.class));
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
	}
	
	public void Viewmap(View v)
	{
		startActivity(new Intent(getApplicationContext(),Location.class));
	}
	


	

}
