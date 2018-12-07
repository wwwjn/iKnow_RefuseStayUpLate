package com.example.test;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;


public class SearchPage extends AppCompatActivity {

    private static String[] testHistory = {"科研","创新","研究生","智能汽车大赛",
            "佐贺偶像是传奇","口腔喷剂","悲惨世界","简明物理化学","魂","1024节",
            "环境学院","神奇口袋","KDA","1001","すバらしい","大学物理","充电台灯",
            "毕业剧","philips","Python"};
    private LinearLayout historyView, resultView, switchView;
    private View.OnClickListener buttonOnClick;
    private SearchView browser;
    private Button cancel;
    private ListView mListView;
    private String[] mStrs = {"aaa", "bbb", "ccc", "airsaid"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_page);
        browser = findViewById(R.id.browser);
        mListView = findViewById(R.id.listView);
        mListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mStrs));
        mListView.setTextFilterEnabled(true);
        cancel = findViewById(R.id.CancelButton);
        cancel.setOnClickListener(returnButtonListener);
        historyView = findViewById(R.id.historyView);
        resultView = findViewById(R.id.resultView);
        switchView = findViewById(R.id.switchColumn);
        initHistoryView();
        browser.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                switchView.removeAllViews();
                View v = getCurrentFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
                resultView.removeAllViews();
                historyView.removeAllViews();
                initSwitchView();
                initResultView();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (!TextUtils.isEmpty(newText)){
                    mListView.clearTextFilter();
                }else{
                    mListView.clearTextFilter();
                }
                return false;
            }
        });

    }
    private void initHistoryView(){
        LinearLayout historyCase = historyView;
        int size = testHistory.length;
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(10, 3, 10, 3);
        LinearLayout.LayoutParams historyBarLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        historyBarLayoutParams.setMargins(0, 10, 0, 10);
        ArrayList<Button> childBtns = new ArrayList<>();
        int totalBtns = 0;

        LinearLayout historyHintBar = new LinearLayout(this);
        historyHintBar.setOrientation(LinearLayout.HORIZONTAL);
        historyHintBar.setLayoutParams(historyBarLayoutParams);
        historyHintBar.setPadding(25,5,0,5);
        TextView searchHistory = new TextView(this);
        searchHistory.setTextColor(Color.BLACK);
        searchHistory.setTextSize(16);
        searchHistory.setText("搜索历史");
        historyHintBar.addView(searchHistory);
        historyView.addView(historyHintBar);

        for(int i = 0; i < size; i++){
            String item = testHistory[i];
            LinearLayout.LayoutParams itemParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            int length= item.length();

            if(length < 4){
                itemParams.weight = 1;
                totalBtns++;
            }else if(length < 8){
                itemParams.weight = 2;
                totalBtns+=2;
            }else{
                itemParams.weight = 3;
                totalBtns+=3;
            }

            itemParams.width = 0;
            itemParams.setMargins(5, 5, 5, 5);
            Button childBtn = (Button) LayoutInflater.from(this).inflate(R.layout.history_button, null);
            childBtn.setText(item);
            childBtn.setOnClickListener(buttonOnClick);
            childBtn.setTag(item);
            childBtn.setLayoutParams(itemParams);
            childBtns.add(childBtn);

            if(totalBtns >= 5){
                LinearLayout  horizLL = new LinearLayout(this);
                horizLL.setOrientation(LinearLayout.HORIZONTAL);
                horizLL.setLayoutParams(layoutParams);

                for(Button addBtn:childBtns){
                    horizLL.addView(addBtn);
                }
                historyCase.addView(horizLL);
                childBtns.clear();
                totalBtns = 0;
            }
        }
        //调整最后一行的样式
        if(!childBtns.isEmpty()){
            LinearLayout horizLL = new LinearLayout(this);
            horizLL.setOrientation(LinearLayout.HORIZONTAL);
            horizLL.setLayoutParams(layoutParams);

            for(Button addBtn:childBtns){
                horizLL.addView(addBtn);
            }
            historyCase.addView(horizLL);
            childBtns.clear();
            totalBtns = 0;
        }
    }

    private void initResultView(){
        LinearLayout resultCase = resultView;
        LinearLayout.LayoutParams resultColumnLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        resultColumnLayoutParams.setMargins(0, 10, 0, 10);
        LinearLayout.LayoutParams resultSelectButtonParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        resultSelectButtonParams.weight = 1;
        LinearLayout resultSelectColumn = new LinearLayout(this);
        resultSelectColumn.setOrientation(LinearLayout.HORIZONTAL);
        resultSelectColumn.setLayoutParams(resultColumnLayoutParams);
        Button leftButton = (Button) LayoutInflater.from(this).inflate(R.layout.result_select_button, null);
        Button middleButton = (Button) LayoutInflater.from(this).inflate(R.layout.result_select_button, null);
        Button rightButton = (Button) LayoutInflater.from(this).inflate(R.layout.result_select_button, null);
        leftButton.setText("默认时长");
        leftButton.setLayoutParams(resultSelectButtonParams);
        leftButton.setOnClickListener(buttonOnClick);
        middleButton.setText("全部时长");
        middleButton.setLayoutParams(resultSelectButtonParams);
        middleButton.setOnClickListener(buttonOnClick);
        rightButton.setText("全部标签");
        rightButton.setLayoutParams(resultSelectButtonParams);
        rightButton.setOnClickListener(buttonOnClick);
        resultSelectColumn.addView(leftButton);
        resultSelectColumn.addView(middleButton);
        resultSelectColumn.addView(rightButton);
        resultCase.addView(resultSelectColumn);

    }

    private void initSwitchView(){
        LinearLayout switchCase = switchView;
        LinearLayout.LayoutParams switchButtonParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        switchButtonParams.weight = 1;
        Button privateButton = (Button) LayoutInflater.from(this).inflate(R.layout.private_button, null);
        Button publicButton = (Button) LayoutInflater.from(this).inflate(R.layout.public_button, null);
        Button settingButton = (Button) LayoutInflater.from(this).inflate(R.layout.setting_button, null);
        privateButton.setOnClickListener(buttonOnClick);
        publicButton.setOnClickListener(buttonOnClick);
        settingButton.setOnClickListener(buttonOnClick);
        privateButton.setLayoutParams(switchButtonParams);
        publicButton.setLayoutParams(switchButtonParams);
        settingButton.setLayoutParams(switchButtonParams);
        switchCase.addView(privateButton);
        switchCase.addView(publicButton);
        switchCase.addView(settingButton);
    }

    Button.OnClickListener returnButtonListener = new Button.OnClickListener() {
        public void onClick(View view) {
            Intent intent = new Intent(SearchPage.this, PublicPage.class);
            startActivity(intent);
            SearchPage.this.finish();
        }
    };
}
