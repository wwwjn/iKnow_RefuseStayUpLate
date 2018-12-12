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

import org.json.JSONObject;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

class User{
    public String Username, Password, Nickname, Department;
    public User(String Username, String Password, String Nickname, String Department){
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
    private String[] DepartmentChoice = new String[]{"请选择您的院系:","生医","生命","工物","建筑"};
    private String[] UsernameNow = new String[]{"李碧璐","程超群","杨小燕","王佳妮","沈运浩","马梓源","赖赣平"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Username = findViewById(R.id.Username);
        Nickname = findViewById(R.id.Nickname);
        Password = findViewById(R.id.Password);
        Password2 = findViewById(R.id.Password2);
        DepartmentText = findViewById(R.id.DepartmentError);
        UsernameText = findViewById(R.id.UsernameError);
        NicknameText = findViewById(R.id.NicknameError);
        PasswordText = findViewById(R.id.PasswordError);
        Password2Text = findViewById(R.id.Password2Error);

        DepartmentSpinner = findViewById(R.id.Department);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, DepartmentChoice);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        DepartmentSpinner.setAdapter(adapter);

        RegisterButton = findViewById(R.id.Register);
        RegisterButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                final String dMessage = DepartmentSpinner.getSelectedItem().toString();
                final String uMessage = Username.getText().toString();
                final String nMessage = Nickname.getText().toString();
                final String pMessage = Password.getText().toString();
                String p2Message = Password2.getText().toString();
                UsernameText.setVisibility(View.INVISIBLE);
                NicknameText.setVisibility(View.INVISIBLE);
                PasswordText.setVisibility(View.INVISIBLE);
                Password2Text.setVisibility(View.INVISIBLE);

                if(dMessage.equals("请选择您的院系:"))
                    DepartmentText.setVisibility(View.VISIBLE);
                else if(uMessage.equals(""))
                    UsernameText.setVisibility(View.VISIBLE);
                else if(nMessage.equals(""))
                    NicknameText.setVisibility(View.VISIBLE);
                else if(pMessage.equals(""))
                    PasswordText.setVisibility(View.VISIBLE);
                else if (p2Message.equals(""))
                    Password2Text.setVisibility(View.VISIBLE);
                else {
                    boolean flag = true;
                    for (int i = 0; i < UsernameNow.length; i++) {
                        if (uMessage.equals(UsernameNow[i])) {
                            UsernameText.setText("对不起，此用户名已存在!");
                            UsernameText.setVisibility(View.VISIBLE);
                            flag = false;
                            break;
                        }
                    }
                    if (!pMessage.equals(p2Message)) {
                        flag = false;
                        Password2Text.setText("两次密码不匹配，请重新输入密码!");
                        Password2Text.setVisibility(View.VISIBLE);
                    }
                    if(flag == true) {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    User Me = new User(uMessage, pMessage, nMessage, dMessage);
                                    JSONObject Json = new JSONObject();
                                    Json.put("Username", Me.Username);
                                    Json.put("Password", Me.Password);
                                    Json.put("Department", Me.Department);
                                    Json.put("Nickname", Me.Nickname);
                                    String content = String.valueOf(Json);

                                    URL url = new URL("http://iknow.gycis.me/updateData/addNewUser");
                                    HttpURLConnection connection =  (HttpURLConnection)url.openConnection();
                                    connection.setRequestMethod("POST");
                                    connection.setConnectTimeout(8000);
                                    connection.setReadTimeout(8000);
                                    connection.setDoInput(true);
                                    connection.setDoOutput(true);

                                    OutputStream os = connection.getOutputStream();
                                    os.write(content.getBytes());
                                    os.close();

                                } catch (Exception e) {
                                    e.printStackTrace();
                                    Log.i("Connection", "fail");
                                }
                            }
                        }).start();
                    }
                }
            }
        });
    }
}
