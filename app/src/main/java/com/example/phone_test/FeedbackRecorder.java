package com.example.phone_test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

//反馈记录页面，上一页面为Feedback页面
public class FeedbackRecorder extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_recorder);
        TextView feedbackRecord = (TextView)findViewById(R.id.feedbackRecordText);
        feedbackRecord.setText("此处应有二百字哟~从数据库里面载入哟然而我还不知道是不是这样载入呀就先这样吧啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啦啊");
        //此处内容应从数据库download
    }


    //返回键
    public void goBack(View view)
    {
        finish();
    }
}
