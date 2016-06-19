package com.edevstudios.driverstandings.services.Impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.edevstudios.driverstandings.conf.factory.util.App;
import com.edevstudios.driverstandings.domain.Leaderboard;
import com.edevstudios.driverstandings.repository.domain.Impl.LeaderboardRepositoryImpl;
import com.edevstudios.driverstandings.repository.domain.LeaderboardRepository;
import com.edevstudios.driverstandings.services.LeaderboardService;

import java.util.Set;

/**
 * Created by Edmund.Simons on 2016/06/19.
 */
public class LeaderboardServiceImpl extends Service implements LeaderboardService
{
    private final LeaderboardRepository leaderboardRepository;
    private final IBinder localBinder = new LeaderboardServiceLocalBinder();
    private static LeaderboardServiceImpl service = null;

    public LeaderboardServiceImpl()
    {
        leaderboardRepository = new LeaderboardRepositoryImpl(App.getAppContext());
    }

    public static LeaderboardServiceImpl getInstance() {
        if (service == null)
            service = new LeaderboardServiceImpl();
        return service;
    }

    public class LeaderboardServiceLocalBinder extends Binder
    {
        public LeaderboardServiceImpl getService()
        {
            return LeaderboardServiceImpl.this;
        }
    }


    @Override
    public Leaderboard findById(Long id) {
        return leaderboardRepository.findById(id);
    }

    @Override
    public Set<Leaderboard> findAll() {
        return leaderboardRepository.findAll();
    }

    @Override
    public Leaderboard save(Leaderboard entity) {
        return leaderboardRepository.save(entity);
    }

    @Override
    public Leaderboard update(Leaderboard entity) {
        return leaderboardRepository.update(entity);
    }

    @Override
    public Leaderboard delete(Leaderboard entity) {
        return leaderboardRepository.delete(entity);
    }

    @Override
    public IBinder onBind(Intent intent)
    {
        return localBinder;
    }
}
