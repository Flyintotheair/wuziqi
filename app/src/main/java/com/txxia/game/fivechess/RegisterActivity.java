package com.txxia.game.fivechess;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.sql.ResultSet;

public class RegisterActivity extends Activity {

    EditText mEtPassword,mEtSurePassword, Username;
    Button mBtnAckRegister;
    Boolean success=false;
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
                String username = Username.getText().toString();
                String pw1 = mEtPassword.getText().toString();
                String pw2 = mEtSurePassword.getText().toString();
                if (username.isEmpty() || pw1.isEmpty() || pw2.isEmpty()) {
                    com.txxia.game.fivechess.ToastUtil.showMsg(RegisterActivity.this, "没有输入用户号或者密码");
                }else if (!pw1.equals(pw2)) {
                    com.txxia.game.fivechess.ToastUtil.showMsg(RegisterActivity.this, "两次输入的密码不一致");
                }else {

                    Thread t = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            String sqlsen = "select * from user where username = '"+username+"';";
                            DBUtils db = new DBUtils();
                            ResultSet rs;
                            rs = db.query(sqlsen);
                            Boolean exit = false;
                            try {
                                if(rs.next()) {
                                    exit = true;

                                }
                            }catch (Exception e) {
                                e.printStackTrace();
                            }
                            if(exit == false) {
                                success = false;
                                String exec = "insert into user(username,password) values('"+username+"','"+pw1+"');";
                                db.update(exec);
                            }
                            else {
                                success = true;
                            }
                        }
                    });
                    t.start();
                    while(t.isAlive());
                    if(success) {
                        com.txxia.game.fivechess.ToastUtil.showMsg(RegisterActivity.this,"用户名已经存在");
                    }
                    else {
                        com.txxia.game.fivechess.ToastUtil.showMsg(RegisterActivity.this,"注册成功");
                        Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                        startActivity(intent);
                    }




                }
            }
        });
    }
}