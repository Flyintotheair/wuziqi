package com.txxia.game.fivechess;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SaveSharedPreference {
    private static String username=null;
    private static String password=null;
    private static int userId;
    private static int grade=0;

    public void setUsername (String username) {
        this.username = username;
    }

    public void setPassword (String password) {
        this.password = password;
    }
    public void setUserId (int userId) {
        this.userId = userId;
    }

    public void setGrade (int grade) {
        this.grade = grade;
    }


    public String getUsername () { return username; }
    public String getPassword () { return password; }
    public int getGrade () { return grade; }
    public int getUserId() {return userId;}




}
