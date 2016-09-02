package com.edevstudios.driverstandings.repositories;

import com.edevstudios.driverstandings.domain.Driver;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Edmund.Simons on 2016/09/02.
 */
public interface RestAPIDriver
{
    HashSet<Driver> getAllDrivers();
}
