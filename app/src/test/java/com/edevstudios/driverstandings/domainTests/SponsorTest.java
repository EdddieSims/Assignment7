package com.edevstudios.driverstandings.domainTests;

import com.edevstudios.driverstandings.conf.factory.SponsorFactory;
import com.edevstudios.driverstandings.domain.Sponsor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

/**
 * Created by Edmund.Simons on 2016/04/01.
 */
public class SponsorTest
{
    Sponsor petronas;
    Sponsor Petronas;
    @Before
    public void setUp() throws Exception
    {
        petronas = SponsorFactory.createSponsor("Petron", "Marine Blue");
    }

    @Test
    public void testCreate() throws Exception
    {
        Assert.assertEquals("Petron", petronas.getName());
    }

    @Test
    public void testEdit() throws Exception
    {
        Petronas = new Sponsor.Builder(petronas.getName()).copy(petronas).logoColour("Aqua blue").build();
        Assert.assertEquals("Marine Blue", petronas.getLogoColour());
        Assert.assertEquals("Aqua blue", Petronas.getLogoColour());
    }

    @After
    public void tearDown() throws Exception
    {

    }
}
