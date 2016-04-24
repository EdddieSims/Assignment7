package com.edevstudios.driverstandings.domainTests;

import com.edevstudios.driverstandings.conf.factory.EngineFactory;
import com.edevstudios.driverstandings.domain.Engine;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

/**
 * Created by Edmund.Simons on 2016/04/01.
 */
public class EngineTest
{
    Engine engine;
    Engine newEngine;
    @Before
    public void setUp() throws Exception
    {
        engine = EngineFactory.createEngine("Mercedes Benz", "AMG-12/6", 8, 4.5);
    }

    @Test
    public void testCreate() throws Exception
    {
        Assert.assertEquals("AMG-12/6", engine.getModel());
    }

    @Test
    public void testEdit() throws Exception
    {
        newEngine = new Engine.Builder(engine.getBrand()).copy(engine).numOfPistons(6).powerOutput(1.6).build();

        Assert.assertEquals(8, engine.getNumOfPistons());
        Assert.assertEquals(6, newEngine.getNumOfPistons());
    }

    @After
    public void tearDown() throws Exception
    {

    }
}
