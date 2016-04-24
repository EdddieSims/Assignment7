package com.edevstudios.driverstandings.domainTests;

import com.edevstudios.driverstandings.conf.factory.TrackFactory;
import com.edevstudios.driverstandings.domain.Track;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

/**
 * Created by Edmund.Simons on 2016/04/01.
 */
public class TrackTest
{
    Track track;
    Track trackUpdate;
    @Before
    public void setUp() throws Exception
    {
        track = TrackFactory.createTrack("Germany", "Leguna Seca", 17, 8.9);
    }

    @Test
    public void testCreate() throws Exception
    {
        Assert.assertEquals("Leguna Seca", track.getTrackName());
    }

    @Test
    public void testEdit() throws Exception
    {
        trackUpdate = new Track.Builder(track.getCountry()).copy(track).trackName("Hockenheim").build();

        Assert.assertEquals("Leguna Seca", track.getTrackName());
        Assert.assertEquals("Hockenheim", trackUpdate.getTrackName());
    }

    @After
    public void tearDown() throws Exception
    {

    }
}
