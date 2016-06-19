package com.edevstudios.driverstandings.repositoryTests;

import android.test.AndroidTestCase;

import com.edevstudios.driverstandings.conf.factory.SponsorFactory;
import com.edevstudios.driverstandings.domain.Sponsor;
import com.edevstudios.driverstandings.repository.domain.Impl.SponsorRepositoryImpl;
import com.edevstudios.driverstandings.repository.domain.SponsorRepository;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

/**
 * Created by Edmund.Simons on 2016/04/24.
 */
public class SponsorRepositoryTest extends AndroidTestCase
{
    private static final String TAG = "CAR TEST";
    private Long id;

    private String name = "Petronas";
    private String logo = "Marine Blue";

    @Test
    public void testSponsorCRUD() throws Exception{
        SponsorRepository repo = new SponsorRepositoryImpl(this.getContext());

        Sponsor car = new Sponsor.Builder(name)
                .logoColour(logo)
                .build();
        Sponsor insertedEntity = repo.save(car);
        id = insertedEntity.getId();
        Assert.assertNotNull(TAG+" CREATE",insertedEntity);

        //READ ALL
        Set<Sponsor> cars = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL", cars.size()>0);

        //READ ENTITY
        Sponsor entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);

        //UPDATE ENTITY
        Sponsor updateEntity = new Sponsor.Builder(name)
                .copy(entity)
                .logoColour("Petrol Green")
                .build();
        repo.update(updateEntity);
        Sponsor newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY", "Petrol Green", newEntity.getLogoColour());

        // DELETE ENTITY
        repo.delete(insertedEntity);
        Sponsor deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);
    }
}
