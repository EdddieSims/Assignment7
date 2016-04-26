package com.edevstudios.driverstandings.repositoryTests;

import android.test.AndroidTestCase;

import com.edevstudios.driverstandings.conf.factory.DriverFactory;
import com.edevstudios.driverstandings.domain.Driver;
import com.edevstudios.driverstandings.repository.domain.Impl.DriverRepositoryImpl;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

/**
 * Created by Edmund.Simons on 2016/04/24.
 */
public class DriverRepositoryTest extends AndroidTestCase
{
    private DriverRepositoryImpl driverImpl;
    Driver driver;
    Driver updateDriver;
    Driver returnDriver;
    HashMap<String, String> raceDriver;

    @Before
    public void setUp() throws Exception
    {
        raceDriver = new HashMap<String, String>();
        raceDriver.put("name","Nico");
        raceDriver.put("surname","Ros");
        raceDriver.put("country","Germany");
        raceDriver.put("team","Mercedes AMG");

        driverImpl = new DriverRepositoryImpl(mContext);
        driver = DriverFactory.createDriver(raceDriver, 25, 0, 1);
    }

    @Test
    public void testCreate() throws Exception
    {
        driverImpl.save(driver);
        //Assert.assertNotNull(carImpl);
    }

    @Test
    public void testRead() throws Exception
    {
        returnDriver = driverImpl.findById(1L);
        String name = returnDriver.getName();
        System.out.println(name);
    }

    @Test
    public void testUpdate() throws Exception
    {
        updateDriver = new Driver.Builder(driver.getName())
                .surname("Rosburg")
                .country(raceDriver.get("country"))
                .team(raceDriver.get("team"))
                .points(25)
                .behind(0)
                .numOfWins(1)
                .build();
        driverImpl.update(updateDriver);
    }

    @Test
    public void testDelete() throws Exception
    {
        driverImpl.delete(driver);
    }

    @After
    public void tearDown() throws Exception
    {
        //carImpl.close();
    }
}
