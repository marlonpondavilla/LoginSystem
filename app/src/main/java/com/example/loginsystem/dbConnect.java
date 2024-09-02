package com.example.loginsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class dbConnect extends SQLiteOpenHelper {

    private Context context;

    private static String dbName = "LoginSystemDB";
    private static int dbVersion = 1;
    private String dbTable = "Users";

    private static final String COLUMN_ID = "ID";
    private static final String COLUMN_FIRST_NAME = "FIRST_NAME";
    private static final String COLUMN_MIDDLE_NAME = "FIRST_NAME";
    private static final String COLUMN_LAST_NAME = "FIRST_NAME";
    private static final String COLUMN_DOB = "DOB";
    private static final String COLUMN_EMAIL_ADDRESS = "EMAIL_ADDRESS";
    private static final String COLUMN_PHONE_NUMBER = "PHONE_NUMBER";
    private static final String COLUMN_USERNAME = "USERNAME";
    private static final String COLUMN_PASSWORD = "PASSWORD";


    public dbConnect(@Nullable Context context) {
        super(context, dbName, null, dbVersion);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + dbTable + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                COLUMN_FIRST_NAME + " TEXT," +
                COLUMN_MIDDLE_NAME + " TEXT," +
                COLUMN_LAST_NAME + " TEXT," +
                COLUMN_DOB + " DATE," +
                COLUMN_EMAIL_ADDRESS + " TEXT," +
                COLUMN_PHONE_NUMBER + " TEXT," +
                COLUMN_USERNAME + " TEXT," +
                COLUMN_PASSWORD + " TEXT" +
                ")";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + dbTable);
        onCreate(sqLiteDatabase);
    }

    public void addUser(Users user){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_FIRST_NAME, user.getFirstname());
        values.put(COLUMN_MIDDLE_NAME, user.getMidName());
        values.put(COLUMN_LAST_NAME, user.getLastname());
        values.put(COLUMN_DOB, user.getDob());
        values.put(COLUMN_EMAIL_ADDRESS, user.getEmailAddress());
        values.put(COLUMN_PHONE_NUMBER, user.getPhoneNumber());
        values.put(COLUMN_USERNAME, user.getUsername());
        values.put(COLUMN_PASSWORD, user.getPassword());

        long DB_Result = DB.insert(dbTable, null, values);
        
        if(DB_Result == -1){
            Toast.makeText(context, "Database failed", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "Records submitted successfully", Toast.LENGTH_SHORT).show();
        }
    }
}
