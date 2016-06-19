package com.edevstudios.driverstandings.repositoryTests;

import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

import com.edevstudios.driverstandings.conf.factory.CarFactory;
import com.edevstudios.driverstandings.domain.Car;
import com.edevstudios.driverstandings.repository.domain.CarRepository;
import com.edevstudios.driverstandings.repository.domain.Impl.CarRepositoryImpl;

import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

/**
 * Created by Edmund.Simons on 2016/06/17.
 */
public class CarRepositoryTest extends AndroidTestCase
{
    private static final String TAG = "CAR TEST";
    private Long id;

    private String make = "Ferrari";
    private String model = "Indie";
    private int year = 2015;

    @Test
    public void testCarCRUD() throws Exception{
        CarRepository repo = new CarRepositoryImpl(this.getContext());

        Car car = new Car.Builder(make)
                .model(model)
                .year(year)
                .build();
        Car insertedEntity = repo.save(car);
        id = insertedEntity.getId();
        Assert.assertNotNull(TAG+" CREATE",insertedEntity);

        //READ ALL
        Set<Car> cars = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL", cars.size()>0);

        //READ ENTITY
        Car entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);

        //UPDATE ENTITY
        Car updateEntity = new Car.Builder(make)
                .copy(entity)
                .year(2016)
                .build();
        repo.update(updateEntity);
        Car newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY", 2016, newEntity.getYear());

        // DELETE ENTITY
        repo.delete(insertedEntity);
        Car deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);
    }
}
