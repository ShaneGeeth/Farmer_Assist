package com.example.farmer_assist;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Person;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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
        Button updateButton=convertView.findViewById(R.id.productUpdateBtn);

        productName.setText(product.getName());
        productPrice.setText("Rs. "+product.getPrice());
        productImage.setImageBitmap(Utility.getImage(product.getImage()));

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.product_update_dialog);
                dialog.setTitle("Update");
                final EditText updateName=dialog.findViewById(R.id.seletedProductNameUpdateET);
                final EditText updateDesc=dialog.findViewById(R.id.seletedProductDescriptionUpdateET);
                final EditText updatePrice=dialog.findViewById(R.id.seletedProductPriceUpdateET);
                final ImageView updateImage=dialog.findViewById(R.id.selectedImageUpdate);
                Button updateButton=dialog.findViewById(R.id.selectedProductUpdateBtn);

                updateName.setText(product.getName());
                updateDesc.setText(product.getDescription());
                updatePrice.setText(""+product.getPrice());
                updateImage.setImageBitmap(Utility.getImage(product.getImage()));
                dialog.show();

                updateButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Product newProduct=new Product();
                        newProduct.setProduct_no(product.getProduct_id());
                        newProduct.setName(updateName.getText().toString());
                        newProduct.setDescription(updateDesc.getText().toString());
                        newProduct.setPrice(Double.parseDouble(updatePrice.getText().toString()));
//                        newProduct.setImage(updateImage.getResources().);
                        productHandler.updateProduct(newProduct);

                        ShowProducts.notifyAdapter();
                        dialog.dismiss();
                    }
                });

            }
        });

        viewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.dialog_show_prouct_item);
                dialog.setTitle(product.getName());
                TextView descTV=dialog.findViewById(R.id.productSelectedDescriptionTV);
                TextView priceTV=dialog.findViewById(R.id.productSelectedPriceTV);
                ImageView imageView=dialog.findViewById(R.id.productSelectedIV);
                Button okBtn=dialog.findViewById(R.id.dialogOkbtn);
                descTV.setText(product.getDescription());
                priceTV.setText("Price: Rs."+product.getPrice());
                imageView.setImageBitmap(Utility.getImage(product.getImage()));
                dialog.show();

                okBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

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
