package com.edevstudios.driverstandings.services;

import com.edevstudios.driverstandings.domain.Driver;

/**
 * Created by Edmund.Simons on 2016/05/06.
 */
public interface UpdateService
{
    String driverExists();
    String updateDriverWinnings(Long id, int points, int behind, int wins);
    boolean isUpdated(Long id);
}
