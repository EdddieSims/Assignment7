package com.edevstudios.driverstandings.repositoryTests;

import android.test.AndroidTestCase;

import com.edevstudios.driverstandings.conf.factory.EngineFactory;
import com.edevstudios.driverstandings.domain.Engine;
import com.edevstudios.driverstandings.repository.domain.EngineRepository;
import com.edevstudios.driverstandings.repository.domain.Impl.EngineRepositoryImpl;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

/**
 * Created by Edmund.Simons on 2016/04/24.
 */
public class EngineRepositoryTest extends AndroidTestCase
{
    private static final String TAG = "ENGINE TEST";
    private Long id;

    private String brand = "Mercedes Benz";
    private String model = "AMG-12/6";
    private int pistons = 8;
    private double power = 4.5;

    @Test
    public void testEngineCRUD() throws Exception{
        EngineRepository repo = new EngineRepositoryImpl(this.getContext());

        Engine engine = new Engine.Builder(brand)
                .model(model)
                .numOfPistons(pistons)
                .powerOutput(power)
                .build();
        Engine insertedEntity = repo.save(engine);
        id = insertedEntity.getId();
        Assert.assertNotNull(TAG+" CREATE",insertedEntity);

        //READ ALL
        Set<Engine> cars = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL", cars.size()>0);

        //READ ENTITY
        Engine entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);

        //UPDATE ENTITY
        Engine updateEntity = new Engine.Builder(brand)
                .copy(entity)
                .numOfPistons(6)
                .build();
        repo.update(updateEntity);
        Engine newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY", 6, newEntity.getNumOfPistons());

        // DELETE ENTITY
        repo.delete(insertedEntity);
        Engine deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);

    }

    /*@Before
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
    }*/
}
