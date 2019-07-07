package com.example.farmer_assist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.melnykov.fab.FloatingActionButton;

public class ShowProducts extends AppCompatActivity {
    ProductHandler productHandler;
    ListView listViewProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_products);

        productHandler=new ProductHandler(this);
        final Context context=this;
        FloatingActionButton btnAdd=findViewById(R.id.fab);
        listViewProduct=findViewById(R.id.productLV);

        if(productHandler.getAllProducts()!=null){
            ProductItemAdapter weatherAdapter = new ProductItemAdapter(this,productHandler.getAllProducts());
            listViewProduct.setAdapter(weatherAdapter);
        }




        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(context, AddProduct.class);
                startActivity(i);
            }
        });
    }
}
