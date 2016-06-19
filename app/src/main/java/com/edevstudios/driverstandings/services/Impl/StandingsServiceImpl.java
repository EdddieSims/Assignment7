package com.edevstudios.driverstandings.services.Impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.edevstudios.driverstandings.conf.factory.util.App;
import com.edevstudios.driverstandings.domain.Standings;
import com.edevstudios.driverstandings.repository.domain.Impl.StandingsRepositoryImpl;
import com.edevstudios.driverstandings.repository.domain.StandingsRepository;
import com.edevstudios.driverstandings.services.StandingsService;

import java.util.Set;

/**
 * Created by Edmund.Simons on 2016/06/19.
 */
public class StandingsServiceImpl extends Service implements StandingsService
{
    private final StandingsRepository standingsRepository;
    private final IBinder localBinder = new StandingsServiceLocalBinder();
    private static StandingsServiceImpl service = null;

    public StandingsServiceImpl()
    {
        standingsRepository = new StandingsRepositoryImpl(App.getAppContext());
    }

    public static StandingsServiceImpl getInstance() {
        if (service == null)
            service = new StandingsServiceImpl();
        return service;
    }

    public class StandingsServiceLocalBinder extends Binder
    {
        public StandingsServiceImpl getService()
        {
            return StandingsServiceImpl.this;
        }
    }


    @Override
    public Standings findById(Long id) {
        return standingsRepository.findById(id);
    }

    @Override
    public Set<Standings> findAll() {
        return standingsRepository.findAll();
    }

    @Override
    public Standings save(Standings entity) {
        return standingsRepository.save(entity);
    }

    @Override
    public Standings update(Standings entity) {
        return standingsRepository.update(entity);
    }

    @Override
    public Standings delete(Standings entity) {
        return standingsRepository.delete(entity);
    }

    @Override
    public IBinder onBind(Intent intent)
    {
        return localBinder;
    }
}
