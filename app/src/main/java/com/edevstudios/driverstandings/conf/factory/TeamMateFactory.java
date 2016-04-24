package com.edevstudios.driverstandings.conf.factory;

import com.edevstudios.driverstandings.domain.TeamMate;

import java.util.Map;

/**
 * Created by Edmund.Simons on 2016/04/01.
 */
public class TeamMateFactory
{
    public static TeamMate createTeamMate(Map<String, String> value, int points, int behind, int numOfWins)
    {
        TeamMate createTeamMate = new TeamMate.Builder(value.get("name"))
                .surname(value.get("surname"))
                .country(value.get("country"))
                .team(value.get("team"))
                .points(25)
                .behind(0)
                .numOfWins(1).build();
        return createTeamMate;
    }
}
