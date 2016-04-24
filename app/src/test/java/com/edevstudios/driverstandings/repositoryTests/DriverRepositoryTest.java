package com.edevstudios.driverstandings.repositoryTests;

import android.test.AndroidTestCase;

import com.edevstudios.driverstandings.conf.factory.DriverFactory;
import com.edevstudios.driverstandings.domain.Driver;
import com.edevstudios.driverstandings.repository.domain.Impl.DriverRepositoryImpl;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Test;

import java.util.HashMap;

/**
 * Created by Edmund.Simons on 2016/04/24.
 */
public class DriverRepositoryTest extends AndroidTestCase
{
    private DriverRepositoryImpl driverImpl;
    Driver driver;
    HashMap<String, String> raceDriver;
    @Override
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
    public void testObject() throws Exception
    {
        Assert.assertNotNull(driverImpl);
    }

    @After
    public void tearDown() throws Exception
    {
        //carImpl.close();
    }
}
