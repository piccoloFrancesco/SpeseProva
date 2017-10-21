package com.example.francesco.speseprova;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by Francesco on 21/10/2017.
 */

public class ReceiptDBManager extends SQLiteOpenHelper {
    // Database Info
    private static final String DATABASE_NAME = "ReceiptDatabase";
    private static final int DATABASE_VERSION = 1;

    // Table Names
    private static final String TABLE_RECEIPTS = "Receipts";
    // Post Table Columns
    private static final String KEY_ID = "id";
    private static final String KEY_VALUE = "value";
    private static final String KEY_DATE = "date";
    private static final String KEY_DES = "description";

    private static ReceiptDBManager sInstance;

    public static synchronized ReceiptDBManager getInstance(Context context) {
        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = new ReceiptDBManager(context.getApplicationContext());
        }//if
        return sInstance;
    }//getIstance

    /**
     * Constructor should be private to prevent direct instantiation.
     * Make a call to the static method "getInstance()" instead.
     */
    private ReceiptDBManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }//PostDatabaseHelper

    // Called when the database connection is being configured.
    // Configure database settings for things like foreign key support, write-ahead logging, etc.
    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }//onConfigure

    // Called when the database is created for the FIRST time.
    // If a database already exists on disk with the same DATABASE_NAME, this method will NOT be called.
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_POSTS_TABLE = "CREATE TABLE " + TABLE_RECEIPTS +
                "(" +
                KEY_ID + " INTEGER PRIMARY KEY," + // Define a primary key
                KEY_VALUE + " REAL " + KEY_DATE + " TEXT" + KEY_DES +"TEXT"+
                ")";
        db.execSQL(CREATE_POSTS_TABLE);
    }//onCreate

    // Called when the database needs to be upgraded.
    // This method will only be called if a database already exists on disk with the same DATABASE_NAME,
    // but the DATABASE_VERSION is different than the version of the database that exists on disk.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            // Simplest implementation is to drop all old tables and recreate them
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECEIPTS);
            onCreate(db);
        }//if
    }//onUpgrade

    // Insert a post into the database
    public void addReceipt(Receipt rec) {
        // Create and/or open the database for writing
        SQLiteDatabase db = getWritableDatabase();

        // It's a good idea to wrap our insert in a transaction. This helps with performance and ensures
        // consistency of the database.
        db.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(KEY_VALUE, rec.getValue());
            values.put(KEY_DATE,rec.getDate());
            values.put(KEY_DES, rec.getDesc());
            // Notice how we haven't specified the primary key. SQLite auto increments the primary key column.
            db.insertOrThrow(TABLE_RECEIPTS, null, values);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d(TAG, "Error while trying to add post to database");
        } finally {
            db.endTransaction();
        }//finally
    }//addReceipt

    public String getReceipt(int i){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = null;
        String rec="";
        try {
            cursor = DB.rawQuery("SELECT * FROM "+TABLE_RECEIPTS+" WHERE "+KEY_ID+"=?", new String[] {i + ""});
            if(cursor.getCount() > 0) {
                cursor.moveToFirst();
                rec = cursor.getString(cursor.getColumnIndex(KEY_ID))+"|"+cursor.getString(cursor.getColumnIndex(KEY_VALUE))+"|"+
                        cursor.getString(cursor.getColumnIndex(KEY_DATE))+"|"+cursor.getString(cursor.getColumnIndex(KEY_DES));
                }
            }finally {
                cursor.close();
            }
        return rec;
    }//getReceipt

    // Get all receipts in the database
    public List<Receipt> getAllReceipts() {
        List<Receipt> rec = new ArrayList<>();

        // SELECT * FROM POSTS
        // LEFT OUTER JOIN USERS
        // ON POSTS.KEY_POST_USER_ID_FK = USERS.KEY_USER_ID
        String RECEIPT_SELECT_QUERY =
                String.format("SELECT * FROM %s ",
                        TABLE_RECEIPTS);

        // "getReadableDatabase()" and "getWriteableDatabase()" return the same object (except under low
        // disk space scenarios)
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(RECEIPT_SELECT_QUERY, null);
        try {
            if (cursor.moveToFirst()) {
                do {
                    Receipt newRec = new Receipt();
                    newRec.setImport(cursor.getDouble(cursor.getColumnIndex(KEY_VALUE)));
                    newRec.setDate(cursor.getString(cursor.getColumnIndex(KEY_DATE)));
                    newRec.setDesc(cursor.getString(cursor.getColumnIndex(KEY_DES)));
                    rec.add(newRec);
                } while(cursor.moveToNext());
            }//if
        } catch (Exception e) {
            Log.d(TAG, "Error while trying to get posts from database");
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }//if
        }//finally
        return rec;
    }//getAllReceipts

    // Delete all receipt and users in the database
    public void deleteAllReceipts() {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();
        try {
            // Order of deletions is important when foreign key relationships exist.
            db.delete(TABLE_RECEIPTS, null, null);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.d(TAG, "Error while trying to delete all posts and users");
        } finally {
            db.endTransaction();
        }//finally
    }//deleteAllReceipts
}//ReceiptDBManager