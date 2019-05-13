package com.example.dogfinder;


import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.ImageButton;
import android.view.View;
import android.content.Intent;
import android.widget.TextView;

import org.w3c.dom.Text;

public class BreedOutput extends AppCompatActivity {
    private ImageButton takePictureButton;
    private Uri bmpUri;
    private ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breed_output);


        bmpUri = getIntent().getParcelableExtra("dog");     // to fetch the picture sent by our previous activity
        imageView = (ImageView) findViewById(R.id.finalDog);      // finds the right imageView in which we put our picture
        imageView.setImageURI(bmpUri);                            // sets picture

        takePictureButton = (ImageButton) findViewById(R.id.back);
    }

    public void backHome(View view) {
        Intent backHome = new Intent(this, MainActivity.class);
        startActivity(backHome);
    }
}
