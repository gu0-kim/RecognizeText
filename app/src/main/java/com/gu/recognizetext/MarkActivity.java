package com.gu.recognizetext;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class MarkActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark);
        String path = getIntent().getStringExtra("path");
        ImageView imageView = (ImageView) findViewById(R.id.ivbg);
        Glide.with(this).load(path).into(imageView);
    }
}
