package com.edevstudios.driverstandings.ServiceTests;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.test.AndroidTestCase;

import com.edevstudios.driverstandings.conf.factory.LeaderboardFactory;
import com.edevstudios.driverstandings.conf.factory.util.App;
import com.edevstudios.driverstandings.domain.Leaderboard;
import com.edevstudios.driverstandings.services.Impl.LeaderboardServiceImpl;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Created by Edmund.Simons on 2016/06/19.
 */
public class LeaderboardServiceTest extends AndroidTestCase
{
    private LeaderboardServiceImpl leaderboardServiceImpl;
    private boolean isBound;
    private static final String TAG = "LEADERBOARD TEST ";

    @Override
    public void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(this.getContext(), LeaderboardServiceImpl.class);
        App.context = this.getContext();
        leaderboardServiceImpl = LeaderboardServiceImpl.getInstance();
        App.getAppContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            LeaderboardServiceImpl.LeaderboardServiceLocalBinder binder
                    = (LeaderboardServiceImpl.LeaderboardServiceLocalBinder) service;
            leaderboardServiceImpl = binder.getService();
            isBound = true;
        }


        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };

    @Test
    public void testCreate() throws Exception
    {
        Leaderboard createLeaderboard = LeaderboardFactory.createLeaderboard("Nico", "Rosburg", 1.23, 1.11, 3.35, 10, 3, 7);
        Leaderboard newEntity = leaderboardServiceImpl.save(createLeaderboard);
        Assert.assertNotNull(TAG+" CREATE",newEntity);
    }
}
