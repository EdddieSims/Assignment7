package com.edevstudios.driverstandings.ServiceTests;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.test.AndroidTestCase;

import com.edevstudios.driverstandings.conf.factory.CarFactory;
import com.edevstudios.driverstandings.conf.factory.util.App;
import com.edevstudios.driverstandings.domain.Car;
import com.edevstudios.driverstandings.services.Impl.CarServiceImpl;

import junit.framework.Assert;

import org.junit.Test;

import java.util.HashMap;

/**
 * Created by Edmund.Simons on 2016/06/19.
 */
public class CarServiceTest extends AndroidTestCase
{
    private CarServiceImpl carServiceImpl;
    private boolean isBound;
    private static final String TAG = "CAR TEST ";

    @Override
    public void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(this.getContext(), CarServiceImpl.class);
        App.context = this.getContext();
        carServiceImpl = CarServiceImpl.getInstance();
        App.getAppContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            CarServiceImpl.CarServiceLocalBinder binder
                    = (CarServiceImpl.CarServiceLocalBinder) service;
            carServiceImpl = binder.getService();
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
        Car createCar = CarFactory.createCar("Mercedes Benz", "Indie", 2015);
        Car newEntity = carServiceImpl.save(createCar);
        Assert.assertNotNull(TAG+" CREATE",newEntity);
    }
}
