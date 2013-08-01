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

@SuppressLint("ValidFragment")
public class JavaScriptInterface {
	Context _context;
	
	public static Uri imageUri;
	
	
	public static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
//	private static final int CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE = 200;
	
//	//Create an Interface to communicate with Activity
//		public interface OnCameraButtonClicked {
//			void cameraHandler(String response);
//		}
//	
//	//Create a private connection to MainActivity method startSearchActivity
//		private OnCameraButtonClicked parentActivity;
//		
//		
//		
//		public void onAttach(Activity activity) {
//			// TODO Auto-generated method stub
//			super.onAttach(activity);
//			
//			if(activity instanceof OnCameraButtonClicked){
//				parentActivity = (OnCameraButtonClicked) activity;
//			}
//			else {
//				throw new ClassCastException(activity.toString() + "must implement onCameraButtonClicked");
//			}
//			
//		}
	
	JavaScriptInterface (Context context){
		_context = context;
	}
	
    /** Show a toast from the web page */
	@JavascriptInterface
    public void showToast(String toast) {
        Toast.makeText(_context, toast, Toast.LENGTH_SHORT).show();
    }

	@JavascriptInterface
	public void startCamera(String response){
		Log.i("startCamera", "Camera Started");
		Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

		//Create Uri using the StoreMedia Class
		JavaScriptInterface.imageUri = StoreMedia.getOutputMediaFileUri(StoreMedia.MEDIA_TYPE_IMAGE);
		cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, JavaScriptInterface.imageUri);
		
		((Activity) _context).startActivityForResult(cameraIntent, JavaScriptInterface.CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
		
//		startActivityForResult(cameraIntent, JavaScriptInterface.CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
	}
	
}
