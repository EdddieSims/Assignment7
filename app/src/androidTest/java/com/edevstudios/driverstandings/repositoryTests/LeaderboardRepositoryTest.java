package com.edevstudios.driverstandings.repositoryTests;

import android.test.AndroidTestCase;

import com.edevstudios.driverstandings.conf.factory.LeaderboardFactory;
import com.edevstudios.driverstandings.domain.Leaderboard;
import com.edevstudios.driverstandings.repository.domain.Impl.LeaderboardRepositoryImpl;
import com.edevstudios.driverstandings.repository.domain.LeaderboardRepository;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

/**
 * Created by Edmund.Simons on 2016/04/24.
 */
public class LeaderboardRepositoryTest extends AndroidTestCase
{
    private static final String TAG = "LEADER BOARD TEST";
    private Long id;

    private String make = "Ferrari";
    private String model = "Indie";
    private int year = 2015;

    @Test
    public void testLeaderCRUD() throws Exception{
        LeaderboardRepository repo = new LeaderboardRepositoryImpl(this.getContext());
        //"Nico", "Rosburg", 1.23, 1.11, 3.35, 10, 3, 7
        Leaderboard leader = new Leaderboard.Builder("Nico")
                .surname("Rosburg")
                .fastestLapTime(1.23)
                .currLapTime(1.11)
                .totalRaceTime(3.35)
                .totalLaps(10)
                .currLap(3)
                .lapsRemaining(7)
                .build();
        Leaderboard insertedEntity = repo.save(leader);
        id = insertedEntity.getId();
        Assert.assertNotNull(TAG+" CREATE",insertedEntity);

        //READ ALL
        Set<Leaderboard> cars = repo.findAll();
        Assert.assertTrue(TAG+" READ ALL", cars.size()>0);

        //READ ENTITY
        Leaderboard entity = repo.findById(id);
        Assert.assertNotNull(TAG+" READ ENTITY",entity);

        //UPDATE ENTITY
        Leaderboard updateEntity = new Leaderboard.Builder(make)
                .copy(entity)
                .currLap(4)
                .lapsRemaining(6)
                .build();
        repo.update(updateEntity);
        Leaderboard newEntity = repo.findById(id);
        Assert.assertEquals(TAG+ " UPDATE ENTITY", 6, newEntity.getLapsRemaining());

        // DELETE ENTITY
        repo.delete(insertedEntity);
        Leaderboard deletedEntity = repo.findById(id);
        Assert.assertNull(TAG+" DELETE",deletedEntity);
    }
}
