package com.edevstudios.driverstandings.repositoryTests;

import android.test.AndroidTestCase;

import com.edevstudios.driverstandings.conf.factory.EngineFactory;
import com.edevstudios.driverstandings.domain.Engine;
import com.edevstudios.driverstandings.repository.domain.Impl.EngineRepositoryImpl;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Edmund.Simons on 2016/04/24.
 */
public class EngineRepositoryTest extends AndroidTestCase
{
    private EngineRepositoryImpl engineImpl;
    Engine engine;
    Engine updateEngine;
    Engine returnEngine;

    @Before
    public void setUp() throws Exception
    {
        engineImpl = new EngineRepositoryImpl(mContext);
        engine = EngineFactory.createEngine("Mercedes Benz", "AMG-12/6", 8, 4.5);
    }

    @Test
    public void testObject() throws Exception
    {
        Assert.assertNotNull(engineImpl);
    }

    @Test
    public void testCreate() throws Exception
    {
        engineImpl.save(engine);
        //Assert.assertNotNull(carImpl);
    }

    @Test
    public void testRead() throws Exception
    {
        returnEngine = engineImpl.findById(1L);
        String make = returnEngine.getBrand();
        System.out.println(make);
    }

    @Test
    public void testUpdate() throws Exception
    {
        updateEngine = new Engine.Builder(returnEngine.getBrand())
                .model("AMG-6/6")
                .numOfPistons(6)
                .powerOutput(1.6).build();
        engineImpl.update(updateEngine);
    }

    @Test
    public void testDelete() throws Exception
    {
        engineImpl.delete(engine);
    }

    @After
    public void tearDown() throws Exception
    {
        //carImpl.close();
    }
}
