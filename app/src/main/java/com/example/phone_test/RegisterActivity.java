package com.example.phone_test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.content.Intent;

import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.ByteArrayOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

class User{
    //存储用户的用户名、密码、昵称、院系
    public String Username, Password, Nickname, Department;
    public void SetInformation(String Username, String Password, String Nickname, String Department){
        this.Username = Username;
        this.Password = Password;
        this.Nickname = Nickname;
        this.Department = Department;
    }
}

public class RegisterActivity extends AppCompatActivity {
    private Button RegisterButton;
    private Spinner DepartmentSpinner;
    private EditText Username, Nickname, Password, Password2;
    private TextView DepartmentText, UsernameText, NicknameText, PasswordText, Password2Text;
    private User Me = new User();
    private boolean RegisterSucceedFlag = true;
    private String[] UsernameNow = new String[]{"李碧璐","程超群","杨小燕","王佳妮","沈运浩","马梓源","赖赣平"};

    private List<String> DepartmentChoice(){
        List<String> data = new ArrayList<>();
        data.add("请选择您的院系:");
        data.add("建筑学院"); data.add("经济管理学院"); data.add("土木水利学院"); data.add("公共管理学院"); data.add("环境学院");
        data.add("人文学院"); data.add("机械工程学院"); data.add("社会科学学院"); data.add("信息科学技术学院"); data.add("法学院");
        data.add("新闻与传播学院"); data.add("材料学院"); data.add("美术学院"); data.add("理学院"); data.add("生命科学学院"); data.add("医学院");
        return data;
    }

    public void Init(){
        //根据id确定每个控件
        DepartmentSpinner = findViewById(R.id.Department);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, DepartmentChoice());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        DepartmentSpinner.setAdapter(adapter);

        Username = findViewById(R.id.Username);
        Nickname = findViewById(R.id.Nickname);
        Password = findViewById(R.id.Password);
        Password2 = findViewById(R.id.Password2);
        DepartmentText = findViewById(R.id.DepartmentError);
        UsernameText = findViewById(R.id.UsernameError);
        NicknameText = findViewById(R.id.NicknameError);
        PasswordText = findViewById(R.id.PasswordError);
        Password2Text = findViewById(R.id.Password2Error);

        RegisterButton = findViewById(R.id.Register);
        RegisterButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                final String department = DepartmentSpinner.getSelectedItem().toString();
                final String username = Username.getText().toString();
                final String nickname = Nickname.getText().toString();
                final String password = Password.getText().toString();
                final String password2 = Password2.getText().toString();
                UsernameText.setVisibility(View.INVISIBLE);
                NicknameText.setVisibility(View.INVISIBLE);
                PasswordText.setVisibility(View.INVISIBLE);
                Password2Text.setVisibility(View.INVISIBLE);

                if(department.equals("请选择您的院系:"))
                    DepartmentText.setVisibility(View.VISIBLE);
                else if(username.equals(""))
                    UsernameText.setVisibility(View.VISIBLE);
                else if(nickname.equals(""))
                    NicknameText.setVisibility(View.VISIBLE);
                else if(password.equals(""))
                    PasswordText.setVisibility(View.VISIBLE);
                else if(password2.equals(""))
                    Password2Text.setVisibility(View.VISIBLE);
                else if(!password.equals(password2)) {
                    Password2Text.setText("两次密码不匹配，请重新输入密码!");
                    Password2Text.setVisibility(View.VISIBLE);
                }
                else{
                    Me.SetInformation(username, password, nickname, department);
                    Register();
                    if(RegisterSucceedFlag == false){
                        UsernameText.setText("对不起，此用户名已存在!");
                        UsernameText.setVisibility(View.VISIBLE);
                    }
                    else{
                        //Intent intent = new Intent();
                        //这里把MyActivity.class改成想要跳转的页面即可实现跳转
                        //intent.setClass(RegisterActivity.this, MyActivity.class);
                        //startActivity(intent);
                    }
                }
            }
        });
    }

    public void Register(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONObject Json = new JSONObject();  //把数据存成Json格式
                    Json.put("Username", Me.Username);
                    Json.put("Password", Me.Password);
                    Json.put("Department", Me.Department);
                    Json.put("Nickname", Me.Nickname);
                    String content = String.valueOf(Json);  //Json格式转成字符串来传输


                    URL url = new URL("https://iknow.gycis.me/updateData/addNewUser");  //不同的请求发送到不同的URL地址，见群里的“后端网页名字设计.docx”
                    HttpURLConnection connection =  (HttpURLConnection)url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setConnectTimeout(5000);
                    connection.setReadTimeout(5000);
                    connection.setDoInput(true);
                    connection.setDoOutput(true);

                    Log.i("Connection", content);
                    OutputStream os = connection.getOutputStream();  //打开输出流传输数据
                    os.write(content.getBytes());
                    os.flush();
                    os.close();

                    Log.i("Connection", String.valueOf(connection.getResponseCode()));  //如果ResponseCode=200说明和服务器连接正确
                    if (connection.getResponseCode() == 200) {
                        //以字符串格式读取服务器的返回内容，Register功能只需返回普通字符串，如果请求的是活动信息则将会返回Json格式的字符串，
                        //可以用形如JSONObject Json = new JSONObject(String)的语句把字符串转成Json格式
                        String result = StreamToString(connection.getInputStream());
                        Log.i("Connection", result);
                        if(result.equals("username existed"))
                            RegisterSucceedFlag = false;
                        else if(result.equals("register succeed"))
                            RegisterSucceedFlag = true;
                    }
                    else{
                        Log.i("Connection", "Fail");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public String StreamToString(InputStream is) {
        //把输入流转换成字符串
        try {
            ByteArrayOutputStream Baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = is.read(buffer)) != -1)
                Baos.write(buffer, 0, len);
            String result = Baos.toString();
            is.close();
            Baos.close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Init();
    }
}
