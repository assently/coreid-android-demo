package com.assently.mobiletest;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.assently.mobiletest.common.JwtHelper;
import com.assently.mobiletest.data.JwtPayload;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.UUID;

import static android.content.pm.PackageManager.GET_ACTIVITIES;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WebView view = (WebView)findViewById(R.id.webview);
        view.setWebViewClient(new MobileWebViewClient(this));

        WebSettings settings = view.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setAllowFileAccessFromFileURLs(true);

        JwtPayload payload = createPayload();

        JwtHelper helper = new JwtHelper();
        String token = helper.sign(payload, getResources().getString(R.string.jwtSecret));
        String content = getContent(token);

        view.loadDataWithBaseURL(getResources().getString(R.string.clientHost), content, "text/html", "utf-8", getResources().getString(R.string.clientHost));
    }

    private JwtPayload createPayload() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -1);

        JwtPayload payload = new JwtPayload();
        payload.setDnm("Test av mobilt bankid");
        payload.setJti(UUID.randomUUID().toString());
        payload.setHst(getResources().getString(R.string.clientHost));
        payload.setAud(new String[] { "se-bankid", "se", "all" });
        payload.setIss("customerweb");
        payload.setIat(calendar.getTime());
        calendar.add(Calendar.YEAR, 2);
        payload.setExp(calendar.getTime());

        return payload;
    }

    private String getContent(String token) {
        try (InputStream stream = getResources().openRawResource(R.raw.index)) {
            String content = IOUtils.toString(stream);
            content = content.replace("{accessToken}", token);
            content = content.replace("{clientHost}", getResources().getString(R.string.clientHost));
            content = content.replace("{isSwedishMobiltBankIdInstalled}", Boolean.toString(isSwedishMobiltBankIdInstalled(this.getApplicationContext())));

            return content;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isSwedishMobiltBankIdInstalled(Context context) {
        PackageManager pm = context.getPackageManager();
        try {
            pm.getPackageInfo("com.bankid.bus", GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }
}
