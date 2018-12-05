package com.example.phone_test;

import android.content.Context;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

//消息设置页面，上一页面为设置页面
public class set_Notice extends AppCompatActivity {//消息设置页面

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set__notice);

        CheckBox reminder;
        CheckBox voice;
        CheckBox vibrate;
        CheckBox recommend;
        final TextView TextReminder = (TextView)findViewById(R.id.textViewReminderInfo);
        final TextView TextVoice = (TextView)findViewById(R.id.textViewVoiceInfo);
        final TextView TextVibrate = (TextView)findViewById(R.id.textViewVibrateInfo);
        final TextView TextRecommend = (TextView)findViewById(R.id.textViewRecommendInfo);

        reminder = (CheckBox)findViewById(R.id.checkBoxReminder);
        voice = (CheckBox)findViewById(R.id.checkBoxVoice);
        vibrate = (CheckBox)findViewById(R.id.checkBoxVibrate);
        recommend = (CheckBox)findViewById(R.id.checkBoxRecommend);

        //打开状态栏提示
        reminder.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                // TODO Auto-generated method stub
                if(isChecked)
                {
                    TextReminder.setText("当有新消息时在状态栏提示");
                }
                else
                    {
                        TextReminder.setText("不在状态栏提示新消息");
                    }
            }
        });

        //打开消息声音提示
        voice.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                // TODO Auto-generated method stub
                if(isChecked)
                {
                    TextVoice.setText("当有新消息时允许声音提示");
                }
                else
                {
                    TextVoice.setText("不允许声音提示新消息");
                }
            }
        });

        //打开振动提示
        vibrate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                // TODO Auto-generated method stub
                if(isChecked)
                {
                    TextVibrate.setText("当有新消息时允许振动提示");
                }
                else
                {
                    TextVibrate.setText("不允许振动提示新消息");
                }
            }
        });

        //打开智能推荐
        recommend.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                // TODO Auto-generated method stub
                if(isChecked)
                {
                    TextRecommend.setText("允许向您推荐活动");
                }
                else
                {
                    TextRecommend.setText("不允许向您推荐活动");
                }
            }
        });



    }



    public void goBack(View view)
    {
        finish();
    }
}
