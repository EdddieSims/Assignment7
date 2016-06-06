package com.edevstudios.driverstandings.services.Impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.edevstudios.driverstandings.conf.factory.util.App;
import com.edevstudios.driverstandings.domain.Driver;
import com.edevstudios.driverstandings.repository.domain.DriverRepository;
import com.edevstudios.driverstandings.repository.domain.Impl.DriverRepositoryImpl;
import com.edevstudios.driverstandings.services.DriverService;

import java.util.Set;

/**
 * Created by Edmund.Simons on 2016/06/06.
 */
public class DriverServiceImpl extends Service implements DriverService
{
    private final DriverRepository driverRepository;
    private final IBinder localBinder = new DriverServiceLocalBinder();
    private static DriverServiceImpl service = null;

    public DriverServiceImpl()
    {
        driverRepository = new DriverRepositoryImpl(App.getAppContext());
    }

    public static DriverServiceImpl getInstance() {
        if (service == null)
            service = new DriverServiceImpl();
        return service;
    }

    public class DriverServiceLocalBinder extends Binder
    {
        public DriverServiceImpl getService()
        {
            return DriverServiceImpl.this;
        }
    }


    @Override
    public Driver findById(Long id) {
        return driverRepository.findById(id);
    }

    @Override
    public Set<Driver> findAll() {
        return driverRepository.findAll();
    }

    @Override
    public Driver save(Driver entity) {
        return driverRepository.save(entity);
    }

    @Override
    public Driver update(Driver entity) {
        return driverRepository.update(entity);
    }

    @Override
    public Driver delete(Driver entity) {
        return driverRepository.delete(entity);
    }

    @Override
    public IBinder onBind(Intent intent)
    {
        return localBinder;
    }
}
