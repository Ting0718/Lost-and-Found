package com.example.zot_and_found;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {
    private Button btnNewUserSignUp;
    private EditText etNewUsername;
    private EditText etNewPassword;
    //firebase instance

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        btnNewUserSignUp = findViewById(R.id.btnNewUserSignUp);
        etNewUsername = findViewById(R.id.etNewEmail);
        etNewPassword = findViewById(R.id.etNewPassword);

        btnNewUserSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etNewUsername.getText().toString() != "" && etNewPassword.getText().toString() != ""){
                    //add new user to database
                    Intent i = new Intent(SignUpActivity.this, LoginActivity.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(SignUpActivity.this, "Cannot have an empty username/password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
