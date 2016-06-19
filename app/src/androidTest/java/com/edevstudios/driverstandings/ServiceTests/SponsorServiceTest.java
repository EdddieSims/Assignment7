package com.edevstudios.driverstandings.ServiceTests;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.test.AndroidTestCase;

import com.edevstudios.driverstandings.conf.factory.SponsorFactory;
import com.edevstudios.driverstandings.conf.factory.util.App;
import com.edevstudios.driverstandings.domain.Sponsor;
import com.edevstudios.driverstandings.services.Impl.SponsorServiceImpl;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Created by Edmund.Simons on 2016/06/19.
 */
public class SponsorServiceTest extends AndroidTestCase
{
    private SponsorServiceImpl sponsorServiceImpl;
    private boolean isBound;
    private static final String TAG = "SPONSOR TEST ";

    @Override
    public void setUp() throws Exception {
        super.setUp();
        Intent intent = new Intent(this.getContext(), SponsorServiceImpl.class);
        App.context = this.getContext();
        sponsorServiceImpl = SponsorServiceImpl.getInstance();
        App.getAppContext().bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            SponsorServiceImpl.SponsorServiceLocalBinder binder
                    = (SponsorServiceImpl.SponsorServiceLocalBinder) service;
            sponsorServiceImpl = binder.getService();
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
        Sponsor createSponsor = SponsorFactory.createSponsor("Petron", "Petrol Green");
        Sponsor newEntity = sponsorServiceImpl.save(createSponsor);
        Assert.assertNotNull(TAG+" CREATE",newEntity);
    }
}
