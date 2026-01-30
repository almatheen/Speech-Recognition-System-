package com.example.speech_rec;



import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Location extends Activity {
	WebView webView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_location);
		//Intent intent = getIntent();
		//String   message= intent.getStringExtra("message");
		
		String message=Property.id;
		//Bundle bundle = getIntent().getExtras();
		//String message = bundle.getString("message");

	     	String lang=Ids.longi;
	     	String lat=Ids.lat;
		
		 webView = (WebView) findViewById(R.id.webView);
		 WebSettings webSettings = webView.getSettings();
		 webSettings.setBuiltInZoomControls(true);
		 webView.getSettings().setJavaScriptEnabled(true);
         webView.setWebViewClient(new MyWebViewClient());
		 String url = "http://192.168.43.114:8084/Speech_Recognization/test22.jsp?id="+Property.id+"&latsrc="+lat+"&langsrc="+lang+"";
		
		 webView.loadUrl(url);	
		
	}

      private class MyWebViewClient extends WebViewClient
    {
   	    @Override
	    public boolean shouldOverrideUrlLoading(WebView view, String url) {
	        view.loadUrl(url);
	        return true;
	    }
    }
}