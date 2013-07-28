package com.jemcphe.teamwidget;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RemoteViews;

public class WidgetActivity extends Activity {

	int widgetId;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
//		Button button = (Button) findViewById(R.id.button1);
//		button.setOnClickListener(new OnClickListener() {
//			
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				Log.i("Button", "Clicked");
//				
//			}
//		});
		Bundle extras = getIntent().getExtras();
		Log.i("BUNDLE", "CHECKING EXTRAS");
		if (extras != null) {
			Log.i("extras", "not null");
			widgetId = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
			onWidget();
		} 
	}
	
	public void onWidget() {

        if (widgetId != AppWidgetManager.INVALID_APPWIDGET_ID) {
            RemoteViews remoteViews = new RemoteViews(this.getPackageName(), R.layout.widget_layout);

            AppWidgetManager.getInstance(this).updateAppWidget(widgetId, remoteViews);

            Intent resultValue = new Intent();
            resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);
            setResult(RESULT_OK, resultValue);
            finish();
        }
    }

}
