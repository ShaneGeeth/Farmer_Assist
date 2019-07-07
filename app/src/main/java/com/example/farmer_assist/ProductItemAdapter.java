package com.example.farmer_assist;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ProductItemAdapter extends ArrayAdapter<Product> {
    private ProductHandler productHandler;
    private List<Product> productlist;

    public ProductItemAdapter(Context context, List<Product> objects, ProductHandler pHandler) {
        super(context,0, objects);
        this.productHandler=pHandler;
        productlist=objects;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final Product product=getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.product_list_item, parent, false);
        }
        TextView productName=convertView.findViewById(R.id.productNameTV);
        TextView productPrice=convertView.findViewById(R.id.productPriceTV);
        ImageView productImage=convertView.findViewById(R.id.productIV);
        Button deleteButton=convertView.findViewById(R.id.productDeleteBtn);
        Button viewButton=convertView.findViewById(R.id.productViewBtn);

        productName.setText(product.getName());
        productPrice.setText("Rs. "+product.getPrice());
        productImage.setImageBitmap(Utility.getImage(product.getImage()));

        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Warning");
                builder.setMessage("Would you want to remove this product?");
                // add the buttons
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        productlist.remove(position);
                        productHandler.removeProduct(product);

                        ShowProducts.notifyAdapter();
                    }
                });
                builder.setNegativeButton("Cancel", null);
                // create and show the alert dialog
                AlertDialog dialog = builder.create();
                dialog.show();



            }
        });

        return convertView;
    }
}
