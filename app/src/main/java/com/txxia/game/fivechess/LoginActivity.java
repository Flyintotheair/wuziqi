package com.txxia.game.fivechess;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginActivity extends AppCompatActivity {

    EditText mEtPhone,mEtPassword;
    Button mBtnLogin,mBtnRegister,mBtnForget;
    Thread t;
    DBUtils dbUtils;
    ResultSet rs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        mEtPhone = findViewById(R.id.etPhone);
        mEtPassword = findViewById(R.id.etPassword);
        mBtnLogin = findViewById(R.id.btn_login);
        mBtnRegister = findViewById(R.id.btn_register);

        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = null;
                intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ok = "登录成功！";
                String fail = "登录失败！";

                if (mEtPhone.getText().toString().equals("") || mEtPassword.getText().toString().equals("")) {
                    com.txxia.game.fivechess.ToastUtil.showMsg(LoginActivity.this, "没有输入手机号或者密码");
                }else if (mEtPhone.getText().toString().length() > 15 || mEtPassword.getText().toString().length() > 10) {
                    com.txxia.game.fivechess.ToastUtil.showMsg(LoginActivity.this, "手机号或者密码格式不对");
                }else {
                    String sqlfinduser = "select * from user where User_phone = '" + mEtPhone.getText().toString() + "';";
                    Intent intent = null;
                    final Boolean[] isOK = {false};
                    try {
                        t = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                dbUtils = new DBUtils();
                                rs = dbUtils.query(sqlfinduser);
                                try {
                                    if (rs.isBeforeFirst()) {
                                        rs.next();
                                        if (rs.getString("password").equals(mEtPassword.getText().toString())) isOK[0] = true;
                                        if (isOK[0]) {
                                            SaveSharedPreference saveSharedPreference = new SaveSharedPreference();
                                            saveSharedPreference.setUsername(rs.getString("username"));
                                            saveSharedPreference.setUserId(rs.getInt("id"));
                                            saveSharedPreference.setPassword(rs.getString("password"));
                                            saveSharedPreference.setGrade(rs.getInt("grade"));

                                        }
                                    }
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                        t.start();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    while (t.isAlive());
                    if (isOK[0]) {
                        //toast
                        Toast.makeText(getApplicationContext(), ok, Toast.LENGTH_LONG).show();
                        intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        //不正确
                        ToastUtil.showMsg(LoginActivity.this, fail);
//            //登录失败居中显示
//            Toast toastcenter = Toast.makeText(getApplicationContext(), fail, Toast.LENGTH_LONG);
//            toastcenter.setGravity(Gravity.CENTER, 0, 0);
//            toastcenter.show();
                    }
                }
            }
        });



    }
}