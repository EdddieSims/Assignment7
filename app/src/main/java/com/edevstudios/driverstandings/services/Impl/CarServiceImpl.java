package com.edevstudios.driverstandings.services.Impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.edevstudios.driverstandings.conf.factory.util.App;
import com.edevstudios.driverstandings.domain.Car;
import com.edevstudios.driverstandings.repository.domain.CarRepository;
import com.edevstudios.driverstandings.repository.domain.Impl.CarRepositoryImpl;
import com.edevstudios.driverstandings.services.CarService;

import java.util.Set;

/**
 * Created by Edmund.Simons on 2016/06/19.
 */
public class CarServiceImpl extends Service implements CarService
{
    private final CarRepository carRepository;
    private final IBinder localBinder = new CarServiceLocalBinder();
    private static CarServiceImpl service = null;

    public CarServiceImpl()
    {
        carRepository = new CarRepositoryImpl(App.getAppContext());
    }

    public static CarServiceImpl getInstance() {
        if (service == null)
            service = new CarServiceImpl();
        return service;
    }

    public class CarServiceLocalBinder extends Binder
    {
        public CarServiceImpl getService()
        {
            return CarServiceImpl.this;
        }
    }


    @Override
    public Car findById(Long id) {
        return carRepository.findById(id);
    }

    @Override
    public Set<Car> findAll() {
        return carRepository.findAll();
    }

    @Override
    public Car save(Car entity) {
        return carRepository.save(entity);
    }

    @Override
    public Car update(Car entity) {
        return carRepository.update(entity);
    }

    @Override
    public Car delete(Car entity) {
        return carRepository.delete(entity);
    }

    @Override
    public IBinder onBind(Intent intent)
    {
        return localBinder;
    }
}
