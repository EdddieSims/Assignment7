package com.edevstudios.driverstandings.services;

import com.edevstudios.driverstandings.domain.Driver;

import java.util.Set;

/**
 * Created by Edmund.Simons on 2016/06/06.
 */
public interface DriverService
{
    Driver findById(Long id);

    Set<Driver> findAll();

    String save(Driver entity);

    Driver update(Driver entity);

    Driver delete(Driver entity);
}
