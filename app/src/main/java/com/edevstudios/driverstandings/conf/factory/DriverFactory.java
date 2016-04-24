package com.edevstudios.driverstandings.conf.factory;

import com.edevstudios.driverstandings.domain.Driver;

import java.util.Map;

/**
 * Created by Edmund.Simons on 2016/04/01.
 */
public class DriverFactory
{
    public static Driver createDriver(Map<String, String> value, int points, int behind, int numOfWins)
    {
        Driver driver = new Driver.Builder(value.get("name"))
                .surname(value.get("surname"))
                .country(value.get("country"))
                .team(value.get("team"))
                .points(25)
                .behind(0)
                .numOfWins(1).build();
        return driver;
    }
}
