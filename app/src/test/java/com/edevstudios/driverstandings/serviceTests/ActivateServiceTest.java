package com.edevstudios.driverstandings.serviceTests;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.test.AndroidTestCase;

import com.edevstudios.driverstandings.conf.factory.util.App;
import com.edevstudios.driverstandings.services.Impl.ActivateServiceImpl;

import junit.framework.Assert;

/**
 * Created by Edmund.Simons on 2016/05/06.
 */
public class ActivateServiceTest extends AndroidTestCase
{
    private ActivateServiceImpl activateService;
    private boolean isBound = false;

    @Override
    public void setUp() throws Exception
    {
        super.setUp();
        Intent intent = new Intent(App.getAppContext(), ActivateServiceImpl.class);
        App.getAppContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection connection = new ServiceConnection()
    {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service)
        {
            ActivateServiceImpl.MyLocalBinder binder = (ActivateServiceImpl.MyLocalBinder) service;
            activateService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name)
        {
            isBound = false;
        }
    };

    public void testExists() throws Exception
    {
        String exists = activateService.driverExists();
        Assert.assertEquals("ACTIVATED", exists);
    }

    public void testUpdate() throws Exception
    {

    }
}