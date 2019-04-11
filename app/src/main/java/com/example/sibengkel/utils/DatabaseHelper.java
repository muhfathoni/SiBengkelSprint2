package com.example.sibengkel.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.sibengkel.models.BookModels;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper implements Database {
    // Database Version
    private static final int DATABASE_VERSION = 13;

    // Database Name
    private static final String DATABASE_NAME = DatabaseContents.DATABASE.toString();


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + DatabaseContents.TABLE_USERS + "("
                + "_id INTEGER PRIMARY KEY,"
                + "name TEXT(100),"
                + "email TEXT(32),"
                + "password TEXT(256),"
                + "phone TEXT(32),"
                + "address TEXT(32),"
                + "status INTEGER DEFAULT 1,"
                + "date_added DATETIME"
                + ");");
        Log.d("CREATE DATABASE", "Create " + DatabaseContents.TABLE_USERS + " Successfully.");

        db.execSQL("CREATE TABLE " + DatabaseContents.TABLE_BOOK + "("
                + "_id INTEGER PRIMARY KEY,"
                + "nama_kendaraan TEXT(32),"
                + "email TEXT(32),"
                + "jenis_service TEXT(32),"
                + "tanggal TEXT(32),"
                + "jam TEXT(32),"
                + "status INTEGER DEFAULT 1,"
                + "date_added DATETIME"
                + ");");

        Log.d("CREATE DATABASE", "Create " + DatabaseContents.TABLE_BOOK + " Successfully.");
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseContents.TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + DatabaseContents.TABLE_BOOK);

        // Create tables again
        onCreate(db);
    }

    @Override
    public List<Object> select(String queryString) {
        try {
            SQLiteDatabase database = this.getWritableDatabase();
            List<Object> list = new ArrayList<Object>();
            Cursor cursor = database.rawQuery(queryString, null);

            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        ContentValues content = new ContentValues();
                        String[] columnNames = cursor.getColumnNames();
                        for (String columnName : columnNames) {
                            content.put(columnName, cursor.getString(cursor
                                    .getColumnIndex(columnName)));
                        }
                        list.add(content);
                    } while (cursor.moveToNext());
                }
            }
            cursor.close();
            database.close();
            return list;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int insert(String tableName, Object content) {
        try {
            SQLiteDatabase database = this.getWritableDatabase();

            int id = (int) database.insert(tableName, null,
                    (ContentValues) content);

            database.close();
            return id;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public boolean update(String tableName, Object content) {
        try {
            SQLiteDatabase database = this.getWritableDatabase();
            ContentValues cont = (ContentValues) content;
            // this array will always contains only one element.
            String[] array = new String[]{cont.get("_id")+""};
            database.update(tableName, cont, " _id = ?", array);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(String tableName, int id) {
        try {
            SQLiteDatabase database = this.getWritableDatabase();
            database.delete(tableName, " _id = ?", new String[]{id+""});
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean execute(String queryString) {
        try{
            SQLiteDatabase database = this.getWritableDatabase();
            database.execSQL(queryString);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public List<BookModels> allBookings(String email) {
        List<BookModels> bookings = new ArrayList<>();
        String selectQuery = "SELECT  * FROM tbl_book where email = '" + email + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                BookModels booking = new BookModels();
                booking.setId(cursor.getLong(0));
                booking.setNamaKendaraan(cursor.getString(1));
                booking.setEmail(cursor.getString(2));
                booking.setJenisService(cursor.getString(3));
                booking.setTanggal(cursor.getString(4));
                booking.setJam(cursor.getString(5));
                bookings.add(booking);
            } while (cursor.moveToNext());
        }

        db.close();
        return bookings;
    }
}
