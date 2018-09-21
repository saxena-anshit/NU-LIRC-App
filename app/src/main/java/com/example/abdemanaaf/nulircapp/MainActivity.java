package com.example.abdemanaaf.nulircapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView libraryApp = findViewById(R.id.libraryApp);
        TextView toContinue = findViewById(R.id.toContinue);
        ImageView logo = findViewById(R.id.logo);
        RelativeLayout relativeLayout = findViewById(R.id.rl);

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.my_transition);

        libraryApp.startAnimation(animation);
        logo.startAnimation(animation);
        toContinue.startAnimation(animation);

        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginPage.class);
                startActivity(intent);
            }
        });
    }
}
