package com.example.shaunmesias.assignment_6_2.repository.person.impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.shaunmesias.assignment_6_2.conf.databases.Database;
import com.example.shaunmesias.assignment_6_2.domain.driver.DriverContact;
import com.example.shaunmesias.assignment_6_2.domain.person.Person;
import com.example.shaunmesias.assignment_6_2.domain.person.PersonContact;
import com.example.shaunmesias.assignment_6_2.repository.driver.impl.DriverContactRepositoryImpl;
import com.example.shaunmesias.assignment_6_2.repository.driver.impl.DriverDetailsRepositoryImpl;
import com.example.shaunmesias.assignment_6_2.repository.driver.impl.DriverRepositoryImpl;
import com.example.shaunmesias.assignment_6_2.repository.person.PersonRepository;
import com.example.shaunmesias.assignment_6_2.repository.register.impl.RegisterRepositoryImpl;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Shaun Mesias on 2016/05/07.
 */
public class PersonRepositoryImpl extends SQLiteOpenHelper implements PersonRepository {
    public static final String TABLE_NAME = "Person";
    private SQLiteDatabase database;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_LOCATION = "location";
    public static final String COLUMN_SERVERID = "serverId";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_CONTACT = "contact";

    public static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME + " ("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT , "
            + COLUMN_NAME + " TEXT NOT NULL , "
            + COLUMN_LOCATION + " TEXT NOT NULL , "
            + COLUMN_SERVERID + " TEXT NOT NULL , "
            + COLUMN_EMAIL + " TEXT NOT NULL , "
            + COLUMN_CONTACT + " TEXT)";

    public PersonRepositoryImpl(Context context) {
        super(context, Database.DATABASE_NAME, null, Database.DATABASE_VERSION);
    }

    @Override
    public Person findById(long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{COLUMN_ID, COLUMN_NAME, COLUMN_LOCATION, COLUMN_SERVERID, COLUMN_EMAIL,COLUMN_CONTACT}
                , COLUMN_ID + " =?"
                , new String[]{String.valueOf(id)}
                , null, null, null, null);
        if(cursor.moveToFirst()){
            final PersonContact personContact = new PersonContact.Builder()
                    .contactValue(cursor.getString(cursor.getColumnIndex(COLUMN_CONTACT)))
                    .build();

            final Person person = new Person.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                    .location(cursor.getString(cursor.getColumnIndex(COLUMN_LOCATION)))
                    .serverId(cursor.getString(cursor.getColumnIndex(COLUMN_SERVERID)))
                    .email(cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)))
                    .getPersonContact(personContact)
                    .build();
            return person;
        }
        else {
            return null;
        }
    }

    @Override
    public Person save(Person entity) {
        open();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, entity.getName());
        values.put(COLUMN_LOCATION, entity.getLocation());
        values.put(COLUMN_SERVERID, entity.getServerId());
        values.put(COLUMN_EMAIL, entity.getEmail());
        values.put(COLUMN_CONTACT, entity.getPersonContact().getContactValue());

        long id = database.insertOrThrow(TABLE_NAME, null, values);
        Person insertedEntity = new Person.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();
        return insertedEntity;
    }

    @Override
    public Person update(Person entity) {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_NAME, entity.getName());
        values.put(COLUMN_LOCATION, entity.getLocation());
        values.put(COLUMN_SERVERID, entity.getServerId());
        values.put(COLUMN_EMAIL, entity.getEmail());
        values.put(COLUMN_CONTACT, entity.getPersonContact().getContactValue());

        database.update(TABLE_NAME, values, COLUMN_ID + " =? ", new String[]{String.valueOf(entity.getId())});

        return entity;
    }

    @Override
    public Person delete(Person entity) {
        open();
        database.delete(TABLE_NAME, COLUMN_ID + " =?", new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<Person> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Person> Person = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final PersonContact personContact = new PersonContact.Builder()
                        .contactValue(cursor.getString(cursor.getColumnIndex(COLUMN_CONTACT)))
                        .build();
                final Person person = new Person.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .name(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                        .location(cursor.getString(cursor.getColumnIndex(COLUMN_LOCATION)))
                        .serverId(cursor.getString(cursor.getColumnIndex(COLUMN_SERVERID)))
                        .email(cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)))
                        .getPersonContact(personContact)
                        .build();
                Person.add(person);
            } while (cursor.moveToNext());
        }
        return Person;
    }

    @Override
    public Cursor selectAll()
    {
        String[] columns = {COLUMN_ID,COLUMN_SERVERID, COLUMN_NAME, COLUMN_LOCATION, COLUMN_EMAIL,COLUMN_CONTACT};
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
        db.execSQL(PersonContactRepositoryImpl.DATABASE_CREATE);
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
        db.execSQL("DROP TABLE IF EXISTS" + RegisterRepositoryImpl.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS" + PersonDetailsRepositoryImpl.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS" + PersonContactRepositoryImpl.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS" + DriverRepositoryImpl.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS" + DriverContactRepositoryImpl.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS" + DriverDetailsRepositoryImpl.TABLE_NAME);
        onCreate(db);
    }
}
