package com.edevstudios.driverstandings.interfaces;

/**
 * Created by Edmund.Simons on 2016/03/29.
 */
public interface ILeaderBoard
{
    Long getId();
    String getName();
    String getSurname();
    double getFastestLapTime();
    double getCurrLapTime();
    double getTotalRaceTime();
    int getTotalLaps();
    int getCurrLap();
    int getLapsRemaining();
}
