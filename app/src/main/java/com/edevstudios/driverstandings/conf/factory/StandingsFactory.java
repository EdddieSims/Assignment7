package com.edevstudios.driverstandings.conf.factory;

import com.edevstudios.driverstandings.domain.Standings;

import java.util.Map;

/**
 * Created by Edmund.Simons on 2016/04/01.
 */
public class StandingsFactory
{
    public static Standings createStandings(Map<String, String> values, int points, int behind, int numOfWins)
    {
        Standings standings = new Standings.Builder(values.get("name"))
                .surname(values.get("surname"))
                .team(values.get("team"))
                .points(points)
                .behind(behind)
                .numOfWins(numOfWins)
                .build();

        return standings;
    }
}
