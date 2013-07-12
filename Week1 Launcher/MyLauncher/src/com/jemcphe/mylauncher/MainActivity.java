/*
 * James E. McPherson III
 * Mobile Development Frameworks 3
 * Project 1 Launcher
 * Term - 1307
 * 07/11/2013
 */

package com.jemcphe.mylauncher;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {
	
	//Global Variables
	String url;
	EditText urlField;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
    }

    //This function is initiated via activity_mail.xml for button
    public void startIntent (View view) { 
    	//Create the intent
    	Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.espn.com"));
    	//Start the intent
    	startActivity(webIntent);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
