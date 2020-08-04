package com.example.loginandregister;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity  {

    private TextView registTextView;
    private EditText userEmail,userPassword;
    private Button loginButton;

    OnLoginFormActivityListerner loginFormActivityListerner;

    public interface OnLoginFormActivityListerner{
        public void performRegister();
        public void performLogin(String email);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userEmail = findViewById(R.id.login_email);
        userPassword = findViewById(R.id.login_password);
        loginButton = findViewById(R.id.login_button);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performLogin();
            }
        });


        registTextView = findViewById(R.id.text_view_register);
        registTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //...//
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private void performLogin()
    {
        String email = userEmail.getText().toString();
        String password = userPassword.getText().toString();
        Call<User> call = MainActivity.apiInterface.performUserLogin(email,password);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.body().getResponse().equals("ok"))
                {
                    MainActivity.prefConfig.writeLoginStatus(true);
//                    loginFormActivityListerner.performLogin(response.body().getEmail());
                    MainActivity.prefConfig.displayToast("Login success...");
                    Intent intent = new Intent(LoginActivity.this,AfterLoginAlready.class);
                    startActivity(intent);
                }
                else if(response.body().getResponse().equals("failed"))
                {
                    MainActivity.prefConfig.displayToast("Login Failed...Please try again...");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
        userEmail.setText("");
        userPassword.setText("");
    }
}
