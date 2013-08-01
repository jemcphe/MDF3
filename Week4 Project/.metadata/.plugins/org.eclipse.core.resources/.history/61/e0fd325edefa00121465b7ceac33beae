package com.jemcphe.pictcha2;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

public class JavaScriptInterface {
	Context _context;
	
	JavaScriptInterface (Context context){
		_context = context;
	}
	
    /** Show a toast from the web page */
	@JavascriptInterface
    public void showToast(String toast) {
        Toast.makeText(_context, toast, Toast.LENGTH_SHORT).show();
    }

}
