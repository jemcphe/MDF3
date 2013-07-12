/*
 * James E. McPherson III
 * Mobile Development Frameworks 3
 * Project 1
 * Term - 1307
 * 07/11/2013
 */

package com.jemcphe.tinybrowser;

import java.net.URL;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements OnClickListener{

	//Global Variables
	WebView webView;
	String urlString;
	EditText webAddress;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
	//Grab the intent being sent to this application	
    Intent myIntent = getIntent();
    
    //Define the Edit Text Field
    webAddress = (EditText) findViewById(R.id.addressBar);
    //Set Hint for user
    webAddress.setHint("Enter Web Address Here");
    
    
      
    //Create Nav Buttons
    Button goButton = (Button) findViewById(R.id.goButton);
    Button backButton = (Button) findViewById(R.id.backButton);
    Button forwardButton = (Button) findViewById(R.id.forwardButton);
    Button refreshButton = (Button) findViewById(R.id.refreshButton);
    
    //setOnClickListeners
    goButton.setOnClickListener(this);
    backButton.setOnClickListener(this);
    forwardButton.setOnClickListener(this);
    refreshButton.setOnClickListener(this);

    //Create Uri from intent data
   	Uri webData = myIntent.getData();
  	URL url = null;
    
   	try {
   	 	url = new URL(webData.getScheme(), webData.getHost(),
   	 		webData.getPath());
  	  } catch (Exception e) {
   	 	e.printStackTrace();
   	}
    
   	/*
   	 * Define WebView, enable javascript, and setWebViewClient so other browsers
   	 * won't steal my thunder!!
   	 */
  	webView = (WebView) findViewById(R.id.webView); 
  	webView.getSettings().setJavaScriptEnabled(true);
  	webView.setWebViewClient(new WebViewClient() {
  		public boolean shouldOverrideUrlLoading (WebView view, String url) {
			
  			view.loadUrl(url);
  			return true;
  			
  		}
  	});
  	//Load the URL passed from intent
  	webView.loadUrl(url.toString());
   	
}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		//Switch through id's
		switch(v.getId()){
		//Go Button
		case R.id.goButton:
			//Load user input from webAddress Field
			webView.loadUrl("http://" + webAddress.getText().toString());
			break;
		//Back Button
		case R.id.backButton:
			if (webView.canGoBack()){
				webView.goBack();
			}
			break;
		//Forward Button
		case R.id.forwardButton:
			if (webView.canGoForward()){
				webView.goForward();
			}
			break;
		//Refresh Button
		case R.id.refreshButton:
			webView.reload();
			break;
		default:
			break;
		
		}
	}

}
