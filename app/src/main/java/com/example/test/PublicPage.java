package com.example.test;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.List;


public class PublicPage extends AppCompatActivity  {

    Button browser;
    Button[] dateBtns = new Button[14];
    static int[] date={2018,11,11};
    private List<Activity> activities;
    Activity activity1 = new Activity(1,"8:00","10:00","智能车大赛真好玩啊哈哈哈哈哈哈哈啊哈哈","科创","六教6A214","汽车系科协");
    Activity activity2 = new Activity(2,"0:00","24:00","编程序真TM好玩啊哈水电费的说法呢发送到哈","计算机","没啥地点","拒绝熬夜组");
    Activity activity3 = new Activity(3,"16:00","20:00","生命学院学生节欢迎大家一起来看很好看的","社工","大礼堂","生命学院学生会");
    Activity activity4 = new Activity(4,"12:00","20:00","赶紧去学习吧哈哈哈哈哈哈哈哈哈哈哈哈","学习","#10 618","我");
    private LinearLayout dateColumn;
    private LinearLayout publicActivity;
    int shuttle = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_public_page);
        browser = findViewById(R.id.BrowserButton);
        browser.setOnClickListener(pageChangeListener);
        dateColumn = findViewById(R.id.dateColumn);
        initDateColumn(0);
        publicActivity = findViewById(R.id.publicActivity);
        initPublicActivity(activity1);
        shuttle = 1;
    }
    private void initDateColumn(int a){
        LinearLayout dateCase = dateColumn;
        int Size = 14;
        String item = "";
        int day = (date[2]+2*date[1]+3*(date[1]+1)/5+date[0]+date[0]/4-date[0]/100+date[0]/400)%7;

        for(int i = 0; i < Size; i++){
            switch((day+i)%7) {
                case 0: item = "一\n\n"; break;
                case 1: item = "二\n\n"; break;
                case 2: item = "三\n\n"; break;
                case 3: item = "四\n\n"; break;
                case 4: item = "五\n\n"; break;
                case 5: item = "六\n\n"; break;
                case 6: item = "日\n\n"; break;
            }
            item += String.valueOf(date[2]+i);
            WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
            int width = wm.getDefaultDisplay().getWidth();

            LinearLayout.LayoutParams itemParams = new LinearLayout.LayoutParams(width/7, LinearLayout.LayoutParams.MATCH_PARENT);
            itemParams.setMargins(0, 5, 0, 5);
            dateBtns[i] = (Button) LayoutInflater.from(this).inflate(R.layout.date_button, null);
            dateBtns[i].setText(item);
            dateBtns[i].setTag(item);
            dateBtns[i].setId(i);
            dateBtns[i].setLayoutParams(itemParams);
            dateBtns[i].setOnClickListener(dateChangeListener);
            dateBtns[a].setBackgroundColor(android.graphics.Color.rgb(237,189,101));
            dateCase.addView(dateBtns[i]);
            item = "";
        }
    }
    private void initPublicActivity(Activity activity){
        LinearLayout activityList =  publicActivity;
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT); // 每行的水平LinearLayout
        layoutParams.setMargins(0, 0, 0, 0);
        String circleButtonText = "";
        String barButtonText = "";
        for(int i = 0; i < 14; i++) {
            circleButtonText = activity.getStartTime() + "\n————\n" + activity.getEndTime();
            barButtonText = activity.getIntroduction().substring(0,14) + "...|" + activity.getClassification() + "\n\n" + activity.getHost() + "\t\t" + activity.getPlace();

            LinearLayout.LayoutParams circleButtonParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
            circleButtonParams.setMargins(0, 0, 0, 0);
            circleButtonParams.weight = 5;
            LinearLayout.LayoutParams barButtonParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            barButtonParams.setMargins(0, 0, 0, 0);
            barButtonParams.weight = 1;
            Button circleBtn = (Button) LayoutInflater.from(this).inflate(R.layout.circle_button, null);
            Button barBtn = (Button) LayoutInflater.from(this).inflate(R.layout.bar_button, null);
            circleBtn.setText(circleButtonText);
            barBtn.setText(barButtonText);
            circleBtn.setLayoutParams(circleButtonParams);
            barBtn.setLayoutParams(barButtonParams);
            LinearLayout activityCase = new LinearLayout(this);
            activityCase.setOrientation(LinearLayout.HORIZONTAL);
            activityCase.setLayoutParams(layoutParams);
            activityCase.addView(circleBtn);
            activityCase.addView(barBtn);
            activityList.addView(activityCase);
        }
    }
    Button.OnClickListener pageChangeListener = new Button.OnClickListener() {
      public void onClick(View v) {
          Intent intent = new Intent(PublicPage.this, SearchPage.class);
          startActivity(intent);
          PublicPage.this.finish();
      }
    };
    Button.OnClickListener dateChangeListener = new Button.OnClickListener() {
        public void onClick(View v){
            int n = v.getId();
            if(shuttle == 1) {
                publicActivity.removeAllViews();
                initPublicActivity(activity2);
                shuttle = 2;
            }
            else if(shuttle == 2){
                publicActivity.removeAllViews();
                initPublicActivity(activity3);
                shuttle = 3;
            }
            else if(shuttle == 3){
                publicActivity.removeAllViews();
                initPublicActivity(activity4);
                shuttle = 4;
            }
            else if(shuttle == 4){
                publicActivity.removeAllViews();
                initPublicActivity(activity1);
                shuttle = 1;
            }
            dateColumn.removeAllViews();
            initDateColumn(n);
        }
    };
}



