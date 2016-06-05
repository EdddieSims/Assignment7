package com.edevstudios.driverstandings;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.edevstudios.driverstandings.conf.factory.RaceFactory;
import com.edevstudios.driverstandings.domain.Race;
import com.edevstudios.driverstandings.domain.Track;
import com.edevstudios.driverstandings.repository.domain.Impl.RaceRepositoryImpl;
import com.edevstudios.driverstandings.repository.domain.Impl.TrackRepositoryImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Edmund.Simons on 2016/06/04.
 */
public class RacesActivity extends AppCompatActivity
{
    private Spinner spinTracks;
    private EditText txtLaps;

    private Button btnCreateRace;
    private Button btnViewRaces;

    Race race;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_races);

        instantiateWidgets();
    }

    public void clickNewRace(View view)
    {
        RaceRepositoryImpl raceImpl = new RaceRepositoryImpl(this);
        String trackName;
        if(spinTracks != null && spinTracks.getSelectedItem() !=null)
        {
            trackName = spinTracks.getSelectedItem().toString();
        }
        else
        {
            trackName = "Spa";
        }

        String laps = txtLaps.getText().toString();

        if(trackName.matches("") || laps.matches(""))
        {
            Toast.makeText(this, "Please complete all fields", Toast.LENGTH_LONG).show();
        }
        else
        {
            int lap = Integer.parseInt(laps);
            race = RaceFactory.createRace(trackName, lap, 1L);
            raceImpl.save(race);

            Toast.makeText(this, "Race Successfully set up", Toast.LENGTH_LONG).show();
        }
        //Toast.makeText(this, trackName, Toast.LENGTH_LONG).show();
    }

    public void clickViewRaces(View view)
    {
        Intent i = new Intent(view.getContext(), AllEnginesActivity.class);
        startActivity(i);
    }

    public void instantiateWidgets()
    {
        spinTracks      = (Spinner)findViewById(R.id.spinTracks);
        txtLaps         =(EditText)findViewById(R.id.txtLaps);

        btnCreateRace   =(Button)findViewById(R.id.btnCreateRace);
        btnViewRaces    =(Button)findViewById(R.id.btnViewRaces);

        populateSpinner();
    }

    public void populateSpinner()
    {
        TrackRepositoryImpl trackImpl = new TrackRepositoryImpl(this);

        ArrayList<String> aListOfTracks = new ArrayList<>();
        Set<Track> listOfTracks = new HashSet<>();
        listOfTracks = trackImpl.findAll();

        for(Track track: listOfTracks)
        {
            aListOfTracks.add(track.getTrackName());
        }

        ArrayAdapter<String> adp1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, aListOfTracks);
        adp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinTracks.setAdapter(adp1);
    }
}
