package com.edevstudios.driverstandings.repository.domain.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.edevstudios.driverstandings.config.database.DBConstant;
import com.edevstudios.driverstandings.domain.Sponsor;
import com.edevstudios.driverstandings.repository.domain.SponsorRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Edmund.Simons on 2016/04/22.
 */
public class SponsorRepositoryImpl extends SQLiteOpenHelper implements SponsorRepository
{
    private SQLiteDatabase db;
    public static final String TABLE_NAME          = "sponsor";

    public static final String COLUMN_ID           = "id";
    public static final String COLUMN_NAME         = "name";
    public static final String COLUMN_LOGO_COLOUR  = "logo_colour";

    // This string creates the database when called
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME            + "("
            + COLUMN_ID             + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NAME           + " TEXT NOT NULL , "
            + COLUMN_LOGO_COLOUR    + " TEXT NOT NULL  );";

    public SponsorRepositoryImpl(Context context) {
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
    public Sponsor findById(Long id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_NAME,
                        COLUMN_LOGO_COLOUR},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);

        if (cursor.moveToFirst())
        {
            final Sponsor sponsor = new Sponsor.Builder(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .logoColour(cursor.getString(cursor.getColumnIndex(COLUMN_LOGO_COLOUR)))
                    .build();

            return sponsor;
        }
        else
        {
            return null;
        }
    }

    @Override
    public Sponsor save(Sponsor entity)
    {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_NAME, entity.getName());
        values.put(COLUMN_LOGO_COLOUR, entity.getLogoColour());
        long id = db.insertOrThrow(TABLE_NAME, null, values);
        Sponsor insertedSponsor = new Sponsor.Builder(entity.getName())
                .copy(entity)
                .id(new Long(id))
                .build();
        return insertedSponsor;
    }

    @Override
    public Sponsor update(Sponsor entity)
    {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_NAME, entity.getName());
        values.put(COLUMN_LOGO_COLOUR, entity.getLogoColour());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public Sponsor delete(Sponsor entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<Sponsor> findAll()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Sponsor> sponsors = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final Sponsor sponsor = new Sponsor.Builder(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .logoColour(cursor.getString(cursor.getColumnIndex(COLUMN_LOGO_COLOUR)))
                        .build();
                sponsors.add(sponsor);
            } while (cursor.moveToNext());
        }
        return sponsors;
    }

    @Override
    public int deleteAll() {
        open();
        int rowsDeleted = db.delete(TABLE_NAME,null,null);
        close();
        return rowsDeleted;
    }
}
