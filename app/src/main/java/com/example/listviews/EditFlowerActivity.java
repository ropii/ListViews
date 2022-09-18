package com.example.listviews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

public class EditFlowerActivity extends AppCompatActivity implements View.OnClickListener {
    EditText etTitle,etSubtitle,etPrice;
    Button btnSave,btnCancel;
    ImageView ivProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_flower);
        etTitle=findViewById(R.id.etTitle);
        etPrice=findViewById(R.id.etPrice);
        etSubtitle=findViewById(R.id.etSubtitle);
        btnSave=findViewById(R.id.btnSave);
        btnCancel=findViewById(R.id.btnCancel);
        btnSave.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        ivProduct=findViewById(R.id.ivProduct);
        //connect to intent if it is edit mode
        Intent intent= getIntent();
        if(intent.getExtras()!=null)
        {
            String title = intent.getExtras().getString("title");
            String subtitle = intent.getExtras().getString("sub");
            int price = intent.getExtras().getInt("price");
            int image=intent.getExtras().getInt("imageId");

            etTitle.setText(title);
            etSubtitle.setText(subtitle);
            etPrice.setText(String.valueOf(price));
            ivProduct.setImageResource(image);
            //add the image id to the ImageView using the setTag function
            ivProduct.setTag(image);
        }
    }

    @Override
    public void onClick(View view) {
        if (btnCancel==view)
        {
            setResult(RESULT_CANCELED,null);
            finish();
        }
        else if(btnSave==view)//option 2 - save the data and go to first screen
        {
            if(etPrice.getText().toString().length()>0&&
                    etTitle.getText().toString().length()>0&&etSubtitle.getText().toString().length()>0 &&ivProduct.getDrawable()!=null) {
                Intent intent = new Intent();
                intent.putExtra("price", Integer.valueOf(etPrice.getText().toString()));
                intent.putExtra("title", etTitle.getText().toString());
                intent.putExtra("sub", etSubtitle.getText().toString());
                intent.putExtra("imageId", (int)ivProduct.getTag());

                setResult(RESULT_OK, intent);
                finish();
            }
            else
                Toast.makeText(this,"please fill all fields", Toast.LENGTH_LONG).show();
        }

    }
}