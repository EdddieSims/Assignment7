package com.edevstudios.driverstandings.repositoryTests;

import android.test.AndroidTestCase;

import com.edevstudios.driverstandings.conf.factory.TeamMateFactory;
import com.edevstudios.driverstandings.domain.TeamMate;
import com.edevstudios.driverstandings.repository.domain.Impl.TeamMateRepositoryImpl;
import com.edevstudios.driverstandings.repository.domain.TeamMateRepository;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Set;

/**
 * Created by Edmund.Simons on 2016/04/24.
 */
public class TeamMateRepositoryTest extends AndroidTestCase
{
    private static final String TAG = "TEAM MATE TEST";
    private Long id;
    HashMap<String, String> raceDriver;

    @Test
    public void testTeamMateCRUD() throws Exception
    {
        TeamMateRepository repo = new TeamMateRepositoryImpl(this.getContext());
        raceDriver = new HashMap<String, String>();
        raceDriver.put("name","Nico");
        raceDriver.put("surname","Ros");
        raceDriver.put("country","Germany");
        raceDriver.put("team","Mercedes AMG");

        TeamMate driver = new TeamMate.Builder(raceDriver.get("name"))
                .surname(raceDriver.get("surname"))
                .country(raceDriver.get("country"))
                .team(raceDriver.get("team"))
                .numOfWins(0)
                .points(0)
                .behind(0)
                .build();
        TeamMate insertedEntity = repo.save(driver);
        id = insertedEntity.getId();
        Assert.assertNotNull(TAG+" CREATE",insertedEntity);

        //READ ALL
        Set<TeamMate> drive = repo.findAll();
        org.junit.Assert.assertTrue(TAG+" READ ALL", drive.size()>0);

        //READ ENTITY
        TeamMate entity = repo.findById(id);
        org.junit.Assert.assertNotNull(TAG+" READ ENTITY",entity);

        //UPDATE ENTITY
        TeamMate updateEntity = new TeamMate.Builder(driver.getName())
                .copy(entity)
                .surname("Rosburg")
                .build();
        repo.update(updateEntity);
        TeamMate newEntity = repo.findById(id);
        org.junit.Assert.assertEquals(TAG+ " UPDATE ENTITY", "Rosburg", newEntity.getSurname());

        // DELETE ENTITY
        repo.delete(insertedEntity);
        TeamMate deletedEntity = repo.findById(id);
        org.junit.Assert.assertNull(TAG+" DELETE",deletedEntity);

    }
}
