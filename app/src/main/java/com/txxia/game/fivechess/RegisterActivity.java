package com.txxia.game.fivechess;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.sql.ResultSet;

public class RegisterActivity extends AppCompatActivity {

    EditText mEtPassword,mEtSurePassword, Username;
    Button mBtnAckRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Username = findViewById(R.id.re_et_1);
        mEtPassword = findViewById(R.id.re_et_2);
        mEtSurePassword = findViewById(R.id.re_et_3);
        mBtnAckRegister = findViewById(R.id.btn_ackRegister);

        mBtnAckRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean isok=false;
                Thread t;
                DBUtils db;
                ResultSet rs;
                String username = Username.getText().toString();
                String pw1 = mEtPassword.getText().toString();
                String pw2 = mEtSurePassword.getText().toString();
                if (username.isEmpty() || pw1.isEmpty() || pw2.isEmpty()) {
                    com.txxia.game.fivechess.ToastUtil.showMsg(RegisterActivity.this, "没有输入用户号或者密码");
                }else if (!pw1.equals(pw2)) {
                    com.txxia.game.fivechess.ToastUtil.showMsg(RegisterActivity.this, "两次输入的密码不一致");
                }else {

                }
            }
        });
    }
}