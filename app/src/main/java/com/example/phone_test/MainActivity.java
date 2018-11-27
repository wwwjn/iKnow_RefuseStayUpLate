package com.example.phone_test;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }

    //进入“开发者信息”页面
    public void getDesignerInfo(View view)
    {
        Intent intent = new Intent(this, OurInfo.class);
        startActivity(intent);
    }

    //进入“反馈信息”页面
    public void gotoFeedback(View view)
    {
        Intent intent = new Intent(this, Feedback.class);
        startActivity(intent);
    }

    //返回上一页面
    public void goBack(View view)
    {
        finish();
    }





}


