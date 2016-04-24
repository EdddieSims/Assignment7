package com.edevstudios.driverstandings.repositoryTests;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

import com.edevstudios.driverstandings.conf.factory.CarFactory;
import com.edevstudios.driverstandings.domain.Car;
import com.edevstudios.driverstandings.repository.domain.Impl.CarRepositoryImpl;
import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by Edmund.Simons on 2016/04/23.
 */
public class CarRepositoryTest extends AndroidTestCase
{
    private CarRepositoryImpl carImpl;
    Car formulaOneCar;
    private String make = "Ferrari";
    private String model = "Indie";
    private int year = 2015;

    @Override
    public void setUp() throws Exception
    {
        carImpl = new CarRepositoryImpl(mContext);
        formulaOneCar = CarFactory.createCar(make, model, year);
    }

    @Test
    public void testObject() throws Exception
    {
        Assert.assertNotNull(carImpl);
    }

    @After
    public void tearDown() throws Exception
    {
        //carImpl.close();
    }
}
