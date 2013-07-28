package com.jemcphe.teamwidget;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.sax.StartElementListener;
import android.util.Log;
import android.widget.RemoteViews;

public class WidgetProvider extends AppWidgetProvider {

	
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		Log.i("onUpdate", "Started");
		ComponentName thisWidget = new ComponentName(context, WidgetProvider.class);
		int [] allWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);
		
		for (int widgetId : allWidgetIds){
			
			RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
			
			remoteViews.setTextViewText(R.id.team1, "Texas Rangers");
			remoteViews.setTextViewText(R.id.team2, "Oakland Athletics");
			remoteViews.setTextViewText(R.id.runs1, "11");
			remoteViews.setTextViewText(R.id.runs2, "24");
			
			appWidgetManager.updateAppWidget(widgetId, remoteViews);
			
			Intent dataIntent = new Intent(context.getApplicationContext(), DataService.class);
			dataIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, allWidgetIds);
			//dataIntent.putExtra(DataService.TEAM_KEY, SearchFragment.field.getText().toString());
			Log.i("Service", "Started");
			context.startService(dataIntent);
			
		}
		
		
	}
	
	/*
	 * this service was created to grab the JSON from the erikberg API and save
	 * it to local storage. This makes the app useable when the there is no data
	 * connection... provided it is not a first time use.  In which case, the user
	 * will be notified that they must have a data connection.
	 */
//	public void grabData(){
//
//		//HANDLE DATA FROM SERVICE
//		Handler dataHandler = new Handler() {
//
//			@Override
//			public void handleMessage(Message msg) {
//				// TODO Auto-generated method stub
//				String response = null;
//				//CHECK FOR PROPER SERVICE COMPLETION
//				if (msg.arg1 ==  RESULT_OK) {
//
//					try {
//						//TELL DEBUGGER THAT SERVICE HAS FINISHED
//						response = "Service Finished";
//						Log.i("Service Status", response);
//
//						//Parse uri and use getContentResolver
//						String uriString = TeamProvider.TeamData.CONFERENCE_URI.toString() + "AL";
//						Uri uri = Uri.parse(uriString);
//						Cursor dataCursor = getContentResolver().query(uri, TeamProvider.TeamData.PROJECTION, null, null, null);
//
//						if(dataCursor.moveToFirst() == true){
//							ArrayList<HashMap<String, String>> teamList = new ArrayList<HashMap<String, String>>();
//
//							for (int i = 0; i<dataCursor.getCount(); i++){
//
//								//Create HashMap for data
//								HashMap<String, String> displayMap = new HashMap<String, String>();
//								displayMap.put("team", dataCursor.getString(1));
//								displayMap.put("conference", dataCursor.getString(2));
//								displayMap.put("wins", dataCursor.getString(3));
//								displayMap.put("losses", dataCursor.getString(4));
//
//								dataCursor.moveToNext();
//
//								teamList.add(displayMap);
//							}
//
//							/* Set up the Adapter
//							SimpleAdapter adapter = new SimpleAdapter(_context, teamList, R.layout.list_row, 
//									new String[] {"team", "conference", "wins", "losses"}, new int[] {R.id.team,R.id.conference, R.id.wins, R.id.losses});
//							Instantiate the Adapter
//							_listView.setAdapter(adapter);
//							 */
//
//						} else {
////							Toast toast = Toast.makeText(_context, "You must enter a valid team. Go back and try again", Toast.LENGTH_LONG);
////							toast.show();
//						}
//					}
//					catch (Exception e){
//						/*
//						 * TELL THE USER THAT THEY NEED TO ENTER AN INVALID TEAM NAME
//						 * OR THEY NEED TO BE CONNECTED TO INTERNET FOR TEAM INFORMATION
//						 */
////						Toast toast = Toast.makeText(_context, "Please Enter A Valid Team Name Or Try Connecting To Internet For This Team's Information", Toast.LENGTH_LONG);
////						toast.show();
//
//						Log.e("", e.getMessage().toString());
//					}
//				}	
//			}
//		};
//		
//		//CREATE MESSENGER
//		Messenger dataMessenger = new Messenger(dataHandler);
//
//		/*
//		 * CREATE INTENT & PUT MESSENGER_KEY & TEAM_KEY TO BE
//		 * PASSED TO THE DATASERVICE CLASS AND INITIATE THE INTENT
//		 */
//		Intent dataIntent = new Intent(, DataService.class);
//		dataIntent.putExtra(DataService.MESSENGER_KEY, dataMessenger);
//		//dataIntent.putExtra(DataService.TEAM_KEY, SearchFragment.field.getText().toString());
//		startService(dataIntent);
//		
//	}
}
