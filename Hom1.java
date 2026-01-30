package com.example.speech_rec;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Hom1 extends Activity implements OnClickListener{
	Button b1,b2,b3;

	double latitude; // latitude
	double longitude; // longitude
	String lang,lat;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hom1);
		b1=(Button)findViewById(R.id.bt_doctors);
		b2=(Button)findViewById(R.id.bt_appoinment);
		b3=(Button)findViewById(R.id.bt_prescription);
		
		b1.setOnClickListener(Hom1.this);
		b2.setOnClickListener(Hom1.this);
		b3.setOnClickListener(Hom1.this);
		
		   GPSTracker  gps= new GPSTracker(Hom1.this);
			
			if(gps.canGetLocation()){
	           	
	  	         
	  	       
	  	        latitude = gps.getLatitude();
	  	        longitude = gps.getLongitude();
	  	       
	  	       
	  	        
	  	       lang= String.valueOf(longitude);
	  	       lat= String.valueOf(latitude);
	  	       
	  	     Ids.longi=lang;
	  	   Ids.lat=lat;
	  	        
	  	    
	  	       
	  	       // tts.speak("Now You are at "+add+" Location", TextToSpeech.QUEUE_FLUSH, null);
	  	        	
	  	        	Toast.makeText(getApplicationContext(), "Your Location is - \n Lat: " + latitude + "\n Long : " + longitude+"\n Adress :", Toast.LENGTH_LONG).show();	
	  	        }

			
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int id=v.getId();
		switch(id)
		{
		case R.id.bt_doctors:
			startActivity(new Intent(getApplicationContext(),Home.class));
			break;
		case R.id.bt_appoinment:
			startActivity(new Intent(getApplicationContext(),Appointment1.class));
		break;
		case R.id.bt_prescription:
			startActivity(new Intent(getApplicationContext(),Prescription1.class));
			break;
		}
		
		
	}

}
