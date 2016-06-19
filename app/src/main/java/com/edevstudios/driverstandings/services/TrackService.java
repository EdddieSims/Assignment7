package com.edevstudios.driverstandings.services;

import com.edevstudios.driverstandings.domain.Track;

import java.util.Set;

/**
 * Created by Edmund.Simons on 2016/06/19.
 */
public interface TrackService
{
    Track findById(Long id);

    Set<Track> findAll();

    Track save(Track entity);

    Track update(Track entity);

    Track delete(Track entity);
}
