package com.edevstudios.driverstandings.ServiceTests;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.test.AndroidTestCase;

import com.edevstudios.driverstandings.conf.factory.EngineFactory;
import com.edevstudios.driverstandings.conf.factory.util.App;
import com.edevstudios.driverstandings.domain.Engine;
import com.edevstudios.driverstandings.services.Impl.EngineServiceImpl;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Created by Edmund.Simons on 2016/06/19.
 */
public class EngineServiceTest extends AndroidTestCase
{
    private EngineServiceImpl engineServiceImpl;
    private boolean isBound;
    private static final String TAG = "ENGINE TEST ";

    @Override
    public void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(this.getContext(), EngineServiceImpl.class);
        App.context = this.getContext();
        engineServiceImpl = EngineServiceImpl.getInstance();
        App.getAppContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            EngineServiceImpl.EngineServiceLocalBinder binder
                    = (EngineServiceImpl.EngineServiceLocalBinder) service;
            engineServiceImpl = binder.getService();
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
        Engine createEngine = EngineFactory.createEngine("Mercedes Benz", "AMG-12/6", 8, 4.5);
        Engine newEntity = engineServiceImpl.save(createEngine);
        Assert.assertNotNull(TAG+" CREATE",newEntity);
    }
}
