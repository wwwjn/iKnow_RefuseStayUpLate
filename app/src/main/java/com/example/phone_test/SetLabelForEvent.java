package com.example.phone_test;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

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

//事件的标签设置页面，上一页面为“发布消息”页面
public class SetLabelForEvent extends AppCompatActivity {

    //定义一个布尔数组存储主题标签的选中状态
    boolean[] mainLabel = new boolean[11];
    //定义一个布尔数组存储副标签的选中状态
    boolean[] subLabel = new boolean[11];
    //定义一个布尔数组存储活动形式标签的选中状态
    boolean[] activityLabel = new boolean[5];
    //计数所选主题标签数目，副标签数目，活动形式标签数目并初始化为0
    int mainCount = 0, subCount = 0, activityCount = 0;

    //定义数组存储各个按钮的id
    Integer[] ButtonId = new Integer[]{
            R.id.Button_Main_Sci,R.id.Button_Main_Computer,R.id.Button_Main_PE,R.id.Button_Main_Practice,R.id.Button_Main_English,R.id.Button_Main_Economic,
            R.id.Button_Main_Chuangye,R.id.Button_Main_Literature,R.id.Button_Main_Movie,R.id.Button_Main_Volunteer,R.id.Button_Main_Art,
            R.id.Button_Sub_Sci,R.id.Button_Sub_Computer,R.id.Button_Sub_PE,R.id.Button_Sub_Practice,R.id.Button_Sub_English,R.id.Button_Sub_Economic,
            R.id.Button_Sub_Chuangye,R.id.Button_Sub_Literature,R.id.Button_Sub_Movie,R.id.Button_Sub_Volunteer,R.id.Button_Sub_Art,
            R.id.Button_Activity_Lecture,R.id.Button_Activity_Xsj,R.id.Button_Activity_Exhibition,R.id.Button_Activity_Competition,R.id.Button_Activity_Performance
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
        setContentView(R.layout.activity_set_label_for_event);


        //每次打开标签设置页面加载标签
        refreshLabel();
    }

/*    *//*---------------------------------------------------------*//*
    //初始化标签选中状态，所有标签均为false
    //在新建一个事件的函数里应当有对标签的初始化，此处用于测试
    *//*---------------------------------------------------------*//*
    private void initLabel(View view)
    {
        for(int i=0;i<11;i++)
        {
            TypeLabel[i] = false;
        }
        for(int i=0;i<5;i++)
        {
            ActivityLabel[i] = false;
        }
    }
    *//*---------------------------------------------------------*//*
    //初始化标签选中状态，所有标签均为false
    //在新建一个事件的函数里应当有对标签的初始化，此处用于测试
    *//*---------------------------------------------------------*/

    //设置好标签之后再次打开标签页面时，直接加载已经存储好了的标签
    public void refreshLabel()
    {
        ImageButton LabelBtn;
        //刷新显示主标题：i=0~10
        for(int i=0;i<11;i++)
        {
            LabelBtn = (ImageButton)findViewById(ButtonId[i]);

            //UserLabel[i]==true 代表该标签被选中
            if(mainLabel[i])
            {
                LabelBtn.setImageDrawable(getDrawable(choosedImage[i]));
            }

            //UserLabel[i]==false 代表该标签未被选中
            else
            {
                LabelBtn.setImageDrawable(getDrawable(unchoosedImage[i]));
            }
        }
        //刷新显示副标题：i=11~21
        for(int i=11;i<21;i++)
        {
            LabelBtn = (ImageButton)findViewById(ButtonId[i]);

            //UserLabel[i]==true 代表该标签被选中
            if(subLabel[i-11])
            {
                LabelBtn.setImageDrawable(getDrawable(choosedImage[i-11]));
            }

            //UserLabel[i]==false 代表该标签未被选中
            else
            {
                LabelBtn.setImageDrawable(getDrawable(unchoosedImage[i-11]));
            }
        }
        //刷新显示活动形式标签：i=22~26
        for(int i=22;i<27;i++)
        {
            LabelBtn = (ImageButton)findViewById(ButtonId[i]);

            //activityLabel[i]==true 代表该标签被选中
            if(activityLabel[i-22])
            {
                LabelBtn.setImageDrawable(getDrawable(choosedImage[i-11]));
            }

            //UserLabel[i]==false 代表该标签未被选中
            else
            {
                LabelBtn.setImageDrawable(getDrawable(unchoosedImage[i-11]));
            }
        }
    }

    //监听Button
    public void setEventLabel(View view)
    {
        for(int i=0;i<27;i++)
        {
            if(view.getId() == ButtonId[i]) //得到是第几个标签按钮被点击
            {
                int clickNum = i;

                //主题标签
                if(clickNum < 11 && (!subLabel[clickNum])) //主题标签被点击
                {
                    if(mainCount < 1) //此刻还未选择主题标签,有点击应该是添加主题标签
                    {
                        mainCount++;
                        mainLabel[clickNum] = !mainLabel[clickNum];
                        ImageButton Btn = (ImageButton)findViewById(ButtonId[clickNum]);
                        Btn.setImageDrawable(getDrawable(choosedImage[clickNum]));
                    }
                    else  //此刻已有主题标签被选择，那么应该无法添加更多主题标签,只能删除已选主题标签
                    {
                        if(mainLabel[clickNum])//所点击的是已经被选中的标签
                        {
                            mainLabel[clickNum] = !mainLabel[clickNum];
                            mainCount--;
                            ImageButton Btn = (ImageButton)findViewById(ButtonId[clickNum]);
                            Btn.setImageDrawable(getDrawable(unchoosedImage[clickNum]));
                        }
                    }
                }

                //副标签
                if(clickNum > 10 && clickNum < 22 && (!mainLabel[clickNum - 11])) //副标签被点击且该标签未在主标签里选择
                {
                    ImageButton Btn = (ImageButton)findViewById(ButtonId[clickNum]);
                    clickNum = clickNum - 11;
                    if(subCount < 1) //此刻还未选择副标签,有点击应该是添加标签
                    {
                        subCount++;
                        subLabel[clickNum] = !subLabel[clickNum];
                        Btn.setImageDrawable(getDrawable(choosedImage[clickNum]));
                    }
                    else  //此刻已有副标签被选择，那么应该无法添加更多副标签,只能删除已选副标签
                    {
                        if(subLabel[clickNum])//所点击的是已经被选中的标签
                        {
                            subLabel[clickNum] = !subLabel[clickNum];
                            subCount--;
                            Btn.setImageDrawable(getDrawable(unchoosedImage[clickNum]));
                        }
                    }
                }

                //活动形式标签
                if(clickNum > 21 && clickNum < 27 ) //活动形式标签被点击
                {
                    ImageButton Btn = (ImageButton)findViewById(ButtonId[clickNum]);
                    clickNum = clickNum - 22;
                    if(activityCount < 1) //此刻还未选择活动形式标签,有点击应该是添加标签
                    {
                        activityCount++;
                        activityLabel[clickNum] = !activityLabel[clickNum];
                        Btn.setImageDrawable(getDrawable(choosedImage[clickNum + 11]));
                    }
                    else  //此刻已有活动形式标签被选择，那么应该无法添加更多活动形式标签,只能删除已选活动形式标签
                    {
                        if(activityLabel[clickNum])//所点击的是已经被选中的标签
                        {
                            activityLabel[clickNum] = !activityLabel[clickNum];
                            activityCount--;
                            Btn.setImageDrawable(getDrawable(unchoosedImage[clickNum + 11]));
                        }
                    }
                }


            }
        }
    }

    public void saveLabel(View view)
    {
        TextView Message;
        Message = (TextView) findViewById(R.id.TextSetEventLabelSuccessful);
        Message.setVisibility(View.VISIBLE);
       /* Timer timer;//定时器
        timer = new Timer();
        long time = 3000;
        timer.schedule(new timerTask(),3000);*/
    }


    //监测哪个标签被点击


    /*public void SciClicked(View view)
    {
        ImageButton SciBtn = (ImageButton)findViewById(R.id.Button_Sci);
        if(TypeLabel[0])
        {
            SciBtn.setImageDrawable(getDrawable(button_sci));
        }
        else
        {
            SciBtn.setImageDrawable(getDrawable(button_sci_del));
        }
        TypeLabel[0]=!TypeLabel[0];
    }*/



    //科创标签被点击


    public void goBack(View view)
    {
        finish();
    }
}


