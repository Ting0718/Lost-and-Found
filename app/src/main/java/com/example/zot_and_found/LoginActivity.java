package com.example.zot_and_found;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class LoginActivity extends AppCompatActivity {
    private Button btnSignUp;
    private Button btnLogin;
    private EditText etUsername;
    private EditText etPassword;

    //firebase instance

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnLogin = findViewById(R.id.btnLogin);
        etUsername = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(i);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etUsername.getText().toString() != "" && etPassword.getText().toString() != ""){
                    //search for user
                    //set user as logged in user
                    //start main activity with feed fragment as starting fragment
                }
            }
        });
    }
}

