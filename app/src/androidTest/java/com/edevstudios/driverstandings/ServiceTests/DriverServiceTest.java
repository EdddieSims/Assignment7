package com.edevstudios.driverstandings.ServiceTests;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.test.AndroidTestCase;

import com.edevstudios.driverstandings.conf.factory.DriverFactory;
import com.edevstudios.driverstandings.conf.factory.util.App;
import com.edevstudios.driverstandings.domain.Driver;
import com.edevstudios.driverstandings.services.Impl.DriverServiceImpl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.test.AndroidTestCase;
import junit.framework.Assert;

import org.junit.Test;

import java.util.HashMap;

/**
 * Created by Edmund.Simons on 2016/06/06.
 */
public class DriverServiceTest extends AndroidTestCase
{
    private DriverServiceImpl driverServiceImpl;
    private boolean isBound;
    private static final String TAG = "DRIVER TEST ";
    HashMap<String, String> raceDriver;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(this.getContext(), DriverServiceImpl.class);
        App.context = this.getContext();
        driverServiceImpl = DriverServiceImpl.getInstance();
        App.getAppContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            DriverServiceImpl.DriverServiceLocalBinder binder
                    = (DriverServiceImpl.DriverServiceLocalBinder) service;
            driverServiceImpl = binder.getService();
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
        raceDriver.put("name", "Edmund");
        raceDriver.put("surname", "Simons");
        raceDriver.put("country","South Africa");
        raceDriver.put("team", "Renault");

        Driver createDriver = DriverFactory.createDriver(raceDriver, 0, 0, 0);
        //Driver newEntity = driverServiceImpl.save(createDriver);
        //Assert.assertNotNull(TAG+" CREATE",newEntity);
    }
}
