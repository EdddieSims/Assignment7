package com.edevstudios.driverstandings.repositoryTests;

import android.test.AndroidTestCase;

import com.edevstudios.driverstandings.conf.factory.TrackFactory;
import com.edevstudios.driverstandings.domain.Track;
import com.edevstudios.driverstandings.repository.domain.Impl.TrackRepositoryImpl;
import com.edevstudios.driverstandings.repository.domain.TrackRepository;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

/**
 * Created by Edmund.Simons on 2016/04/24.
 */
public class TrackRepositoryTest extends AndroidTestCase
{
    private static final String TAG = "TRACK TEST";
    private Long id;

    private String make = "Ferrari";
    private String model = "Indie";
    private int year = 2015;

    @Test
    public void testTrackCRUD() throws Exception{
        TrackRepository repo = new TrackRepositoryImpl(this.getContext());

        Track track = new Track.Builder("Spain")
                .trackName("Spa")
                .numOfTurns(12)
                .length(9.7)
                .build();
        Track insertedEntity = repo.save(track);
        id = insertedEntity.getId();
        Assert.assertNotNull(TAG+" CREATE",insertedEntity);

        //READ ALL
        Set<Track> cars = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL", cars.size()>0);

        //READ ENTITY
        Track entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);

        //UPDATE ENTITY
        Track updateEntity = new Track.Builder(make)
                .copy(entity)
                .numOfTurns(16)
                .build();
        repo.update(updateEntity);
        Track newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY", 16, newEntity.getNumOfTurns());

        // DELETE ENTITY
        repo.delete(insertedEntity);
        Track deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);
    }
    /*private TrackRepositoryImpl trackImpl;
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
    }*/
}
