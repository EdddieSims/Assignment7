package com.edevstudios.driverstandings.repository.domain.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.edevstudios.driverstandings.config.database.DBConstant;
import com.edevstudios.driverstandings.domain.Track;
import com.edevstudios.driverstandings.repository.domain.TrackRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Edmund.Simons on 2016/04/22.
 */
public class TrackRepositoryImpl extends SQLiteOpenHelper implements TrackRepository
{
    private SQLiteDatabase db;
    public static final String TABLE_NAME          = "track";

    public static final String COLUMN_ID           = "id";
    public static final String COLUMN_COUNTRY      = "country";
    public static final String COLUMN_NAME         = "name";
    public static final String COLUMN_NUM_OF_TURNS = "numOfTurns";
    public static final String COLUMN_LENGTH       = "length";

    // This string creates the database when called
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME            + "("
            + COLUMN_ID             + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_COUNTRY        + " TEXT NOT NULL , "
            + COLUMN_NAME           + " TEXT NOT NULL , "
            + COLUMN_NUM_OF_TURNS   + " INTEGER NOT NULL , "
            + COLUMN_LENGTH         + " DOUBLE NOT NULL  );";

    public TrackRepositoryImpl(Context context) {
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
    public Track findById(Long id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_COUNTRY,
                        COLUMN_NAME,
                        COLUMN_NUM_OF_TURNS,
                        COLUMN_LENGTH},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);

        if (cursor.moveToFirst())
        {
            final Track track = new Track.Builder(cursor.getString(cursor.getColumnIndex(COLUMN_COUNTRY)))
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .trackName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                    .numOfTurns(cursor.getInt(cursor.getColumnIndex(COLUMN_NUM_OF_TURNS)))
                    .length(cursor.getDouble(cursor.getColumnIndex(COLUMN_LENGTH)))
                    .build();

            return track;
        }
        else
        {
            return null;
        }
    }

    @Override
    public Track save(Track entity)
    {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_COUNTRY, entity.getCountry());
        values.put(COLUMN_NAME, entity.getTrackName());
        values.put(COLUMN_NUM_OF_TURNS, entity.getNumOfTurns());
        values.put(COLUMN_LENGTH, entity.getLength());
        long id = db.insertOrThrow(TABLE_NAME, null, values);
        Track insertedTrack = new Track.Builder(entity.getCountry())
                .copy(entity)
                .id(new Long(id))
                .build();
        return insertedTrack;
    }

    @Override
    public Track update(Track entity)
    {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_COUNTRY, entity.getCountry());
        values.put(COLUMN_NAME, entity.getTrackName());
        values.put(COLUMN_NUM_OF_TURNS, entity.getNumOfTurns());
        values.put(COLUMN_LENGTH, entity.getLength());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public Track delete(Track entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<Track> findAll()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Track> tracks = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final Track track = new Track.Builder(cursor.getString(cursor.getColumnIndex(COLUMN_COUNTRY)))
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .trackName(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                        .numOfTurns(cursor.getInt(cursor.getColumnIndex(COLUMN_NUM_OF_TURNS)))
                        .length(cursor.getDouble(cursor.getColumnIndex(COLUMN_LENGTH)))
                        .build();
                tracks.add(track);
            } while (cursor.moveToNext());
        }
        return tracks;
    }

    @Override
    public int deleteAll() {
        open();
        int rowsDeleted = db.delete(TABLE_NAME,null,null);
        close();
        return rowsDeleted;
    }
}
