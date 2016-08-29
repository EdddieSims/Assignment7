package com.edevstudios.driverstandings.repositories;

import java.util.List;
import java.util.Set;

/**
 * Created by Edmund.Simons on 2016/08/23.
 */
public interface RestAPI<S, ID>
{
    public S findById(ID id);

    public S save(S entity);

    public S update(S entity);

    public void delete(S entity);

    public Set<S> findAll();
}
