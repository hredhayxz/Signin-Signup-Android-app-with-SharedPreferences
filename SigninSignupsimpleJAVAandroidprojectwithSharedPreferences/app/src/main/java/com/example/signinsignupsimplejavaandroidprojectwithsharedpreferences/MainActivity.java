package com.example.signinsignupsimplejavaandroidprojectwithsharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor myEdit;
    String DBUserFullName,DBUserUsername,DBUserAddress,DBUserMobile,DBEmail,DBPassword;

    TextView name,username,address,mobile,email,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Content");

        sharedPreferences=getSharedPreferences("MySharedPref",MODE_PRIVATE);
        myEdit = sharedPreferences.edit();

        DBUserFullName=sharedPreferences.getString("FullName","");
        DBUserUsername=sharedPreferences.getString("Username","");
        DBUserAddress=sharedPreferences.getString("Address","");
        DBUserMobile=sharedPreferences.getString("Mobile","");
        DBEmail=sharedPreferences.getString("Email","");
        DBPassword=sharedPreferences.getString("Password","");


        name=findViewById(R.id.name);
        username=findViewById(R.id.username);
        address=findViewById(R.id.address);
        mobile=findViewById(R.id.mobile);
        email=findViewById(R.id.email);
        password=findViewById(R.id.password);

        name.setText(DBUserFullName);
        username.setText(DBUserUsername);
        address.setText(DBUserAddress);
        mobile.setText(DBUserMobile);
        email.setText(DBEmail);
        password.setText(DBPassword);


    }
}