package com.knight.arch.ui.fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.knight.arch.events.LoginUriMsg;

import de.greenrobot.event.EventBus;


/**
 * @author andyiac
 * @date 15/10/22
 * @web http://blog.andyiac.com
 * @github https://github.com/andyiac
 */
@SuppressLint("ValidFragment")
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
                Uri uri = Uri.parse(url);
                if (uri.getScheme().equals("http")) {

                    LoginUriMsg msg = new LoginUriMsg();
                    msg.setUrl(uri);

                    EventBus.getDefault().post(msg);
                    LoginDialogFragment.this.dismiss();
                    return true;
                }
                return super.shouldOverrideUrlLoading(view, url);
            }

        });

        webView.requestFocus(View.FOCUS_DOWN);

        builder.setView(webView);

        return builder.create();
    }

}
