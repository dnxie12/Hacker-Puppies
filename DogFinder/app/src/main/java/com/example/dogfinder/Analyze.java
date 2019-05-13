package com.example.dogfinder;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageButton;


public class Analyze extends AppCompatActivity {
    private ImageView imageView;

    private Uri bmpUri;

    private ImageButton takePictureButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analyze);

        bmpUri = getIntent().getParcelableExtra("dog");
        imageView = (ImageView) findViewById(R.id.userPic);

        imageView.setImageURI(bmpUri);


        takePictureButton = (ImageButton) findViewById(R.id.Analysis);

    }

    public void swapLoading(View view) {
        Intent swapLoad = new Intent(this, LoadingScreen.class);
        swapLoad.putExtra("dog", bmpUri);
        startActivity(swapLoad);
    }



}
