package com.edevstudios.driverstandings.repositoryTests;

import android.test.AndroidTestCase;

import com.edevstudios.driverstandings.conf.factory.TeamMateFactory;
import com.edevstudios.driverstandings.domain.TeamMate;
import com.edevstudios.driverstandings.repository.domain.Impl.TeamMateRepositoryImpl;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

/**
 * Created by Edmund.Simons on 2016/04/24.
 */
public class TeamMateRepositoryTest extends AndroidTestCase
{
    private TeamMateRepositoryImpl teamMateImpl;
    TeamMate teamMate;
    TeamMate returnTeamMAte;
    TeamMate updateTeamMAte;
    HashMap<String, String> raceDriver;

    @Override
    public void setUp() throws Exception
    {
        teamMateImpl = new TeamMateRepositoryImpl(mContext);

        raceDriver = new HashMap<String, String>();
        raceDriver.put("name","Lewis");
        raceDriver.put("surname","Hamillton");
        raceDriver.put("country","England");
        raceDriver.put("team","Mercedes AMG");
        teamMate = TeamMateFactory.createTeamMate(raceDriver, 18, 7, 0);
    }

    @Test
    public void testObject() throws Exception
    {
        Assert.assertNotNull(teamMateImpl);
    }

    @Test
    public void testCreate() throws Exception
    {
        teamMateImpl.save(teamMate);
        //Assert.assertNotNull(carImpl);
    }

    @Test
    public void testRead() throws Exception
    {
        returnTeamMAte = teamMateImpl.findById(1L);
        String name = returnTeamMAte.getName();
        System.out.println(name);
    }

    @Test
    public void testUpdate() throws Exception
    {
        updateTeamMAte = new TeamMate.Builder(teamMate.getName())
                .surname("Hamilton")
                .country(raceDriver.get(raceDriver.get("country")))
                .team(raceDriver.get(raceDriver.get("team")))
                .points(36)
                .behind(14)
                .numOfWins(0)
                .build();
        teamMateImpl.update(updateTeamMAte);
    }

    @Test
    public void testDelete() throws Exception
    {
        teamMateImpl.delete(teamMate);
    }
    @After
    public void tearDown() throws Exception
    {
        //carImpl.close();
    }
}
