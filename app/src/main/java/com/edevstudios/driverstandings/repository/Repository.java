package com.edevstudios.driverstandings.repository;

import java.util.Set;

/**
 * Created by Edmund.Simons on 2016/04/21.
 */
public interface Repository<V, ID>
{
    V findById(ID id);

    V save(V entity);

    V update(V entity);

    V delete(V entity);

    Set<V> findAll();

    int deleteAll();
}
