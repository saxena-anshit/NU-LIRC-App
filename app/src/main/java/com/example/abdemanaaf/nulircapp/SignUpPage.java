package com.example.abdemanaaf.nulircapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpPage extends AppCompatActivity {

    private EditText mName = findViewById(R.id.name_sign_up);
    private EditText mUsername = findViewById(R.id.username_sign_up);
    private EditText mPassword = findViewById(R.id.password_sign_up);
    private FirebaseAuth mAuth;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://nu-lirc-app.firebaseio.com/").child("Users");

        TextView linkLogin = findViewById(R.id.link_login);
        Button createButton = findViewById(R.id.createButton);

        linkLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpPage.this, LoginPage.class);
                startActivity(intent);
            }
        });

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRegister();
            }
        });
    }

    private void startRegister() {

        final String name = mName.getText().toString().trim();
        final String email = mUsername.getText().toString().trim();
        String password = mPassword.getText().toString().trim();

        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {

            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()) {
                        String userId = mAuth.getCurrentUser().getUid();
                        DatabaseReference currentUserDb = mDatabase.child(userId);

                        currentUserDb.child("name").setValue(name);
                        currentUserDb.child("username").setValue(email);

                        Intent quickLinksIntent = new Intent(SignUpPage.this, QuickLinks.class);
                        quickLinksIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(quickLinksIntent);
                    }

                }
            });

        }

    }
}
