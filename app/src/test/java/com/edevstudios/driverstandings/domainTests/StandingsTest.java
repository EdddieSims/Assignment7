package com.edevstudios.driverstandings.domainTests;

import com.edevstudios.driverstandings.conf.factory.StandingsFactory;
import com.edevstudios.driverstandings.domain.Standings;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import java.util.HashMap;

/**
 * Created by Edmund.Simons on 2016/04/01.
 */
public class StandingsTest
{
    HashMap<String, String> values;
    Standings firstRace;
    Standings secondRace;

    @Before
    public void setUp() throws Exception
    {
        values = new HashMap<String, String>();
        values.put("name","Nico");
        values.put("surname","Rosburg");
        values.put("team","Mercedes AMG");
        firstRace = StandingsFactory.createStandings(values, 25, 0, 1);
    }

    @Test
    public void testCreate() throws Exception
    {
        Assert.assertEquals(25, firstRace.getPoints());
    }

    @Test
    public void testEdit() throws Exception
    {
        secondRace = new Standings.Builder(firstRace.getName()).copy(firstRace).points(43).behind(0).numOfWins(1).build();
        Assert.assertEquals(25, firstRace.getPoints());
        Assert.assertEquals(43, secondRace.getPoints());
    }

    @After
    public void tearDown() throws Exception
    {

    }
}
