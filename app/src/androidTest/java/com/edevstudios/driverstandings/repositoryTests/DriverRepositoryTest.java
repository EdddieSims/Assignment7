package com.edevstudios.driverstandings.repositoryTests;

import android.test.AndroidTestCase;

import com.edevstudios.driverstandings.conf.factory.DriverFactory;
import com.edevstudios.driverstandings.domain.Driver;
import com.edevstudios.driverstandings.repository.domain.DriverRepository;
import com.edevstudios.driverstandings.repository.domain.Impl.DriverRepositoryImpl;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Set;

/**
 * Created by Edmund.Simons on 2016/04/24.
 */
public class DriverRepositoryTest extends AndroidTestCase
{
    private static final String TAG = "DRIVER TEST";
    private Long id;
    HashMap<String, String> raceDriver;

    @Test
    public void testDriverCRUD() throws Exception
    {
        DriverRepository repo = new DriverRepositoryImpl(this.getContext());
        raceDriver = new HashMap<String, String>();
        raceDriver.put("name","Nico");
        raceDriver.put("surname","Ros");
        raceDriver.put("country","Germany");
        raceDriver.put("team","Mercedes AMG");

        Driver driver = new Driver.Builder(raceDriver.get("name"))
                .surname(raceDriver.get("surname"))
                .country(raceDriver.get("country"))
                .team(raceDriver.get("team"))
                .numOfWins(0)
                .points(0)
                .behind(0)
                .build();
        Driver insertedEntity = repo.save(driver);
        id = insertedEntity.getId();
        Assert.assertNotNull(TAG+" CREATE",insertedEntity);

        //READ ALL
        Set<Driver> drive = repo.findAll();
        org.junit.Assert.assertTrue(TAG+" READ ALL", drive.size()>0);

        //READ ENTITY
        Driver entity = repo.findById(id);
        org.junit.Assert.assertNotNull(TAG+" READ ENTITY",entity);

        //UPDATE ENTITY
        Driver updateEntity = new Driver.Builder(driver.getName())
                .copy(entity)
                .surname("Rosburg")
                .build();
        repo.update(updateEntity);
        Driver newEntity = repo.findById(id);
        org.junit.Assert.assertEquals(TAG+ " UPDATE ENTITY", "Rosburg", newEntity.getSurname());

        // DELETE ENTITY
        repo.delete(insertedEntity);
        Driver deletedEntity = repo.findById(id);
        org.junit.Assert.assertNull(TAG+" DELETE",deletedEntity);

    }
}
