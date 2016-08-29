package com.example.jo_shinichi1.bisasewa.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.example.jo_shinichi1.bisasewa.Adapter.Mysingleton;
import com.example.jo_shinichi1.bisasewa.Adapter.MysingletonCompat;
import com.example.jo_shinichi1.bisasewa.R;
import com.squareup.picasso.Picasso;

public class ContactDetails extends AppCompatActivity {
    ImageView ivImage;
    ImageLoader imageLoader;
    TextView tvName,tvEmail,tvMobile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);
        ivImage = (ImageView) findViewById(R.id.ivImagePars);
        tvName = (TextView) findViewById(R.id.TextViewNama);
        tvEmail = (TextView) findViewById(R.id.textViewEmail);
        tvMobile = (TextView) findViewById(R.id.textViewMobile);
        Intent intent = getIntent();
        String image_url = intent.getStringExtra("key_img_id");
        Log.e(this.getClass().getName(),image_url);
        tvName.setText(getIntent().getStringExtra("key_name_id"));
        tvEmail.setText(getIntent().getStringExtra("key_email_id"));
        tvMobile.setText(getIntent().getStringExtra("key_mobile_id"));

//        imageLoader = MysingletonCompat.getMysingleton(this).getImageLoader();
//        imageLoader.get(image_url, ImageLoader.getImageListener(ivImage,
//                R.drawable.quantathree, R.drawable.quantafour));

        Picasso.with(this).load(image_url).into(ivImage);
    }
}
