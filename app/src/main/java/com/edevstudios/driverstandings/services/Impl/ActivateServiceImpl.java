package com.edevstudios.driverstandings.services.Impl;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.edevstudios.driverstandings.conf.factory.DriverFactory;
import com.edevstudios.driverstandings.conf.factory.util.DomainState;
import com.edevstudios.driverstandings.domain.Driver;
import com.edevstudios.driverstandings.repository.domain.DriverRepository;
import com.edevstudios.driverstandings.repository.domain.Impl.DriverRepositoryImpl;
import com.edevstudios.driverstandings.services.UpdateService;

import java.util.HashMap;

/**
 * Created by Edmund.Simons on 2016/05/06.
 */
public class ActivateServiceImpl extends Service implements UpdateService
{
    private final IBinder localBinder = new MyLocalBinder();
    private DriverRepositoryImpl dRepo;
    private Driver driver;
    private Driver updatedDriver;
    private int oldPoints;

    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }


    public class MyLocalBinder extends Binder
    {
        public ActivateServiceImpl getService()
        {
            return ActivateServiceImpl.this;
        }
    }

    //UpdateService Override methods

    @Override
    public String driverExists()
    {
        if (true)
        {
            HashMap<String, String> raceDriver;
            raceDriver = new HashMap<String, String>();
            raceDriver.put("name","Nico");
            raceDriver.put("surname","Ros");
            raceDriver.put("country","Germany");
            raceDriver.put("team","Mercedes AMG");


            Driver driver = DriverFactory.createDriver(raceDriver, 25, 0, 1);
            return DomainState.ACTIVATED.name();
        }
        else
        {
            return DomainState.NOTACTIVATED.name();
        }
    }

    @Override
    public String updateDriverWinnings(Long id, int points, int behind, int wins) {
        driver = dRepo.findById(id);
        updatedDriver = new Driver.Builder(driver.getName())
                .surname(driver.getSurname())
                .country(driver.getCountry())
                .team(driver.getTeam())
                .points(driver.getPoints() + points)
                .behind(driver.getBehind() + behind)
                .numOfWins(driver.getNumOfWins() + wins)
                .build();

        oldPoints = driver.getPoints();
        dRepo.update(updatedDriver);
        return "UPDATED";
    }

    @Override
    public boolean isUpdated(Long id)
    {
        driver = dRepo.findById(id);
        int newPoints = driver.getPoints();
        return newPoints > oldPoints;
    }
}
