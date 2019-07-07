package com.example.farmer_assist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserHandler extends SQLiteOpenHelper {
    private User user;


    //colums
    private final String first_name="first_name";
    private final String last_name="last_name";
    private final String address="address";
    private final String email="email";
    private final String password="password";
    private final String telephone="telephone";

    public static final String DATABASE_NAME = "farm_assist_db";
    SQLiteDatabase mDatabase;
    private static final int DATABASE_VERSION = 1;

    public UserHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mDatabase = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE IF NOT EXISTS user (\n" +
                "    id  varchar(200) NOT NULL  CONSTRAINT user_pk PRIMARY KEY,\n" +
                "    first_name varchar(200) NOT NULL,\n" +
                "    last_name varchar(200) NOT NULL,\n" +
                "    email varchar(200) UNIQUE NOT NULL,\n" +
                "    address text  NOT NULL,\n" +
                "    password varchar(200) NOT NULL,\n" +
                "    telephone varchar(200) \n" +
                ");");
        db.execSQL("CREATE TABLE IF NOT EXISTS product (\n" +
                "    id INTEGER   NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                "    name varchar(200) NOT NULL,\n" +
                "    price double NOT NULL,\n" +
                "    description text  NOT NULL,\n" +
                "    user_id varchar(200) ,\n" +
                "    product_image blob, \n" +
                "    create_date varchar(200) \n" +
                ");");

    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS user");
        // Create tables again
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    public boolean createNewUser(User newUser){
        // creating UUID
        UUID uid = UUID.randomUUID();

        // checking hash code value
        String val=uid.toString();
        long newRowId = 0;
        //create new user in DB
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(this.first_name, newUser.getFirst_name());
        values.put(this.last_name, newUser.getLast_name());
        values.put(this.email, newUser.getEmail());
        values.put(this.address, newUser.getAddress());
        values.put(this.password, newUser.getPassword());
        values.put(this.telephone, newUser.getTelephone());
        values.put("id", val);

        // Inserting Row
        newRowId=db.insert("user", null, values);
        db.close(); // Closing database connection

        if (newRowId == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean removeNewUser(User currentUser){
        long newRowId = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        newRowId=db.delete("user", "id" + " = ?",
                new String[]{String.valueOf(currentUser.getId())});

        if (newRowId == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean updateNewUser(User newUser){
        long newRowId = 0;
        SQLiteDatabase db = this.getWritableDatabase();

        // Creating content values
        ContentValues values = new ContentValues();
        values.put(this.first_name, newUser.getFirst_name());
        values.put(this.last_name, newUser.getLast_name());
        values.put(this.address, newUser.getAddress());
        values.put(this.password, newUser.getPassword());
        values.put(this.telephone, newUser.getTelephone());
        // update row in students table base on students.is value
        newRowId=db.update("user", values, "id" + " = ?", new String[]{String.valueOf(newUser.getId())});

        if (newRowId == -1) {
            return false;
        } else {
            return true;
        }
    }

    public User authUser(String email, String password){
        List<User> users=this.getAllUsers();
        //get user by name username and return user by checking checking its password
        for (int i = 0; i <users.size() ; i++) {
            User user=users.get(i);
            if (user.getEmail().equals(email)){
                if (user.getPassword().equals(password)){
                    return user;
                }
            }
        }
        return null;
    }

    public List<User> getAllUsers(){
        List userList = new ArrayList<User>();
        // Select All Query
        String selectQuery = "SELECT * FROM user";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                User user = new User();
                user.setId(cursor.getString(cursor.getColumnIndex("id")));
                user.setAddress(cursor.getString(cursor.getColumnIndex(this.address)));
                user.setFirst_name(cursor.getString(cursor.getColumnIndex(this.first_name)));
                user.setLast_name(cursor.getString(cursor.getColumnIndex(this.last_name)));
                user.setPassword(cursor.getString(cursor.getColumnIndex(this.password)));
                user.setTelephone(cursor.getString(cursor.getColumnIndex(this.telephone)));
                user.setEmail(cursor.getString(cursor.getColumnIndex(this.email)));

                // Adding country to list
                userList.add(user);
            } while (cursor.moveToNext());
        }

        // return country list
        return userList;
    }


}
