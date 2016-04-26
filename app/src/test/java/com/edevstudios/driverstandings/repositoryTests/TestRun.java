package com.edevstudios.driverstandings.repositoryTests;

import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.content.ContentValues;
import android.test.AndroidTestCase;

import com.edevstudios.driverstandings.conf.factory.CarFactory;
import com.edevstudios.driverstandings.config.database.DBConstant;
import com.edevstudios.driverstandings.domain.Car;
import com.edevstudios.driverstandings.repository.domain.Impl.CarRepositoryImpl;

import org.junit.Test;

/**
 * Created by Edmund.Simons on 2016/04/25.
 */

public class TestRun extends AndroidTestCase
{
    private CarRepositoryImpl carImpl;
    private SQLiteDatabase db;

    Car formulaOneCar;
    private String make = "Ferrari";
    private String model = "Indie";
    private int year = 2015;

    public void testDropDB()
    {
        assertTrue(mContext.deleteDatabase(DBConstant.DATABASE_NAME));
    }

    public void testSetUpDB()
    {
        carImpl = new CarRepositoryImpl(mContext);
        db = carImpl.getWritableDatabase();
        assertTrue(db.isOpen());
        System.out.println("Successful");
        formulaOneCar = CarFactory.createCar(make, model, year);
    }

    @Test
    public void testCreate()
    {
        carImpl.save(formulaOneCar);
    }

    public void testRead()
    {
        Car getCar = carImpl.findById(1L);
        String brand = getCar.getMake();
        System.out.println(brand);
    }

    public void testClose()
    {
        db.close();
    }
}
