package com.edevstudios.driverstandings.repositories;

import java.util.Set;

/**
 * Created by Edmund.Simons on 2016/09/02.
 */
public interface RestAPI<S, ID>
{
    S get(ID id);

    String post(S entity);

    String put(S entity);

    String delete(S entity);

    Set<S> getAll();
}
