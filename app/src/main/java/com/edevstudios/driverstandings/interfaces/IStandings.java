package com.edevstudios.driverstandings.interfaces;

/**
 * Created by Edmund.Simons on 2016/03/29.
 */
public interface IStandings
{
    Long getId();
    String getName();
    String getSurname();
    String getTeam();
    int getPoints();
    int getBehind();
    int getNumOfWins();
}
