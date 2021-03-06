package com.jemcphe.pictcha2;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.widget.Toast;
/*
 * This class will be responsible for binding code Java code with Javascript code.
 * All of the Methods in this class are being called via javascript from index.html
 * located in the assets/www folder.
 */
@SuppressLint("ValidFragment")
public class JavaScriptInterface {
	//Global Variables
	Context _context;
	public static Uri imageUri;
	public static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
	
	//Set the Context of the JavaScriptInterface
	JavaScriptInterface (Context context){
		_context = context;
	}
	
	/*
	 * When Targeting Android SDK 17 or higher, you must implement
	 * @JavascriptInterface to allow the Java to bind correctly to 
	 * Javascript.
	 * 
	 * This Method is attached to a Target Button that will initiate the
	 * Native camera.  Using an Intent to initiate and starting the activity for result.
	 */
	@JavascriptInterface
	public void startCamera(String response){
		Log.i("startCamera", "Camera Started");
		//Create the Intent
		Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

		//Create Uri using the StoreMedia Class
		JavaScriptInterface.imageUri = StoreMedia.getOutputMediaFileUri(StoreMedia.MEDIA_TYPE_IMAGE);
		cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, JavaScriptInterface.imageUri);
		
		/*
		 * startActivityForResult will start the intent.  In order to use startActivityForResult, 
		 * It must have an Activity to refer to.  The following allowed me to do this most effectively.
		 */
		((Activity) _context).startActivityForResult(cameraIntent, JavaScriptInterface.CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
		
	}
	
}
