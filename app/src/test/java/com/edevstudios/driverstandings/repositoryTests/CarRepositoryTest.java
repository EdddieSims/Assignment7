package com.edevstudios.driverstandings.repositoryTests;

import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

import com.edevstudios.driverstandings.conf.factory.CarFactory;
import com.edevstudios.driverstandings.domain.Car;
import com.edevstudios.driverstandings.repository.domain.Impl.CarRepositoryImpl;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Edmund.Simons on 2016/04/23.
 */
public class CarRepositoryTest extends AndroidTestCase
{
    private CarRepositoryImpl carImpl;
    Car formulaOneCar;
    Car updateFormulaOneCar;
    Car returnFormulaOneCar;
    private SQLiteDatabase db;

    private String make = "Ferrari";
    private String model = "Indie";
    private int year = 2015;

    @Before
    public void setUp() throws Exception
    {
        carImpl = new CarRepositoryImpl(mContext);
        formulaOneCar = CarFactory.createCar(make, model, year);

        db = carImpl.getWritableDatabase();
        assertTrue(db.isOpen());
    }

    @Test
    public void testCreate() throws Exception
    {
        carImpl.save(formulaOneCar);
        Assert.assertNotNull(carImpl);
    }

    @Test
    public void testRead() throws Exception
    {
        returnFormulaOneCar = carImpl.findById(1L);
        String name = returnFormulaOneCar.getMake();
        System.out.println(name);
    }

    @Test
    public void testUpdate() throws Exception
    {
        updateFormulaOneCar = new Car.Builder(formulaOneCar.getMake()).model(model).year(2016).build();
        carImpl.update(updateFormulaOneCar);
    }

    @Test
    public void testDelete() throws Exception
    {
        carImpl.delete(formulaOneCar);
    }

    @After
    public void tearDown() throws Exception
    {
        //carImpl.close();
    }
}
