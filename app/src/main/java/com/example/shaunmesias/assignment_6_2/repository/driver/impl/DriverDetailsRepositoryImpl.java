package com.example.shaunmesias.assignment_6_2.repository.driver.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.shaunmesias.assignment_6_2.conf.databases.Database;
import com.example.shaunmesias.assignment_6_2.domain.driver.DriverDetails;
import com.example.shaunmesias.assignment_6_2.repository.Repository;
import com.example.shaunmesias.assignment_6_2.repository.driver.DriverDetailsRepository;
import com.example.shaunmesias.assignment_6_2.repository.person.impl.PersonContactRepositoryImpl;
import com.example.shaunmesias.assignment_6_2.repository.person.impl.PersonDetailsRepositoryImpl;
import com.example.shaunmesias.assignment_6_2.repository.person.impl.PersonRepositoryImpl;
import com.example.shaunmesias.assignment_6_2.repository.register.impl.RegisterRepositoryImpl;

import java.util.HashSet;
import java.util.Set;


/**
 * Created by Shaun Mesias on 2016/05/07.
 */
public class DriverDetailsRepositoryImpl extends SQLiteOpenHelper implements DriverDetailsRepository {

    public static final String TABLE_NAME = "DriverDetails";
    private SQLiteDatabase database;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_OWNER = "owner";
    public static final String COLUMN_CARNAME = "carName";

    public static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + " ("
            + COLUMN_ID + " TEXT PRIMARY KEY , "
            + COLUMN_OWNER + " TEXT NOT NULL , "
            + COLUMN_CARNAME + " TEXT NOT NULL)";

    public DriverDetailsRepositoryImpl(Context context) {
        super(context, Database.DATABASE_NAME, null, Database.DATABASE_VERSION);
    }

    @Override
    public DriverDetails findById(long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{COLUMN_ID, COLUMN_OWNER, COLUMN_CARNAME}
                , COLUMN_ID + " =?"
                , new String[]{String.valueOf(id)}
                , null, null, null, null);
        if(cursor.moveToFirst()){
            final DriverDetails driver = new DriverDetails.Builder()
                    .id(cursor.getString(cursor.getColumnIndex(COLUMN_ID)))
                    .ownerName(cursor.getString(cursor.getColumnIndex(COLUMN_OWNER)))
                    .carName(cursor.getString(cursor.getColumnIndex(COLUMN_CARNAME)))
                    .build();
            return driver;
        }
        else {
            return null;
        }
    }

    @Override
    public DriverDetails save(DriverDetails entity) {
        open();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_OWNER, entity.getOwnerName());
        values.put(COLUMN_CARNAME, entity.getCarName());

        long id = database.insertOrThrow(TABLE_NAME, null, values);

        return entity;
    }

    @Override
    public DriverDetails update(DriverDetails entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_OWNER, entity.getOwnerName());
        values.put(COLUMN_CARNAME, entity.getCarName());

        database.update(TABLE_NAME, values, COLUMN_ID + " =? ", new String[]{String.valueOf(entity.getId())});

        return entity;
    }

    @Override
    public DriverDetails delete(DriverDetails entity) {
        open();
        database.delete(TABLE_NAME, COLUMN_ID + " =?", new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<DriverDetails> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<DriverDetails> Person = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final DriverDetails driver = new DriverDetails.Builder()
                        .id(cursor.getString(cursor.getColumnIndex(COLUMN_ID)))
                        .ownerName(cursor.getString(cursor.getColumnIndex(COLUMN_OWNER)))
                        .carName(cursor.getString(cursor.getColumnIndex(COLUMN_CARNAME)))
                        .build();
                Person.add(driver);
            } while (cursor.moveToNext());
        }
        return Person;
    }

    @Override
    public Cursor selectAll()
    {
        String[] columns = {COLUMN_ID,COLUMN_OWNER, COLUMN_CARNAME};
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
        db.execSQL(PersonContactRepositoryImpl.DATABASE_CREATE);
        db.execSQL(PersonDetailsRepositoryImpl.DATABASE_CREATE);
        db.execSQL(DriverRepositoryImpl.DATABASE_CREATE);
        db.execSQL(DriverContactRepositoryImpl.DATABASE_CREATE);
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
        db.execSQL("DROP TABLE IF EXISTS" + PersonContactRepositoryImpl.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS" + DriverRepositoryImpl.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS" + DriverContactRepositoryImpl.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS" + RegisterRepositoryImpl.TABLE_NAME);
        onCreate(db);
    }
}
