package com.edevstudios.driverstandings.ServiceTests;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.test.AndroidTestCase;

import com.edevstudios.driverstandings.conf.factory.TeamMateFactory;
import com.edevstudios.driverstandings.conf.factory.util.App;
import com.edevstudios.driverstandings.domain.TeamMate;
import com.edevstudios.driverstandings.services.Impl.TeamMateServiceImpl;

import junit.framework.Assert;

import org.junit.Test;

import java.util.HashMap;

/**
 * Created by Edmund.Simons on 2016/06/19.
 */
public class TeamMateServiceTest extends AndroidTestCase
{
    private TeamMateServiceImpl teamMateServiceImpl;
    private boolean isBound;
    private static final String TAG = "TEAMMATE TEST ";
    HashMap<String, String> raceDriver;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(this.getContext(), TeamMateServiceImpl.class);
        App.context = this.getContext();
        teamMateServiceImpl = TeamMateServiceImpl.getInstance();
        App.getAppContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            TeamMateServiceImpl.TeamMateServiceLocalBinder binder
                    = (TeamMateServiceImpl.TeamMateServiceLocalBinder) service;
            teamMateServiceImpl = binder.getService();
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
        raceDriver = new HashMap<String, String>();
        raceDriver.put("name","Lewis");
        raceDriver.put("surname","Hamillton");
        raceDriver.put("country","England");
        raceDriver.put("team","Mercedes AMG");

        TeamMate createTeamMate = TeamMateFactory.createTeamMate(raceDriver, 18, 7, 0);
        TeamMate newEntity = teamMateServiceImpl.save(createTeamMate);
        Assert.assertNotNull(TAG+" CREATE",newEntity);
    }
}
