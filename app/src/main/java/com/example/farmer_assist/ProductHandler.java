package com.example.farmer_assist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProductHandler extends SQLiteOpenHelper {
    private  Product product;
    public static final String DATABASE_NAME = "farm_assist_db";
    SQLiteDatabase mDatabase;
    private static final int DATABASE_VERSION = 1;


    //attrib
    private final String name="name";
    private final String price="price";
    private final String image="product_image";
    private final String description="description";
    private final String create_date="create_date";

    public ProductHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mDatabase = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS product");
        // Create tables again
        onCreate(db);
    }


    public boolean createProduct(Product newProduct){
        //create new product in db
        long newRowId = 0;
        //create new user in DB
        SQLiteDatabase db = this.getWritableDatabase();
        UUID uid = UUID.randomUUID();

        // checking hash code value
        String val=uid.toString();

        ContentValues values = new ContentValues();
        values.put(this.name, newProduct.getName());
        values.put(this.description, newProduct.getDescription());
        values.put(this.price, newProduct.getPrice());
        values.put(this.create_date, newProduct.getCreate_date());
        values.put(this.image,newProduct.getImage() );
        values.put("id", val);
        // Inserting Row
        newRowId=db.insert("product", null, values);
        db.close(); // Closing database connection

        if (newRowId == -1) {
            return false;
        } else {
            return true;
        }
    }

    public  boolean removeProduct(Product currentProduct){
        long newRowId = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        newRowId=db.delete("product", "id" + " = ?",
                new String[]{currentProduct.getProduct_id()});

        if (newRowId == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean updateProduct(Product newProduct){
        //update product in db
        return false;
    }

    public List<Product> getAllProducts(){
        List productList = new ArrayList<User>();
        // Select All Query
        String selectQuery = "SELECT * FROM product";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Product product = new Product();
                product.setProduct_no(cursor.getString(cursor.getColumnIndex("id")));
                product.setName(cursor.getString(cursor.getColumnIndex(this.name)));
                product.setDescription(cursor.getString(cursor.getColumnIndex(this.description)));
                product.setPrice(Double.parseDouble(cursor.getString(cursor.getColumnIndex(this.price))));
                product.setImage( cursor.getBlob(cursor.getColumnIndex(this.image)));
                product.setCreate_date(cursor.getString(cursor.getColumnIndex(this.create_date)));
                // Adding country to list
                productList.add(product);
            } while (cursor.moveToNext());
        }

        //return all products
        return productList;
    }

    public Product[] findProductById(String productName){
        //return all products that contain productName
        return null;
    }



}
