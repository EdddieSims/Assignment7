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

    @After
    public void tearDown() throws Exception
    {
        //carImpl.close();
    }
}
