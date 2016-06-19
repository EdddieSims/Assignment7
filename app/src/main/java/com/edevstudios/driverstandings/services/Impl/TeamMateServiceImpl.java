package com.edevstudios.driverstandings.services.Impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.edevstudios.driverstandings.conf.factory.util.App;
import com.edevstudios.driverstandings.domain.TeamMate;
import com.edevstudios.driverstandings.repository.domain.Impl.TeamMateRepositoryImpl;
import com.edevstudios.driverstandings.repository.domain.TeamMateRepository;
import com.edevstudios.driverstandings.services.TeamMateService;

import java.util.Set;

/**
 * Created by Edmund.Simons on 2016/06/19.
 */
public class TeamMateServiceImpl extends Service implements TeamMateService
{
    private final TeamMateRepository teamMateRepository;
    private final IBinder localBinder = new TeamMateServiceLocalBinder();
    private static TeamMateServiceImpl service = null;

    public TeamMateServiceImpl()
    {
        teamMateRepository = new TeamMateRepositoryImpl(App.getAppContext());
    }

    public static TeamMateServiceImpl getInstance() {
        if (service == null)
            service = new TeamMateServiceImpl();
        return service;
    }

    public class TeamMateServiceLocalBinder extends Binder
    {
        public TeamMateServiceImpl getService()
        {
            return TeamMateServiceImpl.this;
        }
    }


    @Override
    public TeamMate findById(Long id) {
        return teamMateRepository.findById(id);
    }

    @Override
    public Set<TeamMate> findAll() {
        return teamMateRepository.findAll();
    }

    @Override
    public TeamMate save(TeamMate entity) {
        return teamMateRepository.save(entity);
    }

    @Override
    public TeamMate update(TeamMate entity) {
        return teamMateRepository.update(entity);
    }

    @Override
    public TeamMate delete(TeamMate entity) {
        return teamMateRepository.delete(entity);
    }

    @Override
    public IBinder onBind(Intent intent)
    {
        return localBinder;
    }
}
