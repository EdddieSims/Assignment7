package com.edevstudios.driverstandings.services;

import com.edevstudios.driverstandings.domain.Sponsor;

import java.util.Set;

/**
 * Created by Edmund.Simons on 2016/06/19.
 */
public interface SponsorService
{
    Sponsor findById(Long id);

    Set<Sponsor> findAll();

    Sponsor save(Sponsor entity);

    Sponsor update(Sponsor entity);

    Sponsor delete(Sponsor entity);
}
