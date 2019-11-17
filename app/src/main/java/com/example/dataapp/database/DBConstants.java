package com.example.dataapp.database;

public class DBConstants {
    public static final String DATA_BASE_NAME = "dataapp_db";

    //Related to contact table
    public static final String TABLE_NAME = "Contacts";
    public static final String FIELD_CONTACT_COLUMN_ID = "id";
    public static final String FIELD_CONTACT_NAME = "name";
    public static final String FIELD_CONTACT_EMAIL = "email";
    public static final String FIELD_CONTACT_ADDRESS = "address";
    public static final String FIELD_CONTACT_MOBILE = "mobile";


    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + DBConstants.TABLE_NAME + "("
                    + DBConstants.FIELD_CONTACT_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + DBConstants.FIELD_CONTACT_NAME + " TEXT,"
                    + DBConstants.FIELD_CONTACT_EMAIL + " TEXT,"
                    + DBConstants.FIELD_CONTACT_ADDRESS + " TEXT,"
                    + DBConstants.FIELD_CONTACT_MOBILE + " TEXT )";
}
//    String CREATE_TABLE = "CREATE TABLE " + Constants.TABLE_NAME + " (" +
//            Constants.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
//            + Constants.KEY_Name1 + " VARCHAR, " + Constants.KEY_Name2 + " VARCHAR, "
//            + Constants.KEY_Name3 + " VARCHAR," + Constants.KEY_Name4 + " VARCHAR," +
//            Constants.KEY_Name5 + " VARCHAR," + Constants.KEY_Name6 + " VARCHAR)";
