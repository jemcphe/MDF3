package com.jemcphe.tinybrowser;

import java.net.URL;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.webkit.WebView;

public class MainActivity extends Activity {

    @SuppressWarnings("unused")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Intent intent = getIntent();
        
        Uri dataPassed = intent.getData();
        URL url = null;
        
        try {
        	url = new URL(dataPassed.getScheme(), dataPassed.getHost(), dataPassed.getPath());
        } catch (Exception e) {
        	e.printStackTrace();
        }
        
        WebView webView = (WebView) findViewById(R.id.webView);
        webView.loadUrl(url.toString());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
