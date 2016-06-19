package com.edevstudios.driverstandings.services.Impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.edevstudios.driverstandings.conf.factory.util.App;
import com.edevstudios.driverstandings.domain.Sponsor;
import com.edevstudios.driverstandings.repository.domain.Impl.SponsorRepositoryImpl;
import com.edevstudios.driverstandings.repository.domain.SponsorRepository;
import com.edevstudios.driverstandings.services.SponsorService;

import java.util.Set;

/**
 * Created by Edmund.Simons on 2016/06/19.
 */
public class SponsorServiceImpl extends Service implements SponsorService
{
    private final SponsorRepository sponsorRepository;
    private final IBinder localBinder = new SponsorServiceLocalBinder();
    private static SponsorServiceImpl service = null;

    public SponsorServiceImpl()
    {
        sponsorRepository = new SponsorRepositoryImpl(App.getAppContext());
    }

    public static SponsorServiceImpl getInstance() {
        if (service == null)
            service = new SponsorServiceImpl();
        return service;
    }

    public class SponsorServiceLocalBinder extends Binder
    {
        public SponsorServiceImpl getService()
        {
            return SponsorServiceImpl.this;
        }
    }


    @Override
    public Sponsor findById(Long id) {
        return sponsorRepository.findById(id);
    }

    @Override
    public Set<Sponsor> findAll() {
        return sponsorRepository.findAll();
    }

    @Override
    public Sponsor save(Sponsor entity) {
        return sponsorRepository.save(entity);
    }

    @Override
    public Sponsor update(Sponsor entity) {
        return sponsorRepository.update(entity);
    }

    @Override
    public Sponsor delete(Sponsor entity) {
        return sponsorRepository.delete(entity);
    }

    @Override
    public IBinder onBind(Intent intent)
    {
        return localBinder;
    }
}
