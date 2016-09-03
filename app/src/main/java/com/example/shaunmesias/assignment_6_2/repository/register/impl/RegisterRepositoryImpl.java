package com.example.shaunmesias.assignment_6_2.repository.register.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.shaunmesias.assignment_6_2.conf.databases.Database;
import com.example.shaunmesias.assignment_6_2.domain.registration.Register;
import com.example.shaunmesias.assignment_6_2.repository.driver.impl.DriverContactRepositoryImpl;
import com.example.shaunmesias.assignment_6_2.repository.driver.impl.DriverDetailsRepositoryImpl;
import com.example.shaunmesias.assignment_6_2.repository.driver.impl.DriverRepositoryImpl;
import com.example.shaunmesias.assignment_6_2.repository.person.impl.PersonContactRepositoryImpl;
import com.example.shaunmesias.assignment_6_2.repository.person.impl.PersonDetailsRepositoryImpl;
import com.example.shaunmesias.assignment_6_2.repository.person.impl.PersonRepositoryImpl;
import com.example.shaunmesias.assignment_6_2.repository.register.RegisterRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Shaun Mesias on 2016/05/07.
 */
public class RegisterRepositoryImpl extends SQLiteOpenHelper implements RegisterRepository {

    public static final String TABLE_NAME = "Register";
    private SQLiteDatabase database;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_CATEGORY = "category";

    public static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + " ("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , "
            + COLUMN_NAME + " TEXT NOT NULL , "
            + COLUMN_PASSWORD + " TEXT NOT NULL , "
            + COLUMN_CATEGORY + " TEXT NOT NULL )";

    public RegisterRepositoryImpl(Context context) {
        super(context, Database.DATABASE_NAME, null, Database.DATABASE_VERSION);
    }

    @Override
    public Register findById(long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{COLUMN_ID, COLUMN_NAME, COLUMN_PASSWORD, COLUMN_CATEGORY}
                , COLUMN_ID + " =?"
                , new String[]{String.valueOf(id)}
                , null, null, null, null);
        if(cursor.moveToFirst()){
            final Register person = new Register.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .username(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                    .password(cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD)))
                    .category(cursor.getString(cursor.getColumnIndex(COLUMN_CATEGORY)))
                    .build();
            return person;
        }
        else {
            return null;
        }
    }

    @Override
    public Register save(Register entity) {
        open();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, entity.getUsername());
        values.put(COLUMN_PASSWORD, entity.getPassword());
        values.put(COLUMN_CATEGORY, entity.getCategory());

        long id = database.insertOrThrow(TABLE_NAME, null, values);
        Register insertedEntity = new Register.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public Register update(Register entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_NAME, entity.getUsername());
        values.put(COLUMN_PASSWORD, entity.getPassword());
        values.put(COLUMN_CATEGORY, entity.getCategory());

        database.update(TABLE_NAME, values, COLUMN_ID + " =? ", new String[]{String.valueOf(entity.getId())});

        return entity;
    }

    @Override
    public Register delete(Register entity) {
        open();
        database.delete(TABLE_NAME, COLUMN_ID + " =?", new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<Register> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Register> Person = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final Register person = new Register.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .username(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                        .password(cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD)))
                        .category(cursor.getString(cursor.getColumnIndex(COLUMN_CATEGORY)))
                        .build();
                Person.add(person);
            } while (cursor.moveToNext());
        }
        return Person;
    }

    @Override
    public Cursor selectAll()
    {
        String[] columns = {COLUMN_ID,COLUMN_NAME, COLUMN_NAME, COLUMN_PASSWORD, COLUMN_CATEGORY};
        open();
        return database.query(TABLE_NAME, columns,null,null,null,null,null);
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
        db.execSQL(PersonContactRepositoryImpl.DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(this.getClass().getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");

        db.execSQL("DROP TABLE IF EXISTS" + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS" + PersonRepositoryImpl.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS" + PersonDetailsRepositoryImpl.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS" + PersonContactRepositoryImpl.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS" + DriverRepositoryImpl.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS" + DriverContactRepositoryImpl.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS" + DriverDetailsRepositoryImpl.TABLE_NAME);
        onCreate(db);
    }
}
