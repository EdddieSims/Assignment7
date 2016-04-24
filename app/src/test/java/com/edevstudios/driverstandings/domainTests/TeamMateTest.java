package com.edevstudios.driverstandings.domainTests;

import com.edevstudios.driverstandings.conf.factory.TeamMateFactory;
import com.edevstudios.driverstandings.domain.TeamMate;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import java.util.HashMap;

/**
 * Created by Edmund.Simons on 2016/04/01.
 */
public class TeamMateTest
{
    TeamMate teamMate;
    TeamMate teamMateUpdate;
    HashMap<String, String> raceDriver;
    @Before
    public void setUp() throws Exception
    {
        raceDriver = new HashMap<String, String>();
        raceDriver.put("name","Lewis");
        raceDriver.put("surname","Hamillton");
        raceDriver.put("country","England");
        raceDriver.put("team","Mercedes AMG");
        teamMate = TeamMateFactory.createTeamMate(raceDriver, 18, 7, 0);
    }

    @Test
    public void testCreate() throws Exception
    {
        Assert.assertEquals("Lewis", teamMate.getName());
    }

    @Test
    public void testEdit() throws Exception
    {
        teamMateUpdate = new TeamMate.Builder(teamMate.getSurname()).copy(teamMate).surname("Hamilton").build();

        Assert.assertEquals("Hamillton", teamMate.getSurname());
        Assert.assertEquals("Hamilton", teamMateUpdate.getSurname());
    }

    @After
    public void tearDown() throws Exception
    {

    }
}
