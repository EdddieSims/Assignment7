package com.edevstudios.driverstandings.repository.domain.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.edevstudios.driverstandings.config.database.DBConstant;
import com.edevstudios.driverstandings.domain.Race;
import com.edevstudios.driverstandings.repository.domain.RaceRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Edmund.Simons on 2016/06/04.
 */
public class RaceRepositoryImpl extends SQLiteOpenHelper implements RaceRepository
{
    private SQLiteDatabase db;
    public static final String TABLE_NAME       = "season_races";

    public static final String COLUMN_ID        = "id";
    public static final String COLUMN_TRACK     = "track";
    public static final String COLUMN_LAPS      = "laps";
    public static final String COLUMN_WINNER_ID = "winner_id";

    // This string creates the database when called
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME        + "("
            + COLUMN_ID         + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_TRACK      + " TEXT NOT NULL , "
            + COLUMN_LAPS       + " INTEGER NOT NULL , "
            + COLUMN_WINNER_ID  + " INTEGER NOT NULL );";

    public RaceRepositoryImpl(Context context)
    {
        super(context, DBConstant.DATABASE_NAME, null, DBConstant.DATABASE_VERSION);
    }


    public void open() throws SQLException
    {
        db = this.getWritableDatabase();
    }

    public void close()
    {
        this.close();
    }

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
    public Race findById(Long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_TRACK,
                        COLUMN_LAPS,
                        COLUMN_WINNER_ID},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);

        if (cursor.moveToFirst())
        {
            final Race race = new Race.Builder(cursor.getString(cursor.getColumnIndex(COLUMN_TRACK)))
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .laps(cursor.getInt(cursor.getColumnIndex(COLUMN_LAPS)))
                    .winnerId(cursor.getLong(cursor.getColumnIndex(COLUMN_WINNER_ID)))
                    .build();

            return race;
        }
        else
        {
            return null;
        }
    }

    @Override
    public Race save(Race entity)
    {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_TRACK, entity.getTrackName());
        values.put(COLUMN_LAPS, entity.getLaps());
        values.put(COLUMN_WINNER_ID, entity.getWinnerId());
        long id = db.insertOrThrow(TABLE_NAME, null, values);
        Race insertedRace = new Race.Builder(entity.getTrackName())
                .copy(entity)
                .id(new Long(id))
                .build();
        return insertedRace;
    }

    @Override
    public Race update(Race entity)
    {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_TRACK, entity.getTrackName());
        values.put(COLUMN_LAPS, entity.getLaps());
        values.put(COLUMN_WINNER_ID, entity.getWinnerId());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public Race delete(Race entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<Race> findAll() {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Race> races = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final Race race = new Race.Builder(cursor.getString(cursor.getColumnIndex(COLUMN_TRACK)))
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .laps(cursor.getInt(cursor.getColumnIndex(COLUMN_LAPS)))
                        .winnerId(cursor.getLong(cursor.getColumnIndex(COLUMN_WINNER_ID)))
                        .build();
                races.add(race);
            } while (cursor.moveToNext());
        }
        return races;
    }

    @Override
    public int deleteAll()
    {
        open();
        int rowsDeleted = db.delete(TABLE_NAME,null,null);
        close();
        return rowsDeleted;
    }
}
