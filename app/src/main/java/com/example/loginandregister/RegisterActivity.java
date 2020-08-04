package com.example.loginandregister;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.camera2.CaptureRequest;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private EditText userEmail,userPassword;
    private Button registerButton;

    private EditText userName,userSurname,userPhoneNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userName = findViewById(R.id.register_name);
        userSurname = findViewById(R.id.register_surname);
        userEmail = findViewById(R.id.register_email);
        userPhoneNumber = findViewById(R.id.register_mobilenumber);
        userPassword = findViewById(R.id.register_password);

        registerButton = findViewById(R.id.register_button);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                performRegistration();
            }
        });
    }

    public void performRegistration(){
        String name = userName.getText().toString();
        String surname = userSurname.getText().toString();
        String email = userEmail.getText().toString();
        String phoneNumber = userPhoneNumber.getText().toString();
        String password = userPassword.getText().toString();

        Call<User> call = MainActivity.apiInterface.performRegistration(name,surname,email,phoneNumber,password);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.body().getResponse().equals("ok"))
                {
                    MainActivity.prefConfig.displayToast("Registration success...");
                }
                else if(response.body().getResponse().equals("exist"))
                {
                    MainActivity.prefConfig.displayToast("User already exist...");
                }
                else if(response.body().getResponse().equals("error"))
                {
                    MainActivity.prefConfig.displayToast("Something went wrong...");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
        userName.setText("");
        userSurname.setText("");
        userEmail.setText("");
        userPhoneNumber.setText("");
        userPassword.setText("");
    }
}
