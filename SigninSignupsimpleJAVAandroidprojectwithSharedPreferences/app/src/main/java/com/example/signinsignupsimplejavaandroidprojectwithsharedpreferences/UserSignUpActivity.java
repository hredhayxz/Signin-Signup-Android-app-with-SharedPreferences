package com.example.signinsignupsimplejavaandroidprojectwithsharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class UserSignUpActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor myEdit;
    EditText UserFullName,UserUsername,UserAddress,UserMobile,UserEmail,UserPassword;
    Button signUpButton;
    TextView signInText;
    ProgressBar SignUpProgressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_sign_up);
        setTitle("Sign Up");

        sharedPreferences=getSharedPreferences("MySharedPref",MODE_PRIVATE);
        myEdit = sharedPreferences.edit();
        UserFullName=findViewById(R.id.UserFullName);
        UserUsername=findViewById(R.id.UserUsername);
        UserAddress=findViewById(R.id.UserAddress);
        UserMobile=findViewById(R.id.UserMobile);
        UserEmail=findViewById(R.id.UserEmail);
        UserPassword=findViewById(R.id.UserPassword);
        signUpButton=findViewById(R.id.signUpButton);
        signInText=findViewById(R.id.signInText);
        SignUpProgressbar=findViewById(R.id.SignUpProgressbar);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserRegistration();
            }

            private void UserRegistration() {
                String FullName = UserFullName.getText().toString().trim();
                String Username = UserUsername.getText().toString().trim();
                String Address = UserAddress.getText().toString().trim();
                String Mobile = UserMobile.getText().toString().trim();
                String Email = UserEmail.getText().toString().trim();
                String Password = UserPassword.getText().toString().trim();
                if(FullName.isEmpty())
                {
                    UserFullName.setError("Please fill up your name");
                    UserFullName.requestFocus();
                    return;
                }
                if(FullName.length()<3)
                {
                    UserFullName.setError("Your name will be at last 3 letters");
                    UserFullName.requestFocus();
                    return;
                }
                if(Username.isEmpty())
                {
                    UserUsername.setError("Please a unique username");
                    UserUsername.requestFocus();
                    return;
                }
                if(Username.length()<3)
                {
                    UserUsername.setError("Username will be at last 3 letters");
                    UserUsername.requestFocus();
                    return;
                }
                if(Address.isEmpty())
                {
                    UserAddress.setError("Please input your address");
                    UserAddress.requestFocus();
                    return;
                }
                if(Address.length()<5)
                {
                    UserAddress.setError("Please full address");
                    UserAddress.requestFocus();
                    return;
                }
                if(Mobile.isEmpty())
                {
                    UserMobile.setError("Please input your mobile number");
                    UserMobile.requestFocus();
                    return;
                }
                if(Mobile.length()<8)
                {
                    UserMobile.setError("Please input 11 digit mobile number");
                    UserMobile.requestFocus();
                    return;
                }
                if(Email.isEmpty())
                {
                    UserEmail.setError("Please input an email");
                    UserEmail.requestFocus();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(Email).matches())
                {
                    UserEmail.setError("Please input a valid email");
                    UserEmail.requestFocus();
                    return;
                }
                if(Password.isEmpty())
                {
                    UserPassword.setError("Please input a password");
                    UserPassword.requestFocus();
                    return;
                }
                if(Password.length()<8)
                {
                    UserPassword.setError("Minimum length of password should be 8");
                    UserPassword.requestFocus();
                    return;
                }
                //SignUpProgressbar.setVisibility(View.VISIBLE);
                myEdit.putString("FullName", UserFullName.getText().toString());
                myEdit.putString("Username", UserUsername.getText().toString());
                myEdit.putString("Address", UserAddress.getText().toString());
                myEdit.putString("Mobile", UserMobile.getText().toString());
                myEdit.putString("Email", UserEmail.getText().toString());
                myEdit.putString("Password", UserPassword.getText().toString());
                myEdit.commit();
                Intent intent = new Intent(getApplicationContext(),UserLogInActivity.class);
                startActivity(intent);
                //SignUpProgressbar.setVisibility(View.GONE);
                finish();
            }
        });

        signInText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),UserLogInActivity.class);
                startActivity(intent);
            }
        });

    }
}