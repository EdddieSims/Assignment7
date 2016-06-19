package com.edevstudios.driverstandings.ServiceTests;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.test.AndroidTestCase;

import com.edevstudios.driverstandings.conf.factory.StandingsFactory;
import com.edevstudios.driverstandings.conf.factory.util.App;
import com.edevstudios.driverstandings.domain.Standings;
import com.edevstudios.driverstandings.services.Impl.StandingsServiceImpl;

import junit.framework.Assert;

import org.junit.Test;

import java.util.HashMap;

/**
 * Created by Edmund.Simons on 2016/06/19.
 */
public class StandingsServiceTest extends AndroidTestCase
{
    private StandingsServiceImpl standingsServiceImpl;
    private boolean isBound;
    private static final String TAG = "STANDINGS TEST ";
    HashMap<String, String> values;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(this.getContext(), StandingsServiceImpl.class);
        App.context = this.getContext();
        standingsServiceImpl = StandingsServiceImpl.getInstance();
        App.getAppContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            StandingsServiceImpl.StandingsServiceLocalBinder binder
                    = (StandingsServiceImpl.StandingsServiceLocalBinder) service;
            standingsServiceImpl = binder.getService();
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
        values = new HashMap<String, String>();
        values.put("name","Nico");
        values.put("surname","Rosburg");
        values.put("team","Mercedes AMG");

        Standings createStandings = StandingsFactory.createStandings(values, 25, 0, 1);
        Standings newEntity = standingsServiceImpl.save(createStandings);
        Assert.assertNotNull(TAG+" CREATE",newEntity);
    }
}
