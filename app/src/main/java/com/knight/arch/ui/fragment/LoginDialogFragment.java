package com.knight.arch.ui.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.knight.arch.utils.L;


/**
 * @author andyiac
 * @date 15/10/22
 * @web http://blog.andyiac.com
 * @github https://github.com/andyiac
 */
public class LoginDialogFragment extends DialogFragment {

    private String url = "";

    public LoginDialogFragment(String url) {
        this.url = url;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final WebView webView = new WebView(this.getActivity()) {


            boolean layoutChangedOnce = false;

            @Override
            protected void onLayout(boolean changed, int l, int t, int r, int b) {
                if (!layoutChangedOnce) {
                    super.onLayout(changed, l, t, r, b);
                    layoutChangedOnce = true;
                }
            }

            @Override
            protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
                super.onFocusChanged(true, direction, previouslyFocusedRect);
            }

            @Override
            public boolean onCheckIsTextEditor() {
                return true;
            }


        };

        webView.loadUrl(url);
        webView.setFocusable(true);
        webView.setFocusableInTouchMode(true);
        webView.requestFocus(View.FOCUS_DOWN);

        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                L.i("url ==>>" + url);
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view,url);
            }

        });

        webView.requestFocus(View.FOCUS_DOWN);

        builder.setView(webView);

        return builder.create();
    }


}
