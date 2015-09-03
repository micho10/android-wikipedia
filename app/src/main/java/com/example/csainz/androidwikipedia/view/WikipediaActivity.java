package com.example.csainz.androidwikipedia.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.example.csainz.androidwikipedia.R;
import com.example.csainz.androidwikipedia.common.GenericActivity;

public class WikipediaActivity extends GenericActivity {

    private final Context myApp = this;

    private WebView wikiWebView;

    private ProgressDialog mProgress;


    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        requestWindowFeature(Window.FEATURE_LEFT_ICON);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wikipedia);

        wikiWebView = (WebView) findViewById(R.id.webview);
//        wikiWebView.getSettings().setBuiltInZoomControls(false);
//        CookieManager.getInstance().setAcceptCookie(true);
//        wikiWebView.setVerticalScrollBarEnabled(false);
//        wikiWebView.clearCache(true);
//        wikiWebView.setFocusable(true);
//        wikiWebView.setHorizontalScrollBarEnabled(false);
//        wikiWebView.getSettings().setPluginState(WebSettings.PluginState.ON);
//        wikiWebView.getSettings().setJavaScriptEnabled(true);
//        wikiWebView.getSettings().setAppCacheEnabled(true);
//        wikiWebView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
//        wikiWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
//        wikiWebView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
////        wikiWebView.setBackgroundResource(Color.Transparent);
//        wikiWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        mProgress = ProgressDialog.show(this, "Loading", "Please wait for few second...");

        // Allow the user to navigate inside the webview. It overrides the default behaviour when a
        // user clicks a link, which would be open the link on a web browser. Links will be opened
        // inside the webview instead.
        wikiWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (Uri.parse(url).getHost().equals("en.wikipedia.org")) {
                    // This is the Wikipedia site, so don't override; let the WebView load the page
                    return false;
                }
                // Otherwise, the link is not for a page on my site, so launch another Activity
                // that handles URLs
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
                return true;
            }

            public void onPageFinished(WebView view, String url) {
                if (mProgress.isShowing()) {
                    mProgress.dismiss();
                }
                super.onPageFinished(view, url);
            }
        });
        // Load a web page in the webview
        wikiWebView.loadUrl("https://www.google.com");
//        https://en.wikipedia.org/wiki/Special:Random
    }


    public void onBackPressed() {
        Toast.makeText(this, "Please press again to exit", Toast.LENGTH_LONG).show();
        super.onBackPressed();
    }


    /**
     * Called when a key was pressed down and not handled by any of the views inside of the
     * activity. So, for example, key presses while the cursor is inside a TextView will not
     * trigger the event (unless it is a navigation to another object) because TextView handles its
     * own key presses.
     * <p/>
     * <p>If the focused view didn't want this event, this method is called.
     * <p/>
     * <p>The default implementation takes care of {@link KeyEvent#KEYCODE_BACK} by calling
     * {@link #onBackPressed()}, though the behavior varies based on the application compatibility
     * mode: for {@link Build.VERSION_CODES#ECLAIR} or later applications, it will set up the
     * dispatch to call {@link #onKeyUp} where the action will be performed; for earlier
     * applications, it will perform the action immediately in on-down, as those versions of the
     * platform behaved.
     * <p/>
     * <p>Other additional default key handling may be performed if configured with
     * {@link #setDefaultKeyMode}.
     *
     * @param keyCode
     * @param event
     * @return Return <code>true</code> to prevent this event from being propagated further, or
     *          <code>false</code> to indicate that you have not handled this event and it should
     *          continue to be propagated.
     * @see #onKeyUp
     * @see KeyEvent
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // Check if the key event was the Back button and if there's history
        if (keyCode == KeyEvent.KEYCODE_BACK && wikiWebView.canGoBack()) {
            wikiWebView.goBack();
            return true;
        }

        // Check if the key event was the Forward button and if there's a forward history
        if (keyCode == KeyEvent.KEYCODE_FORWARD && wikiWebView.canGoForward()) {
            wikiWebView.goForward();
            return true;
        }

        // If it wasn't the Back key or there's no web page history, bubble up to the default
        // system behavior (probably exit the activity)
        return super.onKeyDown(keyCode, event);
    }

}
