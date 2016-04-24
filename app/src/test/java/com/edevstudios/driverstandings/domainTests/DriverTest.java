package com.edevstudios.driverstandings.domainTests;


import com.edevstudios.driverstandings.conf.factory.DriverFactory;
import com.edevstudios.driverstandings.domain.Driver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import java.util.HashMap;

/**
 * Created by Edmund.Simons on 2016/04/01.
 */
public class DriverTest
{
    Driver driver;
    Driver driverUpdate;
    HashMap<String, String> raceDriver;
    @Before
    public void setUp() throws Exception
    {
        raceDriver = new HashMap<String, String>();
        raceDriver.put("name","Nico");
        raceDriver.put("surname","Ros");
        raceDriver.put("country","Germany");
        raceDriver.put("team","Mercedes AMG");
        driver = DriverFactory.createDriver(raceDriver, 25, 0, 1);
    }

    @Test
    public void testDriver() throws Exception
    {
        Assert.assertEquals("Nico", driver.getName());
    }

    @Test
    public void testEdit() throws Exception
    {
        driverUpdate = new Driver.Builder(driver.getSurname()).copy(driver).surname("Rosburg").build();

        Assert.assertEquals("Ros", driver.getSurname());
        Assert.assertEquals("Rosburg", driverUpdate.getSurname());
    }

    @After
    public void tearDown() throws Exception
    {

    }
}
