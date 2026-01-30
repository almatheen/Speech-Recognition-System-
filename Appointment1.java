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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Appointment1 extends Activity {
	String res;
	Intent intent;
	ListView list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_appointment1);
		new AsyncCallW().execute();
	}
	private class AsyncCallW extends AsyncTask<String, Void, Void>
	{

		@Override
		protected Void doInBackground(String... params) {
			// TODO Auto-generated method stub
			res=WebService.find_doctors1(Ids.uid,"find_doctors1");
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
		        	
		        	 user.add(obj.getString("id")+")  "+obj.getString("name"));
		        	 
		         }
				
		        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.list_view,user);
					
					list=(ListView)findViewById(R.id.listView_doc); 
				   	list.setAdapter(adapter);
				   	list.setOnItemClickListener(new OnItemClickListener() {

						@Override
						public void onItemClick(AdapterView<?> arg0,
								View view, int arg2, long arg3) {
							
							String s=(String) ((TextView) view).getText();
					
							Property.id=s;
							  Toast.makeText(getApplicationContext(),s, Toast.LENGTH_SHORT).show();
							 
						  intent=new Intent(getApplicationContext(),Appointment.class); 
				        
					      startActivity(intent);
						}
					});
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
		getMenuInflater().inflate(R.menu.appointment1, menu);
		return true;
	}

}
