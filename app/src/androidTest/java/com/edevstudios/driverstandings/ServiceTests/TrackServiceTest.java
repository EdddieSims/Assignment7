package com.edevstudios.driverstandings.ServiceTests;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.test.AndroidTestCase;

import com.edevstudios.driverstandings.conf.factory.TrackFactory;
import com.edevstudios.driverstandings.conf.factory.util.App;
import com.edevstudios.driverstandings.domain.Track;
import com.edevstudios.driverstandings.services.Impl.TrackServiceImpl;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Created by Edmund.Simons on 2016/06/19.
 */
public class TrackServiceTest extends AndroidTestCase
{
    private TrackServiceImpl trackServiceImpl;
    private boolean isBound;
    private static final String TAG = "TRACK TEST ";

    @Override
    public void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(this.getContext(), TrackServiceImpl.class);
        App.context = this.getContext();
        trackServiceImpl = TrackServiceImpl.getInstance();
        App.getAppContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            TrackServiceImpl.TrackServiceLocalBinder binder
                    = (TrackServiceImpl.TrackServiceLocalBinder) service;
            trackServiceImpl = binder.getService();
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
        Track createTrack = TrackFactory.createTrack("Germany", "Nurburg Ring", 17, 8.9);
        Track newEntity = trackServiceImpl.save(createTrack);
        Assert.assertNotNull(TAG+" CREATE",newEntity);
    }
}
