package com.edevstudios.driverstandings.services;

import java.util.Set;

/**
 * Created by Edmund.Simons on 2016/09/02.
 */
public interface Service<S, ID>
{
    public S findById(ID id);

    public S save(S entity);

    public S update(S entity);

    public S delete(S entity);

    public Set<S> findAll();
}
