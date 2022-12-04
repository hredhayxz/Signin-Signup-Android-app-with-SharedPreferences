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
import android.widget.Toast;

public class UserLogInActivity extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor myEdit;
    String DBEmail,DBPassword;
    Boolean isLogin;

    EditText SignInEmail,SignInPassword;
    Button SignInButton;
    TextView SignUpText;
    ProgressBar SignInProgressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_log_in);
        setTitle("Sign In");

        sharedPreferences=getSharedPreferences("MySharedPref",MODE_PRIVATE);
        myEdit = sharedPreferences.edit();

        DBEmail=sharedPreferences.getString("Email","");
        DBPassword=sharedPreferences.getString("Password","");
        isLogin=sharedPreferences.getBoolean("isLogin",false);

        SignInEmail=findViewById(R.id.SignInEmail);
        SignInPassword=findViewById(R.id.SignInPassword);
        SignInButton=findViewById(R.id.SignInButton);
        SignUpText=findViewById(R.id.SignUpText);
        SignInProgressbar=findViewById(R.id.SignInProgressbar);

        SignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserLogin();
            }

            private void UserLogin() {
                String Email = SignInEmail.getText().toString().trim();
                String Password = SignInPassword.getText().toString().trim();

                if(Email.isEmpty())
                {
                    SignInEmail.setError("Please input your email");
                    SignInEmail.requestFocus();
                    return;
                }
                if(!Patterns.EMAIL_ADDRESS.matcher(Email).matches())
                {
                    SignInEmail.setError("Please input your valid email");
                    SignInEmail.requestFocus();
                    return;
                }
                if(Password.isEmpty())
                {
                    SignInPassword.setError("Please input your password");
                    SignInPassword.requestFocus();
                    return;
                }
                if(Password.length()<8)
                {
                    SignInPassword.setError("Password is not match");
                    SignInPassword.requestFocus();
                    return;
                }

                if(DBEmail.equals(Email) && DBPassword.equals(Password))
                {
                    myEdit.putBoolean("isLogin",true);
                    myEdit.commit();
                    //SignInProgressbar.setVisibility(View.VISIBLE);
                    Toast.makeText(UserLogInActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                    finish();
                    //SignInProgressbar.setVisibility(View.GONE);
                }
                else
                {
                    Toast.makeText(UserLogInActivity.this, "User not registered", Toast.LENGTH_SHORT).show();
                }

            }
        });

        SignUpText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),UserSignUpActivity.class);
                startActivity(intent);
            }
        });

    }
    protected void onStart() {

        if(isLogin)
        {
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
            finish();
        }

        super.onStart();
    }
}