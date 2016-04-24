package com.edevstudios.driverstandings.domainTests;

import com.edevstudios.driverstandings.conf.factory.CarFactory;
import com.edevstudios.driverstandings.domain.Car;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Edmund.Simons on 2016/04/01.
 */
public class CarTest
{
    Car formulaOneCar;
    Car actualFormulaOneCar;
    @Before
    public void setUp() throws Exception
    {
        formulaOneCar = CarFactory.createCar("Mercedes Benz", "Indie", 2015);
    }

    @Test
    public void testCreate() throws Exception
    {
        Assert.assertEquals("Mercedes Benz", formulaOneCar.getMake());
    }

    @Test
    public void testEdit() throws Exception
    {
        actualFormulaOneCar = new Car.Builder(formulaOneCar.getMake()).copy(formulaOneCar).model("Formula One").build();

        Assert.assertEquals("Mercedes Benz", formulaOneCar.getMake());
        Assert.assertEquals("Formula One", actualFormulaOneCar.getModel());
    }

    @After
    public void tearDown() throws Exception
    {

    }
}
