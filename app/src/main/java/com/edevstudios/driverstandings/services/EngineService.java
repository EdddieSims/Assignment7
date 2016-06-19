package com.edevstudios.driverstandings.services;

import com.edevstudios.driverstandings.domain.Engine;

import java.util.Set;

/**
 * Created by Edmund.Simons on 2016/06/19.
 */
public interface EngineService
{
    Engine findById(Long id);

    Set<Engine> findAll();

    Engine save(Engine entity);

    Engine update(Engine entity);

    Engine delete(Engine entity);
}
