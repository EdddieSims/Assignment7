package com.edevstudios.driverstandings.repository.domain.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.edevstudios.driverstandings.config.database.DBConstant;
import com.edevstudios.driverstandings.domain.Leaderboard;
import com.edevstudios.driverstandings.repository.domain.LeaderboardRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Edmund.Simons on 2016/04/21.
 */
public class LeaderboardRepositoryImpl extends SQLiteOpenHelper implements LeaderboardRepository
{
    private SQLiteDatabase db;
    public static final String TABLE_NAME               = "leaderboard";

    public static final String COLUMN_ID                = "id";
    public static final String COLUMN_NAME              = "name";
    public static final String COLUMN_SURNAME           = "surname";
    public static final String COLUMN_FASTEST_LAP       = "fastestLap";
    public static final String COLUMN_CURRENT_LAP_TIME  = "currentLapTime";
    public static final String COLUMN_TOTAL_RACE_TIME   = "totalRaceTime";
    public static final String COLUMN_TOTAL_LAPS        = "totalLaps";
    public static final String COLUMN_CURRENT_LAP       = "currentLap";
    public static final String COLUMN_LAPS_REMAINING    = "lapsRemaining";

    // This string creates the database when called
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME                + "("
            + COLUMN_ID                 + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NAME               + " TEXT NOT NULL , "
            + COLUMN_SURNAME            + " TEXT NOT NULL , "
            + COLUMN_FASTEST_LAP        + " DOUBLE NOT NULL , "
            + COLUMN_CURRENT_LAP_TIME   + " DOUBLE NOT NULL , "
            + COLUMN_TOTAL_RACE_TIME    + " DOUBLE NOT NULL , "
            + COLUMN_TOTAL_LAPS         + " INTEGER NOT NULL , "
            + COLUMN_CURRENT_LAP        + " INTEGER NOT NULL , "
            + COLUMN_LAPS_REMAINING     + " INTEGER NOT NULL  );";

    public LeaderboardRepositoryImpl(Context context)
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
    public Leaderboard findById(Long id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]
                        {
                            COLUMN_ID,
                            COLUMN_NAME,
                            COLUMN_SURNAME,
                            COLUMN_FASTEST_LAP,
                            COLUMN_CURRENT_LAP_TIME,
                            COLUMN_TOTAL_RACE_TIME,
                            COLUMN_TOTAL_LAPS,
                            COLUMN_CURRENT_LAP,
                            COLUMN_LAPS_REMAINING
                        },
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);

        if (cursor.moveToFirst())
        {
            final Leaderboard leaderboard = new Leaderboard.Builder(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .surname(cursor.getString(cursor.getColumnIndex(COLUMN_SURNAME)))
                    .fastestLapTime(cursor.getDouble(cursor.getColumnIndex(COLUMN_FASTEST_LAP)))
                    .currLapTime(cursor.getDouble(cursor.getColumnIndex(COLUMN_CURRENT_LAP_TIME)))
                    .totalRaceTime(cursor.getDouble(cursor.getColumnIndex(COLUMN_TOTAL_RACE_TIME)))
                    .totalLaps(cursor.getInt(cursor.getColumnIndex(COLUMN_TOTAL_LAPS)))
                    .currLap(cursor.getInt(cursor.getColumnIndex(COLUMN_CURRENT_LAP)))
                    .lapsRemaining(cursor.getInt(cursor.getColumnIndex(COLUMN_LAPS_REMAINING)))
                    .build();

            return leaderboard;
        }
        else
        {
            return null;
        }
    }

    @Override
    public Leaderboard save(Leaderboard entity)
    {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_NAME, entity.getName());
        values.put(COLUMN_SURNAME, entity.getSurname());
        values.put(COLUMN_FASTEST_LAP, entity.getFastestLapTime());
        values.put(COLUMN_CURRENT_LAP_TIME, entity.getCurrLapTime());
        values.put(COLUMN_TOTAL_RACE_TIME, entity.getTotalRaceTime());
        values.put(COLUMN_TOTAL_LAPS, entity.getTotalLaps());
        values.put(COLUMN_CURRENT_LAP, entity.getCurrLap());
        values.put(COLUMN_LAPS_REMAINING, entity.getLapsRemaining());
        long id = db.insertOrThrow(TABLE_NAME, null, values);
        Leaderboard insertedLeaderboard = new Leaderboard.Builder(entity.getName())
                .copy(entity)
                .id(new Long(id))
                .build();
        return insertedLeaderboard;
    }

    @Override
    public Leaderboard update(Leaderboard entity)
    {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_NAME, entity.getName());
        values.put(COLUMN_SURNAME, entity.getSurname());
        values.put(COLUMN_FASTEST_LAP, entity.getFastestLapTime());
        values.put(COLUMN_CURRENT_LAP_TIME, entity.getCurrLapTime());
        values.put(COLUMN_TOTAL_RACE_TIME, entity.getTotalRaceTime());
        values.put(COLUMN_TOTAL_LAPS, entity.getTotalLaps());
        values.put(COLUMN_CURRENT_LAP, entity.getCurrLap());
        values.put(COLUMN_LAPS_REMAINING, entity.getLapsRemaining());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public Leaderboard delete(Leaderboard entity)
    {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<Leaderboard> findAll()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Leaderboard> leaderboards = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final Leaderboard leaderboard = new Leaderboard.Builder(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .surname(cursor.getString(cursor.getColumnIndex(COLUMN_SURNAME)))
                        .fastestLapTime(cursor.getDouble(cursor.getColumnIndex(COLUMN_FASTEST_LAP)))
                        .currLapTime(cursor.getDouble(cursor.getColumnIndex(COLUMN_CURRENT_LAP_TIME)))
                        .totalRaceTime(cursor.getDouble(cursor.getColumnIndex(COLUMN_TOTAL_RACE_TIME)))
                        .totalLaps(cursor.getInt(cursor.getColumnIndex(COLUMN_TOTAL_LAPS)))
                        .currLap(cursor.getInt(cursor.getColumnIndex(COLUMN_CURRENT_LAP)))
                        .lapsRemaining(cursor.getInt(cursor.getColumnIndex(COLUMN_LAPS_REMAINING)))
                        .build();
                leaderboards.add(leaderboard);
            } while (cursor.moveToNext());
        }
        return leaderboards;
    }

    @Override
    public int deleteAll()
    {
        open();
        int rowsDeleted = db.delete(TABLE_NAME,null,null);
        close();
        return rowsDeleted;
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
}
