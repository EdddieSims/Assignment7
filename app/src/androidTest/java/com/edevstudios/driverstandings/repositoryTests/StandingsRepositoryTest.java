package com.edevstudios.driverstandings.repositoryTests;

import android.test.AndroidTestCase;

import com.edevstudios.driverstandings.conf.factory.StandingsFactory;
import com.edevstudios.driverstandings.domain.Standings;
import com.edevstudios.driverstandings.repository.domain.Impl.StandingsRepositoryImpl;
import com.edevstudios.driverstandings.repository.domain.StandingsRepository;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Set;

/**
 * Created by Edmund.Simons on 2016/04/24.
 */
public class StandingsRepositoryTest extends AndroidTestCase
{
    private static final String TAG = "CAR TEST";
    private Long id;

    private String make = "Ferrari";
    private String model = "Indie";
    private int year = 2015;

    @Test
    public void testStandingsCRUD() throws Exception{
        StandingsRepository repo = new StandingsRepositoryImpl(this.getContext());

        Standings standings = new Standings.Builder("Edmund")
                .surname("Simons")
                .team("Renault")
                .points(25)
                .behind(0)
                .numOfWins(1)
                .build();
        Standings insertedEntity = repo.save(standings);
        id = insertedEntity.getId();
        Assert.assertNotNull(TAG+" CREATE",insertedEntity);

        //READ ALL
        Set<Standings> cars = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL", cars.size()>0);

        //READ ENTITY
        Standings entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);

        //UPDATE ENTITY
        Standings updateEntity = new Standings.Builder(make)
                .copy(entity)
                .points(50)
                .numOfWins(2)
                .build();
        repo.update(updateEntity);
        Standings newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY", 2, newEntity.getNumOfWins());

        // DELETE ENTITY
        repo.delete(insertedEntity);
        Standings deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);
    }
}
