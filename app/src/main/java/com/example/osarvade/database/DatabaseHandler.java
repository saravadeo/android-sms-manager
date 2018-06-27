package com.example.osarvade.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by osarvade on 21-05-2016.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "EmailManager";
    private static final String TABLE_LOGIN = "login";

    private static final String KEY_EMAIL = "email_id";
    private static final String KEY_PASSWORD = "password";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_MESSAGE_TABLE = "CREATE TABLE " + TABLE_LOGIN + "("
                + KEY_EMAIL + " TEXT," + KEY_PASSWORD + " TEXT " + " )";
        db.execSQL(CREATE_MESSAGE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOGIN);
        onCreate(db);
    }

    public void addLoginDetails(String email, String password) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_EMAIL, email);
        values.put(KEY_PASSWORD, password);
        db.insert(TABLE_LOGIN, null, values);
        db.close();

    }

    public int isLoggedIn() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db == null || !db.isOpen())
            return 0;
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_LOGIN, null);
        if (!cursor.moveToFirst()) {
            return 0;
        }
        int count = cursor.getCount();
        cursor.close();
        if (count > 0)
            return 1;
        else
            return 0;
    }

    public void deleteAllMessges() {
        SQLiteDatabase db = this.getWritableDatabase();
        String DeleteQuery = "DELETE FROM " + TABLE_LOGIN;
        db.execSQL(DeleteQuery);
        db.close();
    }

}
