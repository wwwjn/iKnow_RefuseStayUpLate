package com.example.phone_test;

import android.content.Intent;
import android.content.pm.FeatureGroupInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

//问题反馈页面（上一页面为MainActivity）
public class Feedback extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        EditText feedbackContent = new EditText(this);
        
    }

    //返回键按钮，跳转到上一页面
    public void goBack(View view)
    {
        finish();
    }

    //点击反馈记录，跳转到FeedbackRecorder页面
    public void getRecord(View view)
    {
        Intent intent = new Intent(this, FeedbackRecorder.class);
        startActivity(intent);
    }
}
