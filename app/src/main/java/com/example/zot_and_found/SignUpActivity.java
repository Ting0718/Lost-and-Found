package com.example.zot_and_found;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {
    private Button btnNewUserSignUp;
    private EditText etNewEmail;
    private EditText etNewPassword;
    private TextView etHasAccount;
    private TextView etMessage;

    FirebaseAuth mFirebaseAuth;
    FirebaseUser mFirebaseUser;
    public static final String TAG = "SignUpActivity";
    DatabaseReference databaseReference;

    //firebase instance

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btnNewUserSignUp = findViewById(R.id.btnNewUserSignUp);
        etNewEmail = findViewById(R.id.etNewEmail);
        etNewPassword = findViewById(R.id.etNewPassword);
        etHasAccount = findViewById(R.id.etHasAccount);
        etMessage = findViewById(R.id.etMessage);

        databaseReference = FirebaseDatabase.getInstance().getReference("user");
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();

        btnNewUserSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etNewEmail.getText().toString();
                String password = etNewPassword.getText().toString();

                if (password.isEmpty() && email.isEmpty())
                {
                    etMessage.setText("Empty username and password");
                    etMessage.setTextColor(Color.RED);
                }

                else if (email.isEmpty())
                {
                    etNewEmail.setError("Please enter an email");
                    etNewEmail.requestFocus();
                }

                else if (password.isEmpty())
                {
                    etNewPassword.setError("Please enter a password");
                    etNewPassword.requestFocus();
                }

                else if (password.length() < 6)
                {
                    etNewPassword.setError("the password has to be least 6 characters");
                    etNewPassword.requestFocus();
                }

                else if (!(email.isEmpty() && password.isEmpty()))
                {
                    mFirebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                        @RequiresApi(api = Build.VERSION_CODES.O)
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful())
                            {
                                Log.e(TAG, "onComplete: Failed=" + task.getException().getMessage());
                                etMessage.setText( task.getException().getMessage());
                                etMessage.setTextColor(Color.RED);

                            }
                            else
                            {
                                Intent i = new Intent(SignUpActivity.this, MainActivity.class);
                                startActivity(i);
                            }
                        }
                    });
                }

                else {
                    etMessage.setText("Error occurred, please try again later");
                }
            }
        });

        etHasAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });
    }
}
