package com.example.francesco.speseprova;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Francesco on 20/10/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ReceiptManager";
    private static final String TABLE_RECEIPT = "Receipt";

    private static final String KEY_ID = "Id";
    private static final String KEY_AMOUNT = "Amount";
    private static final String KEY_DATE = "Date";
    private static final String KEY_DESC = "Description";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }//DatabaseHandler
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_RECEIPT_TABLE = "CREATE TABLE " + TABLE_RECEIPT + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_AMOUNT + " TEXT,"
                + KEY_DATE+" TEXT"+KEY_DESC + " TEXT" + ")";
        db.execSQL(CREATE_RECEIPT_TABLE);
    }//onCreate

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RECEIPT);
        // Create tables again
        onCreate(db);
    }//onUpgrade

    // Adding new receipt
    public void addReceipt(Receipt rec) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_ID, rec.getID()); // Receipt ID
        values.put(KEY_AMOUNT, rec.getImporto()); // Receipt Amount
        values.put(KEY_DATE, rec.getDate()); // Receipt Date
        values.put(KEY_DESC, rec.getDesc()); // Receipt Description
        // Inserting Row
        db.insert(TABLE_RECEIPT ,null, values);
        db.close(); // Closing database connection
    }//addContact

    // Getting single receipt
    public Receipt getReceipt(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_RECEIPT, new String[] { KEY_ID,KEY_AMOUNT, KEY_DATE, KEY_DESC }, KEY_ID + "=?",new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        Receipt rec = new Receipt(Integer.parseInt(cursor.getString(0)),
                Double.parseDouble(cursor.getString(1)), cursor.getString(2),cursor.getString(3));
        return rec;
    }//getReceipt

    // Getting All Contacts
    public List<Receipt> getAllReceipts() {
        List<Receipt> rL = new ArrayList<Receipt>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_RECEIPT;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Receipt rec = new Receipt();
                rec.setId(Integer.parseInt(cursor.getString(0)));
                rec.setImport(Double.parseDouble(cursor.getString(1)));
                rec.setDate(cursor.getString(2));
                rec.setDesc(cursor.getString(3));
                // Adding contact to list
                rL.add(rec);
            } while (cursor.moveToNext());
        }

        // return contact list
        return rL;
    }//getAllReceipts

    // Getting contacts Count
    public int getReceiptsCount() {
            String countQuery = "SELECT  * FROM " + TABLE_RECEIPT;
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(countQuery, null);
            cursor.close();
            // return count
            return cursor.getCount();
    }//getReceiptsCount

    // Updating single receipts
    public int updateReceipts(Receipt rec) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, rec.getID());
        values.put(KEY_AMOUNT, rec.getImporto());
        values.put(KEY_DATE, rec.getDate());
        values.put(KEY_DESC, rec.getDesc());

        // updating row
        return db.update(TABLE_RECEIPT, values, KEY_ID + " = ?",new String[] { String.valueOf(rec.getID())});
    }//updateReceipts

    // Deleting single receipts
    public void deleteReceipts(Receipt rec) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_RECEIPT, KEY_ID + " = ?",
                new String[] { String.valueOf(rec.getID()) });
        db.close();
    }//deleteReceipts
}//DataBaseHandler
