package com.example.zot_and_found;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {
    private Button btnNewUserSignUp;
    private EditText etNewEmail;
    private EditText etNewPassword;
    private TextView etHasAccount;
    FirebaseAuth mFirebaseAuth;
    //firebase instance

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btnNewUserSignUp = findViewById(R.id.btnNewUserSignUp);
        etNewEmail = findViewById(R.id.etNewEmail);
        etNewPassword = findViewById(R.id.etNewPassword);

        etHasAccount = findViewById(R.id.etHasAccount);

        mFirebaseAuth = FirebaseAuth.getInstance();

        btnNewUserSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etNewEmail.getText().toString();
                String password = etNewPassword.getText().toString();

                if (email.isEmpty())
                {
                    etNewEmail.setError("Please enter an email");
                    etNewEmail.requestFocus();
                }

                if (password.isEmpty())
                {
                    etNewPassword.setError("Please enter a password");
                    etNewPassword.requestFocus();
                }



                /*
                if (
                    //add new user to database
                    Intent i = new Intent(SignUpActivity.this, LoginActivity.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(SignUpActivity.this, "Cannot have an empty username/password", Toast.LENGTH_SHORT).show();
                }

                 */
            }
        });
    }
}
