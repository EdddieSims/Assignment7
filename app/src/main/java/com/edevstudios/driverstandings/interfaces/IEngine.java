package com.edevstudios.driverstandings.interfaces;

/**
 * Created by Edmund.Simons on 2016/03/29.
 */
public interface IEngine
{
    Long getId();
    String getBrand();
    String getModel();
    int getNumOfPistons();
    double getPowerOutput();
}
