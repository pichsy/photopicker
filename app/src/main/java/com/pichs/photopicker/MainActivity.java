package com.pichs.photopicker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dena.lcm.photopicker.PhotoPickerActivity;

import java.io.File;

public class MainActivity extends Activity {

    private ImageView iamges;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    public void open(View view) {
        startActivityForResult(new Intent(this, PhotoPickerActivity.class), 11);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            String path = data.getStringExtra("imagePath");
            Toast.makeText(this, path, Toast.LENGTH_SHORT).show();
            Glide.with(MainActivity.this)
                    .load(new File(path))
                    .centerCrop()
                    .into(iamges);
        }
    }

    private void initView() {
        iamges = (ImageView) findViewById(R.id.ac_imgs);
        iamges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                open(v);
            }
        });
    }
}
