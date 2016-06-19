package com.edevstudios.driverstandings.services;

import com.edevstudios.driverstandings.domain.Standings;

import java.util.Set;

/**
 * Created by Edmund.Simons on 2016/06/19.
 */
public interface StandingsService
{
    Standings findById(Long id);

    Set<Standings> findAll();

    Standings save(Standings entity);

    Standings update(Standings entity);

    Standings delete(Standings entity);
}
