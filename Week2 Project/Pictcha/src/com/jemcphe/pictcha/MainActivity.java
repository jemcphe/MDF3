package com.jemcphe.pictcha;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.jemcphe.pictcha.StoreMedia;

public class MainActivity extends Activity implements OnClickListener {

	public Uri imageUri;
	public boolean notifyIcon = true;

	NotificationManager notifyManager;
	Notification notification;

	//Notification ID's
	private final int NOTIFY_SAVE = 1;
	private final int NOTIFY_SUCCESS = 2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		notifyManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		notification = new Notification();

		//Make Button useable
		Button cameraButton = (Button) findViewById(R.id.cameraButton);
		cameraButton.setOnClickListener(this);

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
		Log.i("onClick()", "Button Pressed");

		//Create intent to use existing camera applications
		Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

		//Create Uri using the StoreMedia Class
		imageUri = StoreMedia.getOutputMediaFileUri(StoreMedia.MEDIA_TYPE_IMAGE);
		cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);

		startActivityForResult(cameraIntent, 100);

		notifyFinish();
	}

	//Create function that will give user notification that it is saving an image
	//	public void notifySave() {
	//		
	//		
	//		NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(this)
	//		.setSmallIcon(R.drawable.small_icon)
	//		.setContentTitle("Pictcha Saving Image");
	//
	//		
	//		notification = notifyBuilder.build();
	//		
	//		if(this.notifyIcon) {
	//			notifyManager.notify(NOTIFY_SAVE, notification);
	//		} else {
	//			notifyManager.cancel(NOTIFY_SAVE);
	//		}
	//		
	//	}

	public void scanImages() {
		Intent scanIntent = new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.parse("file://" + Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM)));

		sendBroadcast(scanIntent);
	}

	public void notifyFinish() {

		NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(this)
		.setSmallIcon(R.drawable.small_icon)
		.setContentTitle("Image Saved Successfully");


		notification = notifyBuilder.build();

		if(this.notifyIcon) {
			notifyManager.notify(NOTIFY_SUCCESS, notification);
		} else {
			notifyManager.cancel(NOTIFY_SUCCESS);
		}

	}

}

