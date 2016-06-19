package com.edevstudios.driverstandings.services.Impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.edevstudios.driverstandings.conf.factory.util.App;
import com.edevstudios.driverstandings.domain.Engine;
import com.edevstudios.driverstandings.repository.domain.EngineRepository;
import com.edevstudios.driverstandings.repository.domain.Impl.EngineRepositoryImpl;
import com.edevstudios.driverstandings.services.EngineService;

import java.util.Set;

/**
 * Created by Edmund.Simons on 2016/06/19.
 */
public class EngineServiceImpl extends Service implements EngineService
{
    private final EngineRepository engineRepository;
    private final IBinder localBinder = new EngineServiceLocalBinder();
    private static EngineServiceImpl service = null;

    public EngineServiceImpl()
    {
        engineRepository = new EngineRepositoryImpl(App.getAppContext());
    }

    public static EngineServiceImpl getInstance() {
        if (service == null)
            service = new EngineServiceImpl();
        return service;
    }

    public class EngineServiceLocalBinder extends Binder
    {
        public EngineServiceImpl getService()
        {
            return EngineServiceImpl.this;
        }
    }


    @Override
    public Engine findById(Long id) {
        return engineRepository.findById(id);
    }

    @Override
    public Set<Engine> findAll() {
        return engineRepository.findAll();
    }

    @Override
    public Engine save(Engine entity) {
        return engineRepository.save(entity);
    }

    @Override
    public Engine update(Engine entity) {
        return engineRepository.update(entity);
    }

    @Override
    public Engine delete(Engine entity) {
        return engineRepository.delete(entity);
    }

    @Override
    public IBinder onBind(Intent intent)
    {
        return localBinder;
    }
}
