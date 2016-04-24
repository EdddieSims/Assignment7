package com.edevstudios.driverstandings.interfaces;

/**
 * Created by Edmund.Simons on 2016/03/29.
 */
public interface ITrack
{
    Long getId();
    String getCountry();
    String getTrackName();
    int getNumOfTurns();
    double getLength();
}
