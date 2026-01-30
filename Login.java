package com.example.speech_rec;




import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends Activity implements OnClickListener{
	EditText email,pass;
	TextView register;
	Button blogin;
	String res;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		email=(EditText)findViewById(R.id.et_email);
		pass=(EditText)findViewById(R.id.et_pass);
		register=(TextView)findViewById(R.id.register);
		blogin=(Button)findViewById(R.id.blogin);
		
		blogin.setOnClickListener(this);
		register.setOnClickListener(this);
		
		
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int id=v.getId();
		switch(id)
		{
		case R.id.blogin:
			
			new LoginAsyncCall().execute();
			Toast.makeText(getApplicationContext(), "login", Toast.LENGTH_SHORT).show();
			break;
		case R.id.register:
			startActivity(new Intent(this,Register.class));
		}
		
		
	}
	public class LoginAsyncCall extends AsyncTask<String, Void, Void> {

		@Override
		protected Void doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			res = WebService.login(email.getText().toString(), pass.getText()
					.toString(), "Login");
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			try {
				if (res.equals("false")) {
					Toast.makeText(getApplicationContext(), "login failed",
							Toast.LENGTH_SHORT).show();

				} else {
					Toast.makeText(getApplicationContext(), "login success",
							Toast.LENGTH_SHORT).show();
					
					String s = res;
//					Toast.makeText(getApplicationContext(),s,
//							Toast.LENGTH_SHORT).show();
					Ids.uid = s;
					//UserData.id = s;
					startActivity(new Intent(getApplicationContext(), Hom1.class));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
