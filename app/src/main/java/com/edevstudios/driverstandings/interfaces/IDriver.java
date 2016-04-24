package com.edevstudios.driverstandings.interfaces;

/**
 * Created by Edmund.Simons on 2016/03/29.
 */
public interface IDriver
{
    Long getId();
    String getName();
    String getSurname();
    String getCountry();
    String getTeam();
    int getPoints();
    int getBehind();
    int getNumOfWins();
}
