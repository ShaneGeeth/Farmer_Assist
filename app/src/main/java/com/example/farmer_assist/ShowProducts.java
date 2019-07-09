package com.example.farmer_assist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.SearchEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.SearchView;

import com.shamanland.fab.FloatingActionButton;
import com.shamanland.fab.ShowHideOnScroll;

public class ShowProducts extends AppCompatActivity {
    private static ProductHandler productHandler;
    private static ListView listViewProduct;
    private static ProductItemAdapter productItemAdapter;
    private static Context context2;
    SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_products);
        context2=this;

        searchView=findViewById(R.id.simpleSearchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                searchProduct(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                searchProduct(s);

                return false;
            }
        });

        productHandler=new ProductHandler(this);
        final Context context=this;
        FloatingActionButton btnAdd=findViewById(R.id.fab);
        listViewProduct=findViewById(R.id.productLV);

        if(productHandler.getAllProducts()!=null){
            productItemAdapter = new ProductItemAdapter(this,productHandler.getAllProducts(),productHandler);
            listViewProduct.setAdapter(productItemAdapter);
        }




        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(context, AddProduct.class);
                startActivity(i);
            }
        });


    }

    @Override
    public void onBackPressed()
    {
        Intent i=new Intent(this, UserDashboard.class);
        startActivity(i);
    }

    public static void notifyAdapter(){
        productItemAdapter.notifyDataSetChanged();
        if(productHandler.getAllProducts()!=null){
            productItemAdapter = new ProductItemAdapter(context2,productHandler.getAllProducts(),productHandler);
            listViewProduct.setAdapter(productItemAdapter);
        }
    }
    private void searchProduct(String s){
        if(productHandler.searchProduct(s)!=null){
            productItemAdapter = new ProductItemAdapter(context2,productHandler.searchProduct(s),productHandler);
            listViewProduct.setAdapter(productItemAdapter);
        }
    }

}
