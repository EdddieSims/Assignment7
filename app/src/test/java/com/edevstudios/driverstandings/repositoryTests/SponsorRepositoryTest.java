package com.edevstudios.driverstandings.repositoryTests;

import android.test.AndroidTestCase;

import com.edevstudios.driverstandings.conf.factory.SponsorFactory;
import com.edevstudios.driverstandings.domain.Sponsor;
import com.edevstudios.driverstandings.repository.domain.Impl.SponsorRepositoryImpl;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Edmund.Simons on 2016/04/24.
 */
public class SponsorRepositoryTest extends AndroidTestCase
{
    private SponsorRepositoryImpl sponsorImpl;

    Sponsor petronas;
    Sponsor returnSponsor;
    Sponsor updateSponsor;
    @Override
    public void setUp() throws Exception
    {
        sponsorImpl = new SponsorRepositoryImpl(mContext);
        petronas = SponsorFactory.createSponsor("Petron", "Marine Blue");
    }

    @Test
    public void testObject() throws Exception
    {
        Assert.assertNotNull(sponsorImpl);
    }

    @Test
    public void testCreate() throws Exception
    {
        sponsorImpl.save(petronas);
        //Assert.assertNotNull(carImpl);
    }

    @Test
    public void testRead() throws Exception
    {
        returnSponsor = sponsorImpl.findById(1L);
        String name = returnSponsor.getName();
        System.out.println(name);
    }

    @Test
    public void testUpdate() throws Exception
    {
        updateSponsor = new Sponsor.Builder(petronas.getName())
                .logoColour("Aqua Blue")
                .build();
        sponsorImpl.update(updateSponsor);
    }

    @Test
    public void testDelete() throws Exception
    {
        sponsorImpl.delete(petronas);
    }

    @After
    public void tearDown() throws Exception
    {
        //carImpl.close();
    }
}
