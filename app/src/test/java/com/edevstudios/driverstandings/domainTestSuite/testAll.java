package com.edevstudios.driverstandings.domainTestSuite;

import com.edevstudios.driverstandings.domainTests.CarTest;
import com.edevstudios.driverstandings.domainTests.DriverTest;
import com.edevstudios.driverstandings.domainTests.EngineTest;
import com.edevstudios.driverstandings.domainTests.LeaderboardTest;
import com.edevstudios.driverstandings.domainTests.SponsorTest;
import com.edevstudios.driverstandings.domainTests.StandingsTest;
import com.edevstudios.driverstandings.domainTests.TeamMateTest;
import com.edevstudios.driverstandings.domainTests.TrackTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by Edmund on 2016/04/17.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({CarTest.class,
        DriverTest.class,
        EngineTest.class,
        LeaderboardTest.class,
        SponsorTest.class,
        StandingsTest.class,
        TeamMateTest.class,
        TrackTest.class})
public class testAll
{
}
