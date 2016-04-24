package com.edevstudios.driverstandings.repositoryTests;

import android.test.AndroidTestCase;

import com.edevstudios.driverstandings.conf.factory.EngineFactory;
import com.edevstudios.driverstandings.domain.Engine;
import com.edevstudios.driverstandings.repository.domain.Impl.EngineRepositoryImpl;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Edmund.Simons on 2016/04/24.
 */
public class EngineRepositoryTest extends AndroidTestCase
{
    private EngineRepositoryImpl engineImpl;
    Engine engine;

    @Override
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

    @After
    public void tearDown() throws Exception
    {
        //carImpl.close();
    }
}
