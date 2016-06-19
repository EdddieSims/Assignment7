package com.edevstudios.driverstandings.services;

import com.edevstudios.driverstandings.domain.Leaderboard;

import java.util.Set;

/**
 * Created by Edmund.Simons on 2016/06/19.
 */
public interface LeaderboardService
{
    Leaderboard findById(Long id);

    Set<Leaderboard> findAll();

    Leaderboard save(Leaderboard entity);

    Leaderboard update(Leaderboard entity);

    Leaderboard delete(Leaderboard entity);
}
