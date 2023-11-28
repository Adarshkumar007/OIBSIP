package com.example.unitunify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class splashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView imageView=(ImageView)findViewById(R.id.imageView);
        Glide.with(this).asGif().load(R.raw.logo).into(imageView);
        Toast.makeText(splashScreen.this, "Welcome to UnitUnify", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent=new Intent(splashScreen.this,home.class);
                startActivity(intent);
                finish();
            }
        },3000);
    }
}