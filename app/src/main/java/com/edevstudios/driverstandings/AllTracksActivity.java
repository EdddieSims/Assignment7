package com.edevstudios.driverstandings;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.edevstudios.driverstandings.domain.Engine;
import com.edevstudios.driverstandings.domain.Track;
import com.edevstudios.driverstandings.repository.domain.Impl.TrackRepositoryImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Edmund.Simons on 2016/06/04.
 */
public class AllTracksActivity extends AppCompatActivity
{
    private ListView listAllTracks;

    ArrayList<String> aListOfTracks;
    Set<Track> listOfTracks;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_tracks);

        populateList();
    }

    public void populateList()
    {
        listAllTracks =(ListView)findViewById(R.id.listAllTracks);
        TrackRepositoryImpl engineImpl = new TrackRepositoryImpl(this);

        aListOfTracks = new ArrayList<>();
        listOfTracks = new HashSet<>();
        listOfTracks = engineImpl.findAll();

        for(Track track: listOfTracks)
        {
            aListOfTracks.add(track.getTrackName());
        }

        ArrayAdapter<String> dbEngines = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, aListOfTracks);
        listAllTracks.setAdapter(dbEngines);
    }
}
