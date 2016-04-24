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

    @After
    public void tearDown() throws Exception
    {
        //carImpl.close();
    }
}
