package com.example.speech_rec;



import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Doctor_details extends Activity {
	String res;
	String name,spcl,qual,exp,hos,mob,time1,time2,time3;
	TextView t1,t2,t3,t4,t5,t6,t7,t8,t9;
	Button b1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_doctor_details);
		t1=(TextView)findViewById(R.id.name);
		t2=(TextView)findViewById(R.id.specialization);
		t3=(TextView)findViewById(R.id.qualificaation);
		t4=(TextView)findViewById(R.id.expereince);
		t5=(TextView)findViewById(R.id.hospital);
		t6=(TextView)findViewById(R.id.mobile);
		t7=(TextView)findViewById(R.id.apt1);
		t8=(TextView)findViewById(R.id.time2);
		t9=(TextView)findViewById(R.id.time3);
		

	}


	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
	}

}
