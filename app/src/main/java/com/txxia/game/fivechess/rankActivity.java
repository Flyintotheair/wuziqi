package com.txxia.game.fivechess;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

public class rankActivity extends Activity {
    private TextView myrank;
    private ListView worldrank;
    private Thread t;
    private DBUtils dbUtils;
    private ResultSet rs;
    ArrayList<String> worldgrade = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);
        myrank = findViewById(R.id.myrank);
        myrank.setText("我的分数：" + new SaveSharedPreference().getGrade());
        String sql = "select * from user order by grade desc";
        try {
            t = new Thread(new Runnable() {
                @Override
                public void run() {
                    dbUtils = new DBUtils();
                    rs = dbUtils.query(sql);
                    try {
                        if (rs.isBeforeFirst()) {
                            int i = 0;
                            while (rs.next()) {
                                worldgrade.add((i + 1) + "       " + rs.getString("username") + ":" + rs.getString("grade"));
                                i++;
                                if (i >= 15) break;
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
        while (t.isAlive()) ;
        worldrank = findViewById(R.id.listview);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(rankActivity.this, android.R.layout.simple_list_item_1, worldgrade);
        worldrank.setAdapter(arrayAdapter);
    }
}