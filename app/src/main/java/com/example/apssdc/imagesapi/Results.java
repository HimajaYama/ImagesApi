package com.example.apssdc.imagesapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Results extends AppCompatActivity {
    ImageView imageview;
    TextView t1,t2,t3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        imageview=findViewById(R.id.image);
        t1=findViewById(R.id.likes);
        t2=findViewById(R.id.views);
        t3=findViewById(R.id.tags);
        String[] s=getIntent().getStringArrayExtra("data");
        Picasso.with(this).load(s[0]).into(imageview);
        t1.setText(s[1]);
        t2.setText(s[2]);
        t3.setText(s[3]);
    }
}
