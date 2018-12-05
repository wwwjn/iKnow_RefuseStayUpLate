package com.example.phone_test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import static com.example.phone_test.R.drawable.button_art;
import static com.example.phone_test.R.drawable.button_art_del;
import static com.example.phone_test.R.drawable.button_chuangye;
import static com.example.phone_test.R.drawable.button_chuangye_del;
import static com.example.phone_test.R.drawable.button_competition;
import static com.example.phone_test.R.drawable.button_competition_del;
import static com.example.phone_test.R.drawable.button_computer;
import static com.example.phone_test.R.drawable.button_computer_del;
import static com.example.phone_test.R.drawable.button_economic;
import static com.example.phone_test.R.drawable.button_economic_del;
import static com.example.phone_test.R.drawable.button_english;
import static com.example.phone_test.R.drawable.button_english_del;
import static com.example.phone_test.R.drawable.button_exhibition;
import static com.example.phone_test.R.drawable.button_exhibition_del;
import static com.example.phone_test.R.drawable.button_lecture;
import static com.example.phone_test.R.drawable.button_lecture_del;
import static com.example.phone_test.R.drawable.button_literature;
import static com.example.phone_test.R.drawable.button_literature_del;
import static com.example.phone_test.R.drawable.button_movie;
import static com.example.phone_test.R.drawable.button_movie_del;
import static com.example.phone_test.R.drawable.button_pe;
import static com.example.phone_test.R.drawable.button_pe_del;
import static com.example.phone_test.R.drawable.button_performance;
import static com.example.phone_test.R.drawable.button_performance_del;
import static com.example.phone_test.R.drawable.button_practice;
import static com.example.phone_test.R.drawable.button_practice_del;
import static com.example.phone_test.R.drawable.button_sci;
import static com.example.phone_test.R.drawable.button_sci_del;
import static com.example.phone_test.R.drawable.button_volunteer;
import static com.example.phone_test.R.drawable.button_volunteer_del;
import static com.example.phone_test.R.drawable.button_xsj;
import static com.example.phone_test.R.drawable.button_xsj_del;

//用户的标签设置页面，上一页面为设置页面
public class SetLabelForUser extends AppCompatActivity {

    //定义一个布尔数组存储标签的选中状态:0~10储存活动主题标签，11~15储存活动形式标签
    boolean[] UserLabel = new boolean[16];


    //定义一个LabelCount计数选择了的标签数
    int UserLabelCount = 0;

    //定义一个数组存储各个标签的id
    Integer[] ButtonId = new Integer[]{
            R.id.Button_Sci,R.id.Button_Computer,R.id.Button_PE,R.id.Button_Practice,R.id.Button_English,R.id.Button_Economic,
            R.id.Button_Chuangye,R.id.Button_Literature,R.id.Button_Movie,R.id.Button_Volunteer,R.id.Button_Art,
            R.id.Button_Lecture,R.id.Button_Xsj,R.id.Button_Exhibition,R.id.Button_Competition,R.id.Button_Performance
    };

    //定义数组存储选中状态下的标签图片id
    Integer[] choosedImage = new Integer[]{
            button_sci_del,button_computer_del,button_pe_del,button_practice_del,button_english_del,button_economic_del,
            button_chuangye_del,button_literature_del,button_movie_del,button_volunteer_del,button_art_del,
            button_lecture_del,button_xsj_del,button_exhibition_del,button_competition_del,button_performance_del
    };

    //定义数组存储选中状态下的标签图片id
    Integer[] unchoosedImage = new Integer[]{
            button_sci,button_computer,button_pe,button_practice,button_english,button_economic,
            button_chuangye,button_literature,button_movie,button_volunteer,button_art,
            button_lecture,button_xsj,button_exhibition,button_competition,button_performance
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_label_for_user);


        //每次打开标签设置页面加载标签
        refreshLabel();


    }

    /*---------------------------------------------------------*/
    //初始化标签选中状态，所有标签均为false
    //在新建一个用户的函数里应当有对标签的初始化，此处用于测试
    /*---------------------------------------------------------*/
    /*private void initLabel()
    {
        for(int i=0;i<16;i++)
        {
            UserLabel[i] = false;
        }
    }*/
    /*---------------------------------------------------------*/
    //初始化标签选中状态，所有标签均为false
    //在新建一个用户的函数里应当有对标签的初始化，此处用于测试
    /*---------------------------------------------------------*/

    //刷新标签页面
    public void refreshLabel()
    {
        ImageButton LabelBtn;
        for(int i=0;i<16;i++)
        {
            LabelBtn = (ImageButton)findViewById(ButtonId[i]);

            //UserLabel[i]==true 代表该标签被选中
            if(UserLabel[i])
            {
                LabelBtn.setImageDrawable(getDrawable(choosedImage[i]));
            }

            //UserLabel[i]==false 代表该标签未被选中
            else
            {
                LabelBtn.setImageDrawable(getDrawable(unchoosedImage[i]));
            }
        }
    }

    //监听Button
    public void setUserLabel(View view)
    {
        if(UserLabelCount < 10)//所选标签数小于10，可继续选择
        {
            for(int i=0;i<16;i++)
            {
                if(view.getId() == ButtonId[i])//得到所选的标签
                {
                    UserLabel[i] = !UserLabel[i];
                    ImageButton Btn = (ImageButton)findViewById(ButtonId[i]);
                    if(UserLabel[i])
                    {
                        Btn.setImageDrawable(getDrawable(choosedImage[i]));
                        UserLabelCount++;
                    }
                    else
                    {
                        Btn.setImageDrawable(getDrawable(unchoosedImage[i]));
                        UserLabelCount--;
                    }

                }
            }
        }
        else//所选标签数大于10，则只能删除标签，不可增加标签
        {
            for(int i=0;i<16;i++)
            {
                if(view.getId() == ButtonId[i] && UserLabel[i])
                {
                    UserLabel[i] = !UserLabel[i];
                    ImageButton Btn = (ImageButton)findViewById(ButtonId[i]);
                    Btn.setImageDrawable(getDrawable(unchoosedImage[i]));
                    UserLabelCount--;
                }
            }
        }
    }




    //返回上一页面
    public void goBack(View view)
    {
        finish();
    }
    public void empty()
    {
        int i;
        for(i =0;i<10;i++)
        {
            int m=0;
            m=m+i;
        }
    }



    //点击“保存标签”按钮，上传标签数据至数据库，如果上传成功，显示“标签设置成功”字样
    public void saveLabel(View view)
    {
        TextView Message;
        Message = (TextView) findViewById(R.id.TextSetUserLabelSuccessful);
        Message.setVisibility(View.VISIBLE);
       /* Timer timer;//定时器
        timer = new Timer();
        long time = 3000;
        timer.schedule(new timerTask(),3000);*/
    }

   /* class timerTask extends TimerTask{
        @Override
        public void run()
        {
            TextView Message;
            Message = (TextView) findViewById(R.id.TextSetUserLabelSuccessful);
            Message.setVisibility(View.INVISIBLE);
        }
    }*/
}
