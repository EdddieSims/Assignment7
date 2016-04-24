package com.edevstudios.driverstandings.repository.domain.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.edevstudios.driverstandings.R;
import com.edevstudios.driverstandings.config.database.DBConstant;
import com.edevstudios.driverstandings.domain.Car;
import com.edevstudios.driverstandings.repository.domain.CarRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Edmund.Simons on 2016/04/21.
 */
public class CarRepositoryImpl extends SQLiteOpenHelper implements CarRepository
{
    private SQLiteDatabase db;
    public static final String TABLE_NAME       = "car";

    public static final String COLUMN_ID        = "id";
    public static final String COLUMN_MAKE      = "make";
    public static final String COLUMN_MODEL     = "model";
    public static final String COLUMN_YEAR      = "year";

    // This string creates the database when called
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_NAME    + "("
            + COLUMN_ID     + " INTEGER  PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_MAKE   + " TEXT NOT NULL , "
            + COLUMN_MODEL  + " TEXT NOT NULL , "
            + COLUMN_YEAR   + " INTEGER NOT NULL  );";

    public CarRepositoryImpl(Context context) {
        super(context, DBConstant.DATABASE_NAME, null, DBConstant.DATABASE_VERSION);
    }


    public void open() throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close()
    {
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

    //Finds individual record based on id
    @Override
    public Car findById(Long id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_MAKE,
                        COLUMN_MODEL,
                        COLUMN_YEAR},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);

        if (cursor.moveToFirst())
        {
            final Car car = new Car.Builder(cursor.getString(cursor.getColumnIndex(COLUMN_MAKE)))
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .model(cursor.getString(cursor.getColumnIndex(COLUMN_MODEL)))
                    .year(cursor.getInt(cursor.getColumnIndex(COLUMN_YEAR)))
                    .build();

            return car;
        }
        else
        {
            return null;
        }
    }

    //Saves newly inserted data to the database
    @Override
    public Car save(Car entity)
    {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_MAKE, entity.getMake());
        values.put(COLUMN_MODEL, entity.getModel());
        values.put(COLUMN_YEAR, entity.getYear());
        long id = db.insertOrThrow(TABLE_NAME, null, values);
        Car insertedCar = new Car.Builder(entity.getMake())
                .copy(entity)
                .id(new Long(id))
                .build();
        return insertedCar;
    }

    //Updates a record in the database
    @Override
    public Car update(Car entity)
    {
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_MAKE, entity.getMake());
        values.put(COLUMN_MODEL, entity.getModel());
        values.put(COLUMN_YEAR, entity.getYear());
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    //Deletes a record from the database
    @Override
    public Car delete(Car entity)
    {
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    //Selects all records in the database
    @Override
    public Set<Car> findAll()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Car> cars = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {
                final Car car = new Car.Builder(cursor.getString(cursor.getColumnIndex(COLUMN_MAKE)))
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .model(cursor.getString(cursor.getColumnIndex(COLUMN_MODEL)))
                        .year(cursor.getInt(cursor.getColumnIndex(COLUMN_YEAR)))
                        .build();
                cars.add(car);
            } while (cursor.moveToNext());
        }
        return cars;
    }

    //Deletes all records from the database
    @Override
    public int deleteAll()
    {
        open();
        int rowsDeleted = db.delete(TABLE_NAME,null,null);
        close();
        return rowsDeleted;
    }


}
