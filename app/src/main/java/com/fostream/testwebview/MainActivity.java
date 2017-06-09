package com.fostream.testwebview;



import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
    	WebView webBrowser = null;	
		webBrowser = (WebView) findViewById(R.id.webViewMain);
		//webBrowser.setWebViewClient(new SofosWebViewClient(this));	
		webBrowser.setWebChromeClient(new WebChromeClient());		

		// Gestion du javascript dans le webViews
		WebSettings webSettings = webBrowser.getSettings();
		webSettings.setSaveFormData(false);
		webSettings.setJavaScriptEnabled(true);
		webSettings.setNeedInitialFocus(true);		
		webSettings.setAllowFileAccess(true);	
		webSettings.setBuiltInZoomControls(false); 
		// pour 4.1.2
		//webSettings.setAllowUniversalAccessFromFileURLs(true);
		if(android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1){ 
			fixJellyBeanIssues(webSettings);
			// ajout pour 4.4
			webSettings.setAllowFileAccessFromFileURLs(true);
			webSettings.setAllowUniversalAccessFromFileURLs(true);
			 
		}
	
		webBrowser.clearCache(true);
		//webBrowser.loadUrl(currentURL);
		webBrowser.loadUrl("http://192.168.1.11/SofosAndroid/player/vod/sofosplayermini.html");
		
		// webview is transparent to display html information when player is fullscreen
		/*
		webBrowser.setBackgroundColor(Color.TRANSPARENT); 
		if(android.os.Build.VERSION.SDK_INT > 10)
			setLayerTransparent(webBrowser);		
*/
		// pour debug
		  if (Build.VERSION.SDK_INT >= 19) {
			  WebviewDebug();
		  }
		  
    }
    
	@TargetApi(16)
	protected void fixJellyBeanIssues(WebSettings webSettings ) {
		try {
			webSettings.setAllowUniversalAccessFromFileURLs(true);
		} catch(NullPointerException e) {
			//System.out.println(e.toString());
		}
	}
	
	@TargetApi(11) 
	protected void setLayerTransparent(WebView webBrowser ) {	
		webBrowser.setLayerType(WebView.LAYER_TYPE_SOFTWARE, null);
	}
	
	@TargetApi(19)
	protected void WebviewDebug() {
		try {
		      WebView.setWebContentsDebuggingEnabled(true);
		} catch(NullPointerException e) {
			//System.out.println(e.toString());
		}
	}	
}
