package com.example.shaunmesias.assignment_6_2.repository.driver.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.shaunmesias.assignment_6_2.conf.databases.Database;
import com.example.shaunmesias.assignment_6_2.domain.driver.Driver;
import com.example.shaunmesias.assignment_6_2.domain.driver.DriverContact;
import com.example.shaunmesias.assignment_6_2.domain.driver.DriverDetails;
import com.example.shaunmesias.assignment_6_2.repository.driver.DriverRepository;
import com.example.shaunmesias.assignment_6_2.repository.person.impl.PersonContactRepositoryImpl;
import com.example.shaunmesias.assignment_6_2.repository.person.impl.PersonDetailsRepositoryImpl;
import com.example.shaunmesias.assignment_6_2.repository.person.impl.PersonRepositoryImpl;
import com.example.shaunmesias.assignment_6_2.repository.register.impl.RegisterRepositoryImpl;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Shaun Mesias on 2016/05/07.
 */
public class DriverRepositoryImpl extends SQLiteOpenHelper implements DriverRepository {

    public static final String TABLE_NAME = "Driver";
    private SQLiteDatabase database;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_AREA = "area";
    public static final String COLUMN_SERVERID = "serverId";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_CONTACT = "contact";
    public static final String COLUMN_CAR = "car";
    public static final String COLUMN_OWNER = "owner";

    public static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + " ("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , "
            + COLUMN_NAME + " TEXT NOT NULL , "
            + COLUMN_AREA + " TEXT NOT NULL , "
            + COLUMN_SERVERID + " TEXT , "
            + COLUMN_EMAIL + " TEXT , "
            + COLUMN_CONTACT + " TEXT , "
            + COLUMN_CAR + " TEXT , "
            + COLUMN_OWNER + " TEXT)";

    public DriverRepositoryImpl(Context context) {
        super(context, Database.DATABASE_NAME, null, Database.DATABASE_VERSION);
    }


    @Override
    public Driver findById(long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{COLUMN_ID, COLUMN_NAME, COLUMN_AREA, COLUMN_SERVERID, COLUMN_EMAIL, COLUMN_CONTACT, COLUMN_CAR, COLUMN_OWNER}
                , COLUMN_ID + " =?"
                , new String[]{String.valueOf(id)}
                , null, null, null, null);
        if(cursor.moveToFirst()){
            final DriverDetails driverDetails = new DriverDetails.Builder()
                    .carName(cursor.getString(cursor.getColumnIndex(COLUMN_CAR)))
                    .ownerName(cursor.getString(cursor.getColumnIndex(COLUMN_OWNER)))
                    .build();

            final DriverContact driverContact = new DriverContact.Builder()
                    .contactValue(cursor.getString(cursor.getColumnIndex(COLUMN_CONTACT)))
                    .build();

            final Driver driver = new Driver.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                    .area(cursor.getString(cursor.getColumnIndex(COLUMN_AREA)))
                    .serverId(cursor.getString(cursor.getColumnIndex(COLUMN_SERVERID)))
                    .email(cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)))
                    .getDriverDetails(driverDetails)
                    .getDriverContact(driverContact)
                    .build();
            return driver;
        }
        else {
            return null;
        }
    }

    @Override
    public Driver save(Driver entity) {
       open();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, entity.getName());
        values.put(COLUMN_AREA, entity.getArea());
        values.put(COLUMN_SERVERID, entity.getServerId());
        values.put(COLUMN_EMAIL, entity.getEmail());
        values.put(COLUMN_CONTACT, entity.getDriverContact().getContactValue());
        values.put(COLUMN_CAR, entity.getDriverDetails().getCarName());
        values.put(COLUMN_OWNER, entity.getDriverDetails().getOwnerName());

        long id = database.insertOrThrow(TABLE_NAME, null, values);
        Driver insertedEntity = new Driver.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public Driver update(Driver entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_NAME, entity.getName());
        values.put(COLUMN_AREA, entity.getArea());
        values.put(COLUMN_SERVERID, entity.getServerId());
        values.put(COLUMN_EMAIL, entity.getEmail());
        values.put(COLUMN_CONTACT, entity.getDriverContact().getContactValue());
        values.put(COLUMN_CAR, entity.getDriverDetails().getCarName());
        values.put(COLUMN_OWNER, entity.getDriverDetails().getOwnerName());

        database.update(TABLE_NAME, values, COLUMN_ID + " =? ", new String[]{String.valueOf(entity.getId())});

        return entity;
    }

    @Override
    public Driver delete(Driver entity) {
        open();
        database.delete(TABLE_NAME, COLUMN_ID + " =?", new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<Driver> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Driver> Person = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final DriverDetails driverDetails = new DriverDetails.Builder()
                        .carName(cursor.getString(cursor.getColumnIndex(COLUMN_CAR)))
                        .ownerName(cursor.getString(cursor.getColumnIndex(COLUMN_OWNER)))
                        .build();

                final DriverContact driverContact = new DriverContact.Builder()
                        .contactValue(cursor.getString(cursor.getColumnIndex(COLUMN_CONTACT)))
                        .build();

                final Driver driver = new Driver.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                        .area(cursor.getString(cursor.getColumnIndex(COLUMN_AREA)))
                        .serverId(cursor.getString(cursor.getColumnIndex(COLUMN_SERVERID)))
                        .email(cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)))
                        .getDriverDetails(driverDetails)
                        .getDriverContact(driverContact)
                        .build();
                Person.add(driver);
            } while (cursor.moveToNext());
        }
        return Person;
    }

    @Override
    public Cursor selectAll()
    {
        String[] columns = {COLUMN_ID,COLUMN_NAME, COLUMN_AREA, COLUMN_SERVERID, COLUMN_EMAIL, COLUMN_CONTACT,COLUMN_CAR,COLUMN_OWNER};
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
        db.execSQL(DriverContactRepositoryImpl.DATABASE_CREATE);
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
        db.execSQL("DROP TABLE IF EXISTS" + PersonContactRepositoryImpl.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS" + RegisterRepositoryImpl.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS" + DriverContactRepositoryImpl.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS" + DriverDetailsRepositoryImpl.TABLE_NAME);
        onCreate(db);
    }
}
