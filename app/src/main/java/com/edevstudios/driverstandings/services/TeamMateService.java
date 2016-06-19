package com.edevstudios.driverstandings.services;

import com.edevstudios.driverstandings.domain.TeamMate;

import java.util.Set;

/**
 * Created by Edmund.Simons on 2016/06/19.
 */
public interface TeamMateService
{
    TeamMate findById(Long id);

    Set<TeamMate> findAll();

    TeamMate save(TeamMate entity);

    TeamMate update(TeamMate entity);

    TeamMate delete(TeamMate entity);
}
