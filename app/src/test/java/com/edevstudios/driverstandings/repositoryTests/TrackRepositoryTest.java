package com.edevstudios.driverstandings.repositoryTests;

import android.test.AndroidTestCase;

import com.edevstudios.driverstandings.conf.factory.TrackFactory;
import com.edevstudios.driverstandings.domain.Track;
import com.edevstudios.driverstandings.repository.domain.Impl.TrackRepositoryImpl;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Edmund.Simons on 2016/04/24.
 */
public class TrackRepositoryTest extends AndroidTestCase
{
    private TrackRepositoryImpl trackImpl;
    Track track;

    @Override
    public void setUp() throws Exception
    {
        trackImpl = new TrackRepositoryImpl(mContext);
        track = TrackFactory.createTrack("Germany", "Leguna Seca", 17, 8.9);
    }

    @Test
    public void testObject() throws Exception
    {
        Assert.assertNotNull(trackImpl);
    }

    @After
    public void tearDown() throws Exception
    {
        //carImpl.close();
    }
}
