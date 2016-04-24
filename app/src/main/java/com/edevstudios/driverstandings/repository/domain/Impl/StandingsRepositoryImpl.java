package com.edevstudios.driverstandings.repository.domain.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.edevstudios.driverstandings.config.database.DBConstant;
import com.edevstudios.driverstandings.domain.Standings;
import com.edevstudios.driverstandings.repository.domain.StandingsRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Edmund.Simons on 2016/04/22.
 */
public class StandingsRepositoryImpl extends SQLiteOpenHelper implements StandingsRepository
{
    private SQLiteDatabase db;
    public static final String TABLE_NAME          = "standings";

    public static final String COLUMN_ID          = "id";
    public static final String COLUMN_NAME        = "name";
    public static final String COLUMN_SURNAME     = "surname";
    public static final String COLUMN_TEAM        = "team";
    public static final String COLUMN_POINTS      = "points";
    public static final String COLUMN_BEHIND      = "behind";
    public static final String COLUMN_NUM_OF_WINS = "wins";

    // This string creates the database when called
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME           + "("
            + COLUMN_ID            + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NAME          + " TEXT NOT NULL , "
            + COLUMN_SURNAME       + " TEXT NOT NULL , "
            + COLUMN_TEAM          + " TEXT NOT NULL , "
            + COLUMN_POINTS        + " INTEGER NOT NULL , "
            + COLUMN_BEHIND        + " INTEGER NOT NULL , "
            + COLUMN_NUM_OF_WINS   + " INTEGER NOT NULL  );";

    public StandingsRepositoryImpl(Context context) {
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
    public Standings findById(Long id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_NAME,
                        COLUMN_SURNAME,
                        COLUMN_TEAM,
                        COLUMN_POINTS,
                        COLUMN_BEHIND,
                        COLUMN_NUM_OF_WINS},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);

        if (cursor.moveToFirst())
        {
            final Standings driver = new Standings.Builder(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .surname(cursor.getString(cursor.getColumnIndex(COLUMN_SURNAME)))
                    .team(cursor.getString(cursor.getColumnIndex(COLUMN_TEAM)))
                    .points(cursor.getInt(cursor.getColumnIndex(COLUMN_POINTS)))
                    .behind(cursor.getInt(cursor.getColumnIndex(COLUMN_BEHIND)))
                    .numOfWins(cursor.getInt(cursor.getColumnIndex(COLUMN_NUM_OF_WINS)))
                    .build();

            return driver;
        }
        else
        {
            return null;
        }
    }

    @Override
    public Standings save(Standings entity)
    {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_NAME, entity.getName());
        values.put(COLUMN_SURNAME, entity.getSurname());
        values.put(COLUMN_TEAM, entity.getTeam());
        values.put(COLUMN_POINTS, entity.getPoints());
        values.put(COLUMN_BEHIND, entity.getBehind());
        values.put(COLUMN_NUM_OF_WINS, entity.getNumOfWins());
        long id = db.insertOrThrow(TABLE_NAME, null, values);
        Standings insertedStanding = new Standings.Builder(entity.getName())
                .copy(entity)
                .id(new Long(id))
                .build();
        return insertedStanding;
    }

    @Override
    public Standings update(Standings entity)
    {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_NAME, entity.getName());
        values.put(COLUMN_SURNAME, entity.getSurname());
        values.put(COLUMN_TEAM, entity.getTeam());
        values.put(COLUMN_POINTS, entity.getPoints());
        values.put(COLUMN_BEHIND, entity.getBehind());
        values.put(COLUMN_NUM_OF_WINS, entity.getNumOfWins());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public Standings delete(Standings entity) {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public Set<Standings> findAll()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Standings> standings = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final Standings Standing = new Standings.Builder(cursor.getString(cursor.getColumnIndex(COLUMN_NAME)))
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .surname(cursor.getString(cursor.getColumnIndex(COLUMN_SURNAME)))
                        .team(cursor.getString(cursor.getColumnIndex(COLUMN_TEAM)))
                        .points(cursor.getInt(cursor.getColumnIndex(COLUMN_POINTS)))
                        .behind(cursor.getInt(cursor.getColumnIndex(COLUMN_BEHIND)))
                        .numOfWins(cursor.getInt(cursor.getColumnIndex(COLUMN_NUM_OF_WINS)))
                        .build();
                standings.add(Standing);
            } while (cursor.moveToNext());
        }
        return standings;
    }

    @Override
    public int deleteAll() {
        open();
        int rowsDeleted = db.delete(TABLE_NAME,null,null);
        close();
        return rowsDeleted;
    }
}
