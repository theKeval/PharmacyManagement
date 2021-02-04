package com.example.pharmacymanagement.handler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "pharmacy.sqlite";
    private static final String TABLE_CONTACTS = "contacts";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PH_NO = "phone_number";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        String query_createManufacturerTable =
                "CREATE TABLE Manufacturer(" +
                        "manuf_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "manuf_name VARCHAR(30) NOT NULL," +
                        "manuf_address VARCHAR(50)" +
                        ")";

        String query_createMrTable =
                "CREATE TABLE MedicalRepresentative(" +
                        "mr_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "mr_name VARCHAR(30) NOT NULL," +
                        "mr_contact VARCHAR(30)," +
                        "manuf_id INTEGER NOT NULL," +
                        "FOREIGN KEY (manuf_id) REFERENCES Manufacturer(manuf_id)" +
                        ")";

        String query_createMedicineTable =
                "CREATE TABLE Medicine(" +
                        "med_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "med_name VARCHAR(30) NOT NULL," +
                        "price NUMBER(5) NOT NULL," +
                        "manuf_date DATE NOT NULL," +
                        "expiry_date DATE NOT NULL," +
                        "mr_id INTEGER(6)," +
                        "manuf_id INTEGER(6) NOT NULL," +
                        "CONSTRAINT fk_mr FOREIGN KEY (mr_id) REFERENCES MedicalRepresentative(mr_id)," +
                        "CONSTRAINT fk_manuf FOREIGN KEY (manuf_id) REFERENCES Manufacturer(manuf_id)," +
                        "CONSTRAINT fk_med FOREIGN KEY (med_name) REFERENCES MedicineStock(med_name)" +
                        ")";

        String query_createMedicineContentTable =
                "CREATE TABLE MedicineIngredients(" +
                        "med_id INTEGER NOT NULL," +
                        "content VARCHAR(50)," +
                        "CONSTRAINT pk PRIMARY KEY (med_id, content)," +
                        "CONSTRAINT fk FOREIGN KEY (med_id) REFERENCES Medicine(med_id)" +
                        ")";

        String query_createMedicineStockTable =
                "CREATE TABLE MedicineStock(" +
                        "med_name VARCHAR(30) PRIMARY KEY NOT NULL," +
                        "stock_quantity INTEGER(5) NOT NULL," +
                        "desctiption VARCHAR(50)" +
                        ")";

        String query_createPatientTable =
                "CREATE TABLE Patient(" +
                        "patient_id INTEGER PRIMARY KEY NOT NULL," +
                        "patient_name VARCHAR(30) NOT NULL," +
                        "patient_contact VARCHAR(30)" +
                        ")";

        String query_createPatientRequestTable =
                "CREATE TABLE PatientRequest(" +
                        "patient_id INTEGER NOT NULL," +
                        "med_name VARCHAR(30) NOT NULL," +
                        "purchase_quantity INTEGER(5) NOT NULL," +
                        "CONSTRAINT pk PRIMARY KEY (patient_id, med_name)," +
                        "CONSTRAINT fk_med FOREIGN KEY (med_name) REFERENCES MedicineStock(med_name)," +
                        "CONSTRAINT fk_patient FOREIGN KEY (patient_id) REFERENCES Patient(patient_id)" +
                        ")";

        String query_createInvoiceTable =
                "CREATE TABLE Invoice(" +
                        "invoice_no INTEGER PRIMARY KEY NOT NULL," +
                        "amount NUMBER(5) NOT NULL," +
                        "pay_method VARCHAR(30)," +
                        "pay_date DATE," +
                        "patient_id INTEGER NOT NULL," +
                        "CONSTRAINT fk FOREIGN KEY (patient_id) REFERENCES Patient(patient_id)" +
                        ")";

        db.execSQL(query_createManufacturerTable);
        db.execSQL(query_createMrTable);
        db.execSQL(query_createMedicineTable);
        db.execSQL(query_createMedicineContentTable);
        db.execSQL(query_createMedicineStockTable);
        db.execSQL(query_createPatientTable);
        db.execSQL(query_createPatientRequestTable);
        db.execSQL(query_createInvoiceTable);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        db.execSQL("DROP TABLE IF EXISTS medicine");

        // Create tables again
        onCreate(db);
    }

    
    public void addManufacturer(String manuf_name, String manuf_address){
        String insertQuery =
                "INSERT INTO Manufacturer (manuf_name, manuf_address) " +
                        "VALUES ('"+ manuf_name +"','"+ manuf_address +"')";

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(insertQuery);
    }

    public void addMR(String mr_name, String mr_contact, String manuf_id){
        String insertQuery =
                "INSERT INTO MedicalRepresentative (mr_name, mr_contact, manuf_id) " +
                        "VALUES ('"+ mr_name +"','"+ mr_contact +"','"+ manuf_id +"')";

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(insertQuery);
    }

    public void addMedicine(String med_name, int price, String manuf_date, String expiry_date, int mr_id, int manuf_id){
        String insertQuery =
                "INSERT INTO Medicine (med_name, price, manuf_date, expiry_date, mr_id, manuf_id)" +
                        "VALUES ('"+med_name+"', "+price+",TO_DATE('"+manuf_date+"', 'DD/MM/YYYY'), TO_DATE('"+expiry_date+"', 'DD/MM/YYYY'), "+ mr_id+", "+manuf_id+")";

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(insertQuery);
    }

    public void addMedicineIngredients(int med_id, String content){
        String insertQuery =
                "INSERT INTO MedicineIngredients (med_id, content) " +
                        "VALUES ("+ med_id +",'"+ content +"')";

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(insertQuery);
    }

    public void addMedicineStock(String med_name, int stock_quantity, String description){
        String insertQuery =
                "INSERT INTO MedicineStock (med_name, stock_quantity, description)" +
                        "VALUES ('"+ med_name +"', "+stock_quantity+", '"+description+"')";

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(insertQuery);
    }

    public void addPatient(String patient_name, String patient_contact){
        String insertQuery =
                "INSERT INTO Patient (patient_name, patient_contact)" +
                        "VALUES ('"+patient_name+"', '"+patient_contact+"')";

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(insertQuery);
    }

    public void addPatientRequest(int patient_id, String med_name, int purchase_quantity){
        String insertQuery =
                "INSERT INTO PatientRequest (patient_id, med_name, purchase_quantity)" +
                        "VALUES ("+patient_id+", '"+med_name+"', "+purchase_quantity+")";

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(insertQuery);
    }

    public void addInvoice(int amount, String pay_method, String pay_date, int patient_id){
        String insertQuery =
                "INSERT INTO Invoice (amount, pay_method, pay_date, patient_id)" +
                        "VALUES ("+amount+", '"+pay_method+"', TO_DATE('"+pay_date+"', 'DD/MM/YYYY'), "+patient_id+")";

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(insertQuery);
    }


    // code to add the new contact
    void addContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName()); // Contact Name
        values.put(KEY_PH_NO, contact.getPhoneNumber()); // Contact Phone

        // Inserting Row
        db.insert(TABLE_CONTACTS, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }

    // code to get the single contact
    Contact getContact(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONTACTS, new String[]{KEY_ID,
                        KEY_NAME, KEY_PH_NO}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Contact contact = new Contact(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        // return contact
        return contact;
    }

    // code to get all contacts in a list view
    public List<Contact> getAllContacts() {
        List<Contact> contactList = new ArrayList<Contact>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                contact.setID(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setPhoneNumber(cursor.getString(2));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }

    // code to update the single contact
    public int updateContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_PH_NO, contact.getPhoneNumber());

        // updating row
        return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
                new String[]{String.valueOf(contact.getID())});
    }

    // Deleting single contact
    public void deleteContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
                new String[]{String.valueOf(contact.getID())});
        db.close();
    }

    // Getting contacts Count
    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

}