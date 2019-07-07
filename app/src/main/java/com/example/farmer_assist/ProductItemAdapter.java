package com.example.farmer_assist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ProductItemAdapter extends ArrayAdapter<Product> {
    public ProductItemAdapter(Context context, List<Product> objects) {
        super(context,0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Product product=getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.product_list_item, parent, false);
        }
        TextView productName=convertView.findViewById(R.id.productNameTV);
        TextView productPrice=convertView.findViewById(R.id.productPriceTV);
        ImageView productImage=convertView.findViewById(R.id.productIV);

        productName.setText(product.getName());
        productPrice.setText("Rs. "+product.getPrice());
        productImage.setImageBitmap(Utility.getImage(product.getImage()));

        return convertView;
    }
}
