package com.edevstudios.driverstandings.conf.factory;

import com.edevstudios.driverstandings.domain.Leaderboard;

/**
 * Created by Edmund.Simons on 2016/04/01.
 */
public class LeaderboardFactory
{
    public static Leaderboard createLeaderboard(String name, String surname, double fastestLap, double currLapTime, double totlaRaceTime, int laps, int currLap, int lapsRemaining)
    {
        Leaderboard leaderboard = new Leaderboard.Builder(name)
                .surname(surname)
                .fastestLapTime(fastestLap)
                .currLapTime(currLapTime)
                .totalRaceTime(totlaRaceTime)
                .totalLaps(laps)
                .currLap(currLap)
                .lapsRemaining(lapsRemaining)
                .build();
        return leaderboard;
    }
}
