package com.example.speech_rec;

import java.util.regex.Matcher;
import java.util.regex.Pattern;



import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends Activity implements OnClickListener{
	EditText name,email,pass,age,mobile;
	Button rbtn;
	 String res;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		name=(EditText)findViewById(R.id.name_et);
		email=(EditText)findViewById(R.id.email_et);
		pass=(EditText)findViewById(R.id.pass_et);
		age=(EditText)findViewById(R.id.age_et);
		mobile=(EditText)findViewById(R.id.mobile_et);
		rbtn=(Button)findViewById(R.id.reg_btn);
		
		rbtn.setOnClickListener(Register.this);
	}



	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int id=v.getId();
		switch(id)
		{
		case R.id.reg_btn:
//			String userName = name.getText().toString();
//			String password = pass.getText().toString();
//			
//			String phone = mobile.getText().toString();
//			String email1= email.getText().toString();
//			String age1=age.getText().toString();
			
			

			if (null == name || name.length() == 0) {
				AlertDialog alertDialog = new AlertDialog.Builder(
						Register.this).create();
				alertDialog.setTitle("oops!");
				alertDialog.setMessage("Username field is empty");
				alertDialog.setButton2("Ok",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								// dismiss the dialog
							}
						});
				alertDialog.show();
			} else if (null == pass || pass.length() == 0) {
				AlertDialog alertDialog = new AlertDialog.Builder(
						Register.this).create();
				alertDialog.setTitle("oops!");
				alertDialog.setMessage("Password field is empty");
				alertDialog.setButton2("Ok",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								// dismiss the dialog
							}
						});
				alertDialog.show();
			} else if (null == email || email.length() == 0) {
				AlertDialog alertDialog = new AlertDialog.Builder(
						Register.this).create();
				alertDialog.setTitle("oops!");
				alertDialog.setMessage("Email field is empty");
				alertDialog.setButton2("Ok",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								// dismiss the dialog
							}
						});
				alertDialog.show();
			} else if (null == mobile || mobile.length() == 0) {
				AlertDialog alertDialog = new AlertDialog.Builder(
						Register.this).create();
				alertDialog.setTitle("oops!");
				alertDialog.setMessage("mobile field is empty");
				alertDialog.setButton2("Ok",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								// dismiss the dialog
							}
						});
				alertDialog.show();
			} else if (mobile.length() < 10 || mobile.length() > 10) {
				AlertDialog alertDialog = new AlertDialog.Builder(
						Register.this).create();
				alertDialog.setTitle("oops!");
				alertDialog.setMessage("Invalid mobile number");
				alertDialog.setButton2("Ok",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								// dismiss the dialog
							}
						});
				alertDialog.show();
			}
			else if (age.length() < 0 || age.length() > 120) {
				AlertDialog alertDialog = new AlertDialog.Builder(
						Register.this).create();
				alertDialog.setTitle("oops!");
				alertDialog.setMessage("Invalid Age");
				alertDialog.setButton2("Ok",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								// dismiss the dialog
							}
						});
				alertDialog.show();
			}else if (!isValidEmail(email.getText().toString())) {
				AlertDialog alertDialog = new AlertDialog.Builder(
						Register.this).create();
				alertDialog.setTitle("oops!");
				alertDialog.setMessage("Invalid email address");
				alertDialog.setButton2("Ok",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								// dismiss the dialog
							}
						});
				alertDialog.show();
			} else {
				// Toast.makeText(getApplicationContext(),
				// "registration called",
				// Toast.LENGTH_SHORT).show();

				new RegisterAsyncCall().execute();
				
			}

			break;
		default:
			break;
		
		}
	} 
	public class RegisterAsyncCall extends AsyncTask<String, Void, Void> {

		@Override
		protected Void doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			res = WebService.register(name.getText().toString(), pass
					.getText().toString(), email.getText().toString(),
					mobile.getText().toString(),age.getText().toString(), "Registration");
//			System.out.println(res+" :register");
			return null;
			
		}
		

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
//			Toast.makeText(getApplicationContext(),
//					"registered successfully="+res, Toast.LENGTH_SHORT).show();
			try {
				if (res.equals("true")) {
					Toast.makeText(getApplicationContext(),
							"registered successfully", Toast.LENGTH_SHORT).show();
							
					startActivity(new Intent(getApplicationContext(),Login.class));
				} else {
					Toast.makeText(getApplicationContext(), res.toString(),
							Toast.LENGTH_SHORT).show();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	private boolean isValidEmail(String uemail) {
		String EMAIL_PATTERN = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +

		"\\@" +

		"[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +

		"(" +

		"\\." +

		"[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +

		")+";
		// String EMAIL_PATTERN="^[_A-Za-z\\+]";
		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(uemail);
		return matcher.matches();
	}

}
