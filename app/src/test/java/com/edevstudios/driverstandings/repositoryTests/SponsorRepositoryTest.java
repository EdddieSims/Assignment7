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

    @After
    public void tearDown() throws Exception
    {
        //carImpl.close();
    }
}
