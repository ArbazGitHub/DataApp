package com.example.dataapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.dataapp.models.contactList.ContactListItem;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = DBConstants.DATA_BASE_NAME;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        // create notes table
        db.execSQL(DBConstants.CREATE_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + DBConstants.TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    public long insertContact(ContactListItem contactListItem) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` will be inserted automatically.
        // no need to add them
        values.put(DBConstants.FIELD_CONTACT_NAME, contactListItem.getStrName());
        values.put(DBConstants.FIELD_CONTACT_EMAIL, contactListItem.getStrEmail());
        values.put(DBConstants.FIELD_CONTACT_ADDRESS, contactListItem.getStrAddress());
        values.put(DBConstants.FIELD_CONTACT_MOBILE, contactListItem.getStrNumber());

        // insert row
        long id = db.insert(DBConstants.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }

    //get single contact data
    public ContactListItem getContact(long id) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(DBConstants.TABLE_NAME,
                new String[]{DBConstants.FIELD_CONTACT_COLUMN_ID, DBConstants.FIELD_CONTACT_NAME, DBConstants.FIELD_CONTACT_EMAIL, DBConstants.FIELD_CONTACT_ADDRESS, DBConstants.FIELD_CONTACT_MOBILE},
                DBConstants.FIELD_CONTACT_COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare contact object
        ContactListItem contactListItem = new ContactListItem(
                cursor.getInt(cursor.getColumnIndex(DBConstants.FIELD_CONTACT_COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(DBConstants.FIELD_CONTACT_NAME)),
                cursor.getString(cursor.getColumnIndex(DBConstants.FIELD_CONTACT_EMAIL)),
                cursor.getString(cursor.getColumnIndex(DBConstants.FIELD_CONTACT_ADDRESS)),
                cursor.getString(cursor.getColumnIndex(DBConstants.FIELD_CONTACT_MOBILE)));

        // close the db connection
        cursor.close();
        return contactListItem;
    }

    //get Contacts
    public ArrayList<ContactListItem> getContacts() {
        ArrayList<ContactListItem> contactListItemArrayList= new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + DBConstants.TABLE_NAME + " ORDER BY " +
                DBConstants.FIELD_CONTACT_COLUMN_ID + " ASC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                ContactListItem contactListItem = new ContactListItem();
                contactListItem.setId(cursor.getInt(cursor.getColumnIndex(DBConstants.FIELD_CONTACT_COLUMN_ID)));
                contactListItem.setStrName(cursor.getString(cursor.getColumnIndex(DBConstants.FIELD_CONTACT_NAME)));
                contactListItem.setStrEmail(cursor.getString(cursor.getColumnIndex(DBConstants.FIELD_CONTACT_EMAIL)));
                contactListItem.setStrAddress(cursor.getString(cursor.getColumnIndex(DBConstants.FIELD_CONTACT_ADDRESS)));
                contactListItem.setStrNumber(cursor.getString(cursor.getColumnIndex(DBConstants.FIELD_CONTACT_MOBILE)));

                contactListItemArrayList.add(contactListItem);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();

        // return notes list
        return contactListItemArrayList;
    }


    //update contact
    public int updateContact(ContactListItem contactListItem) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DBConstants.FIELD_CONTACT_NAME, contactListItem.getStrName());
        values.put(DBConstants.FIELD_CONTACT_EMAIL, contactListItem.getStrEmail());
        values.put(DBConstants.FIELD_CONTACT_ADDRESS, contactListItem.getStrAddress());
        values.put(DBConstants.FIELD_CONTACT_MOBILE, contactListItem.getStrNumber());


        // updating row
        return db.update(DBConstants.TABLE_NAME, values, DBConstants.FIELD_CONTACT_COLUMN_ID+ " = ?",
                new String[]{String.valueOf(contactListItem.getId())});
    }


    //delete contact
    public void deleteContact(ContactListItem contactListItem) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DBConstants.TABLE_NAME, DBConstants.FIELD_CONTACT_COLUMN_ID + " = ?",
                new String[]{String.valueOf(contactListItem.getId())});
        db.close();
    }

    //get total record count
    public int getContactCount() {
        String countQuery = "SELECT  * FROM " + DBConstants.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();


        // return count
        return count;
    }
}