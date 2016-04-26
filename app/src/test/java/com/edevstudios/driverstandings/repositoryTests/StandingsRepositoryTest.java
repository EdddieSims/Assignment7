package com.edevstudios.driverstandings.repositoryTests;

import android.test.AndroidTestCase;

import com.edevstudios.driverstandings.conf.factory.StandingsFactory;
import com.edevstudios.driverstandings.domain.Standings;
import com.edevstudios.driverstandings.repository.domain.Impl.StandingsRepositoryImpl;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

/**
 * Created by Edmund.Simons on 2016/04/24.
 */
public class StandingsRepositoryTest extends AndroidTestCase
{
    private StandingsRepositoryImpl standingsImpl;
    Standings firstRace;
    Standings updateStandings;
    Standings returnStandings;
    HashMap<String, String> values;

    @Override
    public void setUp() throws Exception
    {
        standingsImpl = new StandingsRepositoryImpl(mContext);

        values = new HashMap<String, String>();
        values.put("name","Nico");
        values.put("surname","Rosburg");
        values.put("team","Mercedes AMG");
        firstRace = StandingsFactory.createStandings(values, 25, 0, 1);
    }

    @Test
    public void testObject() throws Exception
    {
        Assert.assertNotNull(standingsImpl);
    }

    @Test
    public void testCreate() throws Exception
    {
        standingsImpl.save(firstRace);
        //Assert.assertNotNull(carImpl);
    }

    @Test
    public void testRead() throws Exception
    {
        returnStandings = standingsImpl.findById(1L);
        String name = returnStandings.getName();
        System.out.println(name);
    }

    @Test
    public void testUpdate() throws Exception
    {
        updateStandings = new Standings.Builder(firstRace.getName())
                .surname(values.get("surname"))
                .team(values.get("team"))
                .points(50)
                .behind(0)
                .numOfWins(2)
                .build();
        standingsImpl.update(updateStandings);
    }

    @Test
    public void testDelete() throws Exception
    {
        standingsImpl.delete(firstRace);
    }

    @After
    public void tearDown() throws Exception
    {
        //carImpl.close();
    }
}
