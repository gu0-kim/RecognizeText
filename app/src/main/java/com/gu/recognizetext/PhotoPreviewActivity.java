package com.gu.recognizetext;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bumptech.glide.Glide;

import uk.co.senab.photoview.PhotoView;

public class PhotoPreviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_preview);
        PhotoView photoView = (PhotoView) findViewById(R.id.photoView);
        String filepath = getIntent().getStringExtra("filepath");
        if (filepath != null && !filepath.equals("")) {
            Glide.with(this).load(filepath).into(photoView);
        }
    }
}
