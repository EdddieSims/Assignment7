package com.edevstudios.driverstandings.domainTests;

import com.edevstudios.driverstandings.conf.factory.LeaderboardFactory;
import com.edevstudios.driverstandings.domain.Leaderboard;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

/**
 * Created by Edmund.Simons on 2016/04/01.
 */
public class LeaderboardTest
{
    Leaderboard leaderboard;
    Leaderboard leaderboardUpdate;
    @Before
    public void setUp() throws Exception
    {
        leaderboard = LeaderboardFactory.createLeaderboard("Nico", "Rosburg", 1.23, 1.11, 3.35, 10, 3, 7);
    }

    @Test
    public void testCreate() throws Exception
    {
        Assert.assertEquals(3, leaderboard.getCurrLap());
    }

    @Test
    public void testEdit() throws Exception
    {
        leaderboardUpdate = new Leaderboard.Builder(leaderboard.getName()).copy(leaderboard).currLap(4).lapsRemaining(6).build();
        Assert.assertEquals(3, leaderboard.getCurrLap());
        Assert.assertEquals(4, leaderboardUpdate.getCurrLap());
        Assert.assertEquals(6, leaderboardUpdate.getLapsRemaining());
    }

    @After
    public void tearDown() throws Exception
    {

    }
}
