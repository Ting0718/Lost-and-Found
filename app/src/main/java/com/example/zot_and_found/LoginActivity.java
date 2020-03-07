package com.example.zot_and_found;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zot_and_found.Fragments.HomeFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class LoginActivity extends AppCompatActivity {

    public static final String TAG = "LogInActivity";

    private Button btnLogin;
    private EditText etEmail;
    private EditText etPassword;
    private TextView etNotRegistered;
    private TextView etMessage;

    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;


    //firebase instance

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        btnLogin = findViewById(R.id.btnLogin);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etNotRegistered = findViewById(R.id.etNotRegistered);
        etMessage = findViewById(R.id.etMessage);

        mFirebaseAuth = FirebaseAuth.getInstance();

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
                if (mFirebaseUser != null)
                {
                    Toast.makeText(LoginActivity.this, "Logged in", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(i);
                }
                else
                {
                }
            }
        };

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();


                if (password.isEmpty() && email.isEmpty())
                {
                    etMessage.setText("Empty username and password");
                    etMessage.setTextColor(Color.RED);
                }

                else if (email.isEmpty())
                {
                    etEmail.setError("Please enter an email");
                    etEmail.requestFocus();
                }

                else if (password.isEmpty())
                {
                    etPassword.setError("Please enter a password");
                    etPassword.requestFocus();
                }

                else if (!(email.isEmpty() && password.isEmpty()))
                {
                    mFirebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful())
                            {
                                // the password has to be least 6 characters
                                Log.e(TAG, "onComplete: Failed=" + task.getException().getMessage());
                                etMessage.setText( task.getException().getMessage());
                                etMessage.setTextColor(Color.RED);
                            }
                            else
                            {
                                //Toast.makeText(LoginActivity.this, "Login successfully", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(i);
                            }
                        }
                    });
                }

                else
                {
                    Toast.makeText(LoginActivity.this, "Error ocurred, please try again later", Toast.LENGTH_SHORT).show();
                }
            }
        });

        etNotRegistered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }
}

