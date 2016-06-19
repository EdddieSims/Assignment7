package com.edevstudios.driverstandings.services.Impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.edevstudios.driverstandings.conf.factory.util.App;
import com.edevstudios.driverstandings.domain.Track;
import com.edevstudios.driverstandings.repository.domain.Impl.TrackRepositoryImpl;
import com.edevstudios.driverstandings.repository.domain.TrackRepository;
import com.edevstudios.driverstandings.services.TrackService;

import java.util.Set;

/**
 * Created by Edmund.Simons on 2016/06/19.
 */
public class TrackServiceImpl extends Service implements TrackService
{
    private final TrackRepository trackRepository;
    private final IBinder localBinder = new TrackServiceLocalBinder();
    private static TrackServiceImpl service = null;

    public TrackServiceImpl()
    {
        trackRepository = new TrackRepositoryImpl(App.getAppContext());
    }

    public static TrackServiceImpl getInstance() {
        if (service == null)
            service = new TrackServiceImpl();
        return service;
    }

    public class TrackServiceLocalBinder extends Binder
    {
        public TrackServiceImpl getService()
        {
            return TrackServiceImpl.this;
        }
    }


    @Override
    public Track findById(Long id) {
        return trackRepository.findById(id);
    }

    @Override
    public Set<Track> findAll() {
        return trackRepository.findAll();
    }

    @Override
    public Track save(Track entity) {
        return trackRepository.save(entity);
    }

    @Override
    public Track update(Track entity) {
        return trackRepository.update(entity);
    }

    @Override
    public Track delete(Track entity) {
        return trackRepository.delete(entity);
    }

    @Override
    public IBinder onBind(Intent intent)
    {
        return localBinder;
    }
}
