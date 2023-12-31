package com.msku.example.mobileapplicationweek3;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class PostActivity extends AppCompatActivity {

    ImageView imageView;
    TextView txtMessage;
    ImageButton btnOk;
    ImageButton btnCancel;
    static final int CAPTURE_İMAGE=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        txtMessage = findViewById(R.id.txtMessage);
        imageView = findViewById(R.id.imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,CAPTURE_İMAGE);

            }
        });

        btnOk= findViewById(R.id.btnOkey);
        btnCancel= findViewById(R.id.btnCancel);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putCharSequence("msg",txtMessage.getText());
                bundle.putParcelable("bitmap",((BitmapDrawable)imageView.getDrawable()).getBitmap());
                intent.putExtras(bundle);
                setResult(Activity.RESULT_OK,intent);
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(Activity.RESULT_CANCELED);
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode== CAPTURE_İMAGE && resultCode == RESULT_OK){
            Bundle bundle = data.getExtras();
            Bitmap image = (Bitmap) bundle.get("data");
            imageView.setImageBitmap(image);

        }
    }
}