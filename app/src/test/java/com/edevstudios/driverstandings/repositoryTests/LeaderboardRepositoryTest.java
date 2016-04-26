package com.edevstudios.driverstandings.repositoryTests;

import android.test.AndroidTestCase;

import com.edevstudios.driverstandings.conf.factory.LeaderboardFactory;
import com.edevstudios.driverstandings.domain.Leaderboard;
import com.edevstudios.driverstandings.repository.domain.Impl.LeaderboardRepositoryImpl;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Edmund.Simons on 2016/04/24.
 */
public class LeaderboardRepositoryTest extends AndroidTestCase
{
    private LeaderboardRepositoryImpl leaderboardImpl;
    Leaderboard leaderboard;
    Leaderboard returnLeaderboard;
    Leaderboard updateLeaderboard;
    @Override
    public void setUp() throws Exception
    {
        leaderboardImpl = new LeaderboardRepositoryImpl(mContext);
        leaderboard = LeaderboardFactory.createLeaderboard("Nico", "Rosburg", 1.23, 1.11, 3.35, 10, 3, 7);
    }

    @Test
    public void testObject() throws Exception
    {
        Assert.assertNotNull(leaderboardImpl);
    }

    @Test
    public void testCreate() throws Exception
    {
        leaderboardImpl.save(leaderboard);
        //Assert.assertNotNull(carImpl);
    }

    @Test
    public void testRead() throws Exception
    {
        returnLeaderboard = leaderboardImpl.findById(1L);
        String name = returnLeaderboard.getName();
        System.out.println(name);
    }

    @Test
    public void testUpdate() throws Exception
    {
        updateLeaderboard = new Leaderboard.Builder(leaderboard.getName())
                .surname("Rosburg")
                .fastestLapTime(1.20)
                .currLapTime(1.11)
                .totalRaceTime(3.35)
                .totalLaps(10)
                .currLap(3)
                .lapsRemaining(7)
                .build();
        leaderboardImpl.update(updateLeaderboard);
    }

    @Test
    public void testDelete() throws Exception
    {
        leaderboardImpl.delete(leaderboard);
    }

    @After
    public void tearDown() throws Exception
    {
        //carImpl.close();
    }
}
