package com.example.shaunmesias.assignment_6_2.repository.person.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.shaunmesias.assignment_6_2.conf.databases.Database;
import com.example.shaunmesias.assignment_6_2.domain.person.PersonContact;
import com.example.shaunmesias.assignment_6_2.repository.driver.impl.DriverContactRepositoryImpl;
import com.example.shaunmesias.assignment_6_2.repository.driver.impl.DriverDetailsRepositoryImpl;
import com.example.shaunmesias.assignment_6_2.repository.driver.impl.DriverRepositoryImpl;
import com.example.shaunmesias.assignment_6_2.repository.person.PersonContactRepository;
import com.example.shaunmesias.assignment_6_2.repository.register.impl.RegisterRepositoryImpl;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Shaun Mesias on 2016/05/07.
 */
public class PersonContactRepositoryImpl extends SQLiteOpenHelper implements PersonContactRepository {
    public static final String TABLE_NAME = "PersonContact";
    private SQLiteDatabase database;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_CONTACT = "contact";

    public static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + " ("
            + COLUMN_ID + " TEXT PRIMARY KEY , "
            + COLUMN_CONTACT + " TEXT NOT NULL)";

    public PersonContactRepositoryImpl(Context context) {
        super(context, Database.DATABASE_NAME, null, Database.DATABASE_VERSION);
    }

    //@Override
    public PersonContact findById(long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{COLUMN_ID, COLUMN_CONTACT}
                , COLUMN_ID + " =?"
                , new String[]{String.valueOf(id)}
                , null, null, null, null);
        if(cursor.moveToFirst()){
            final PersonContact driver = new PersonContact.Builder()
                    .id(cursor.getString(cursor.getColumnIndex(COLUMN_ID)))
                    .contactValue(cursor.getString(cursor.getColumnIndex(COLUMN_CONTACT)))
                    .build();
            return driver;
        }
        else {
            return null;
        }
    }

    @Override
    public PersonContact update(PersonContact entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_CONTACT, entity.getContactValue());

        database.update(TABLE_NAME, values, COLUMN_ID + " =? ", new String[]{String.valueOf(entity.getId())});

        return entity;
    }

    @Override
    public PersonContact save(PersonContact entity) {
        open();
        int total = 0;
        Set<PersonContact> ids = findAll();
        ContentValues values;
        while (total <= ids.size()){
            total = total + 1;
        }
        if(total == 0){
            Log.i("PersonContact", "In If");
            values = new ContentValues();
            values.put(COLUMN_ID, "1");
            values.put(COLUMN_CONTACT, entity.getContactValue());
            long id = database.insertOrThrow(TABLE_NAME, null, values);
        }
        else
        {
            Log.i("PersonContact", "In else");
            values = new ContentValues();
            values.put(COLUMN_ID, String.valueOf(total));
            values.put(COLUMN_CONTACT, entity.getContactValue());
            long id = database.insertOrThrow(TABLE_NAME, null, values);
        }

        /*ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_CONTACT, entity.getContactValue());
        values.put(COLUMN_STATE, entity.getState());
        values.put(COLUMN_STATUS, entity.getStatus());*/



        return entity;
    }

    @Override
    public Set<PersonContact> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<PersonContact> Person = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final PersonContact driver = new PersonContact.Builder()
                        .id(cursor.getString(cursor.getColumnIndex(COLUMN_ID)))
                        .contactValue(cursor.getString(cursor.getColumnIndex(COLUMN_CONTACT)))
                        .build();
                Person.add(driver);
            } while (cursor.moveToNext());
        }
        return Person;
    }

    @Override
    public Cursor selectAll()
    {
        String[] columns = {COLUMN_ID,COLUMN_CONTACT};
        open();
        return database.query(TABLE_NAME, columns,null,null,null,null,null);
    }

    @Override
    public PersonContact delete(PersonContact entity) {
        open();
        database.delete(TABLE_NAME, COLUMN_ID + " =?", new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public int deleteAll() {
        open();
        int rowsDeleted = database.delete(TABLE_NAME,null,null);
        return rowsDeleted;
    }

    public void open(){
        database = this.getWritableDatabase();
    }

    public void close(){
        this.close();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(DATABASE_CREATE);
        db.execSQL(PersonRepositoryImpl.DATABASE_CREATE);
        db.execSQL(PersonDetailsRepositoryImpl.DATABASE_CREATE);
        db.execSQL(DriverContactRepositoryImpl.DATABASE_CREATE);
        db.execSQL(DriverRepositoryImpl.DATABASE_CREATE);
        db.execSQL(DriverDetailsRepositoryImpl.DATABASE_CREATE);
        db.execSQL(RegisterRepositoryImpl.DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(this.getClass().getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");

        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS" + PersonRepositoryImpl.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS" + PersonDetailsRepositoryImpl.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS" + RegisterRepositoryImpl.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS" + DriverRepositoryImpl.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS" + DriverContactRepositoryImpl.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS" + DriverDetailsRepositoryImpl.TABLE_NAME);
        onCreate(db);
    }
}
