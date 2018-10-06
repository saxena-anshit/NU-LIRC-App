package com.example.abdemanaaf.nulircapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginPage extends AppCompatActivity {

    private FirebaseAuth mAuth;

    private EditText mUsername;
    private EditText mPassword;
    private ProgressDialog mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        mUsername = findViewById(R.id.email_login);
        mPassword = findViewById(R.id.password_login);

        mAuth = FirebaseAuth.getInstance();
        mProgress = new ProgressDialog(this);

        TextView signUp = findViewById(R.id.sign_up_link);
        Button login = findViewById(R.id.loginButton);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginPage.this, SignUpPage.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLogin();
            }
        });
    }

    private void checkLogin() {

        String email = mUsername.getText().toString().trim();
        String password = mPassword.getText().toString().trim();

        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {

            mProgress.setMessage("Logging In...");
            mProgress.show();

            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(LoginPage.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()) {

                        mProgress.dismiss();

                        Intent quickLinksIntent = new Intent(LoginPage.this, QuickLinks.class);
                        quickLinksIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(quickLinksIntent);
                        finish();

                    } else {

                        mProgress.dismiss();
                        Toast.makeText(LoginPage.this,
                                "Error Logging In" + "\n" + task.getException().getMessage(),
                                Toast.LENGTH_SHORT).show();
                    }

                }
            });

        }
    }
}

