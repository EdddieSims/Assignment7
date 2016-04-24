package com.edevstudios.driverstandings.conf.factory;

import com.edevstudios.driverstandings.domain.Track;

/**
 * Created by Edmund.Simons on 2016/04/01.
 */
public class TrackFactory
{
    public static Track createTrack(String country, String trackName, int numOfturns, double lenght)
    {
        Track track = new Track.Builder(country)
                .trackName(trackName)
                .numOfTurns(numOfturns)
                .length(lenght)
                .build();
        return track;
    }
}
