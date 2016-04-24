package com.edevstudios.driverstandings.repository.domain.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.edevstudios.driverstandings.config.database.DBConstant;
import com.edevstudios.driverstandings.domain.Engine;
import com.edevstudios.driverstandings.repository.domain.EngineRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Edmund.Simons on 2016/04/22.
 */
public class EngineRepositoryImpl extends SQLiteOpenHelper implements EngineRepository
{
    private SQLiteDatabase db;
    public static final String TABLE_NAME          = "engine";

    public static final String COLUMN_ID           = "id";
    public static final String COLUMN_BRAND        = "brad";
    public static final String COLUMN_MODEL        = "model";
    public static final String COLUMN_PISTONS      = "pistons";
    public static final String COLUMN_POWER_OUTPUT = "output";

    // This string creates the database when called
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME            + "("
            + COLUMN_ID             + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_BRAND          + " TEXT NOT NULL , "
            + COLUMN_MODEL          + " TEXT NOT NULL , "
            + COLUMN_PISTONS        + " TEXT NOT NULL , "
            + COLUMN_POWER_OUTPUT   + " DOUBLE NOT NULL  );";

    public EngineRepositoryImpl(Context context) {
        super(context, DBConstant.DATABASE_NAME, null, DBConstant.DATABASE_VERSION);
    }

    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close() {
        this.close();
    }

    //Overriding the onCreate method to call the database create statement
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        Log.w(this.getClass().getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    @Override
    public Engine findById(Long id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_BRAND,
                        COLUMN_MODEL,
                        COLUMN_PISTONS,
                        COLUMN_POWER_OUTPUT},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);

        if (cursor.moveToFirst())
        {
            final Engine engine = new Engine.Builder(cursor.getString(cursor.getColumnIndex(COLUMN_BRAND)))
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .model(cursor.getString(cursor.getColumnIndex(COLUMN_MODEL)))
                    .numOfPistons(cursor.getInt(cursor.getColumnIndex(COLUMN_PISTONS)))
                    .powerOutput(cursor.getDouble(cursor.getColumnIndex(COLUMN_POWER_OUTPUT)))
                    .build();

            return engine;
        }
        else
        {
            return null;
        }
    }

    @Override
    public Engine save(Engine entity)
    {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_BRAND, entity.getBrand());
        values.put(COLUMN_MODEL, entity.getModel());
        values.put(COLUMN_PISTONS, entity.getNumOfPistons());
        values.put(COLUMN_POWER_OUTPUT, entity.getPowerOutput());
        long id = db.insertOrThrow(TABLE_NAME, null, values);
        Engine insertedEngine = new Engine.Builder(entity.getBrand())
                .copy(entity)
                .id(new Long(id))
                .build();
        return insertedEngine;
    }

    @Override
    public Engine update(Engine entity)
    {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_BRAND, entity.getBrand());
        values.put(COLUMN_MODEL, entity.getModel());
        values.put(COLUMN_PISTONS, entity.getNumOfPistons());
        values.put(COLUMN_POWER_OUTPUT, entity.getPowerOutput());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public Engine delete(Engine entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<Engine> findAll()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Engine> engines = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final Engine engine = new Engine.Builder(cursor.getString(cursor.getColumnIndex(COLUMN_BRAND)))
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .model(cursor.getString(cursor.getColumnIndex(COLUMN_MODEL)))
                        .numOfPistons(cursor.getInt(cursor.getColumnIndex(COLUMN_PISTONS)))
                        .powerOutput(cursor.getDouble(cursor.getColumnIndex(COLUMN_POWER_OUTPUT)))
                        .build();
                engines.add(engine);
            } while (cursor.moveToNext());
        }
        return engines;
    }

    @Override
    public int deleteAll() {
        open();
        int rowsDeleted = db.delete(TABLE_NAME,null,null);
        close();
        return rowsDeleted;
    }
}
