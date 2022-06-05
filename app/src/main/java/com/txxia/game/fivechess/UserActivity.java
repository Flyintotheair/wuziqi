package com.txxia.game.fivechess;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserActivity extends Activity {
    private TextView username;
    private TextView password;
    private TextView grade;
    private Thread t;
    private DBUtils dbUtils;
    private ResultSet rs;
    String name;
    String mima;
    String rank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String sql = "select * from user where username = 'root';";
        try {
            t = new Thread(new Runnable() {
                @Override
                public void run() {
                    dbUtils = new DBUtils();
                    rs = dbUtils.query(sql);
                    try {
                        if (rs.isBeforeFirst()) {
                            rs.next();
                            name = "名称：" + rs.getString("username");
                            mima = "密码：" + rs.getString("password");
                            rank = "分数：" + rs.getString("grade");
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
        setContentView(R.layout.activity_user);
        username = (TextView) findViewById(R.id.name);
        password = (TextView) findViewById(R.id.password);
        grade = (TextView) findViewById(R.id.grade);
        username.setText(name);
        password.setText(mima);
        grade.setText(rank);
    }
}