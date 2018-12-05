package com.example.phone_test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

//开发者信息页面，上一页面为“MainActivity”
public class OurInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_our_info);


    }

    public void goBack(View view)
    {
        finish();
    }



}
