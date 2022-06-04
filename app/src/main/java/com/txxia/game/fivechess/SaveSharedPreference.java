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
    private static String phone=null;
    private static String signature=null;
    private static String email=null;
    private static int age;
    private final static File user = new File("data/data/com.txxia.game.fivechess/user.txt");

    public void setUsername (String username) {
        this.username = username;
    }

    public void setPassword (String password) {
        this.password = password;
    }
    public void setUserId (int userId) {
        this.userId = userId;
    }

    public void setPhone (String phone) {
        this.phone = phone;
    }

    public void setSignature(String signature) {this.signature = signature;}
    public void setEmail(String Email) {this.email = Email;}
    public void setAge(int  age) {this.age = age;}

    public String getUsername () { return username; }
    public String getPassword () { return password; }
    public String getPhone () { return phone; }
    public int getUserId() {return userId;}

    public String getSignature() {return signature;}
    public String getEmail() {return email;}
    public int getAge() {return age;}



    public void open () {
        if (!user.exists()) {
            try {
                user.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (user.length() == 0) {
            try {
                FileWriter fileWriter = new FileWriter(user);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write(username + "\n");
                bufferedWriter.write(password + "\n");
                bufferedWriter.write(userId + "\n");
                bufferedWriter.write(phone + "\n");
                bufferedWriter.write(signature + "\n");
                bufferedWriter.close();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                FileReader fileReader = new FileReader(user);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                username = bufferedReader.readLine();
                password = bufferedReader.readLine();
                userId = Integer.parseInt(bufferedReader.readLine());
                phone = bufferedReader.readLine();
                signature = bufferedReader.readLine();
                bufferedReader.close();
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static File getUser() {
        return user;
    }

    public void close () {
        if (user.exists()) {
            System.out.println(user.exists());
            user.delete();
            System.out.println(user.exists());
        }
    }
}
