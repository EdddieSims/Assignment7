package com.edevstudios.driverstandings.services;

import com.edevstudios.driverstandings.domain.Car;

import java.util.Set;

/**
 * Created by Edmund.Simons on 2016/06/19.
 */
public interface CarService
{
    Car findById(Long id);

    Set<Car> findAll();

    Car save(Car entity);

    Car update(Car entity);

    Car delete(Car entity);
}
