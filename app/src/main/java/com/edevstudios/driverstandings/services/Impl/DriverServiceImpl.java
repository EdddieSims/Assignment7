package com.edevstudios.driverstandings.services.Impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.edevstudios.driverstandings.conf.factory.util.App;
import com.edevstudios.driverstandings.domain.Driver;
import com.edevstudios.driverstandings.repositories.RestAPI;
import com.edevstudios.driverstandings.repositories.RestAPIDriver;
import com.edevstudios.driverstandings.repositories.rest.RestDriverAPI;
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

    final RestAPI<Driver,Long> rest = new RestDriverAPI();

    @Override
    public Driver findById(Long id)
    {
        return rest.get(id);
    }

    @Override
    public Set<Driver> findAll()
    {
        return rest.getAll();
    }

    @Override
    public String save(Driver entity)
    {
        return rest.post(entity);
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
