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

    @After
    public void tearDown() throws Exception
    {
        //carImpl.close();
    }
}
