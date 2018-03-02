package com.assently.mobiletest;

import android.app.Activity;
import android.content.Intent;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MobileWebViewClient extends WebViewClient {
    private Activity activity;

    public MobileWebViewClient(Activity activity) {
        this.activity = activity;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
        if(request.getUrl().getScheme().equalsIgnoreCase("bankid")) {
            final Intent intent = new Intent(Intent.ACTION_VIEW, request.getUrl());
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            activity.startActivity(intent);

            return true;
        } else {
            return false;
        }
    }
}