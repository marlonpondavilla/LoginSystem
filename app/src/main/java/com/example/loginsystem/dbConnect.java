package com.example.loginsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class dbConnect extends SQLiteOpenHelper {

    private Context context;

    private static final String dbName = "LoginSystemDB";
    private static final int dbVersion = 1;
    private static final String dbTable = "Users";

    private static final String COLUMN_ID = "ID";
    private static final String COLUMN_FIRST_NAME = "FIRST_NAME";
    private static final String COLUMN_MIDDLE_NAME = "MIDDLE_NAME";
    private static final String COLUMN_LAST_NAME = "LAST_NAME";
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
        String query = "CREATE TABLE " + dbTable + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_FIRST_NAME + " TEXT, " +
                COLUMN_MIDDLE_NAME + " TEXT, " +
                COLUMN_LAST_NAME + " TEXT, " +
                COLUMN_DOB + " DATE, " +
                COLUMN_EMAIL_ADDRESS + " TEXT, " +
                COLUMN_PHONE_NUMBER + " TEXT, " +
                COLUMN_USERNAME + " TEXT, " +
                COLUMN_PASSWORD + " TEXT" +
                ")";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + dbTable);
        onCreate(sqLiteDatabase);
    }

    public void addUser(Users user) {
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

        try {
            long result = DB.insert(dbTable, null, values);
            if (result == -1) {
                Toast.makeText(context, "Database insertion failed", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(context, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
        } finally {
            DB.close();
        }
    }

    public Boolean isUsernameExist(String username){
        SQLiteDatabase DB = this.getReadableDatabase();
        try (Cursor cursor = DB.rawQuery("SELECT USERNAME FROM USERS WHERE USERNAME = ?", new String[]{username})) {

            return cursor.getCount() > 0;
        }
    }

    public Boolean checkUsernameAndPassword(String username, String password){
        SQLiteDatabase DB = this.getReadableDatabase();
        Cursor cursor = DB.rawQuery("SELECT USERNAME, PASSWORD FROM USERS WHERE USERNAME = ? AND PASSWORD = ?", new String[] {username, password});

        return cursor.getCount() > 0;
    }
}
