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
    Track returnTrack;
    Track updateTrack;

    @Override
    public void setUp() throws Exception
    {
        trackImpl = new TrackRepositoryImpl(mContext);
        track = TrackFactory.createTrack("Germany", "Leguna Seca", 17, 9.8);
    }

    @Test
    public void testObject() throws Exception
    {
        Assert.assertNotNull(trackImpl);
    }

    @Test
    public void testCreate() throws Exception
    {
        trackImpl.save(track);
        //Assert.assertNotNull(carImpl);
    }

    @Test
    public void testRead() throws Exception
    {
        returnTrack = trackImpl.findById(1L);
        String name = returnTrack.getTrackName();
        System.out.println(name);
    }

    @Test
    public void testUpdate() throws Exception
    {
        updateTrack = new Track.Builder(track.getCountry())
                .trackName("Leguna Seca")
                .numOfTurns(17)
                .length(8.9)
                .build();
        trackImpl.update(updateTrack);
    }

    @Test
    public void testDelete() throws Exception
    {
        trackImpl.delete(track);
    }
    @After
    public void tearDown() throws Exception
    {
        //carImpl.close();
    }
}
