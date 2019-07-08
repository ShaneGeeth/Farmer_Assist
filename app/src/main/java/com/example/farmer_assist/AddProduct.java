package com.example.farmer_assist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;

public class AddProduct extends AppCompatActivity {
    private static final int SELECT_PICTURE = 100;
    private Uri selectedImageUri;
    private ProductHandler productHandler;
    private EditText prouctNameEditText;
    private  EditText productDescriptionEditText;
    private  EditText productPriceEditText;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

         productHandler=new ProductHandler(this);

        //get data from UI components
         prouctNameEditText=findViewById(R.id.productNameET);
         productDescriptionEditText=findViewById(R.id.prductDescriptionET);
         productPriceEditText=findViewById(R.id.priceET);

        imageView=findViewById(R.id.imageView2);

        Button addImageBtn=findViewById(R.id.addImageBtn);
        Button storeButton=findViewById(R.id.storeBtn);

        addImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();
            }
        });
         final Context context=this;

        storeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(saveImageInDB()){
                    Toast.makeText(context, "You Added new Product", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(context, "Failed!", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    public void selectImage(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                selectedImageUri = data.getData();
                if (null != selectedImageUri) {
//                    imgView.setImageURI(selectedImageUri);
                    imageView.setImageURI(selectedImageUri);
                }
            }
        }
    }
    boolean saveImageInDB() {

        try {

            InputStream iStream = getContentResolver().openInputStream(selectedImageUri);
            if(iStream==null) return false;
            byte[] inputData = Utility.getBytes(iStream);
            Product newProduct=new Product();
            newProduct.setName(prouctNameEditText.getText().toString());
            newProduct.setDescription(productDescriptionEditText.getText().toString());
            newProduct.setPrice(Double.valueOf(productPriceEditText.getText().toString()));
            newProduct.setImage(inputData);
            productHandler.createProduct(newProduct);


            return true;
        } catch (IOException ioe) {
            Log.e("A", "<saveImageInDB> Error : " + ioe.getLocalizedMessage());

            return false;
        }

    }

    @Override
    public void onBackPressed()
    {
        Intent i=new Intent(this, ShowProducts.class);
        startActivity(i);
    }
}
