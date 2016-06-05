package com.edevstudios.driverstandings;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.edevstudios.driverstandings.conf.factory.TrackFactory;
import com.edevstudios.driverstandings.domain.Track;
import com.edevstudios.driverstandings.repository.domain.Impl.TrackRepositoryImpl;

import java.util.HashMap;

/**
 * Created by Edmund.Simons on 2016/06/03.
 */
public class TrackActivity extends AppCompatActivity
{
    private EditText txtTrackName;
    private EditText txtTrackCountry;
    private EditText txtNumOfTurns;
    private EditText txtTrackLength;

    private Button btnAddTrack;
    private Button btnViewTracks;

    Track track;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track);

        instantiateWidgets();
    }

    public void clickAddTrack(View view)
    {
        TrackRepositoryImpl trackImpl = new TrackRepositoryImpl(this);

        String name = txtTrackName.getText().toString();
        String country = txtTrackCountry.getText().toString();
        String turns = txtNumOfTurns.getText().toString();
        String length = txtTrackLength.getText().toString();

        if(name.matches("") || country.matches("") || turns.matches("") || length.matches(""))
        {
            Toast.makeText(this,  "Please complete all fields", Toast.LENGTH_LONG).show();
        }
        else
        {
            int turn = Integer.parseInt(turns);
            double distance = Double.parseDouble(length);

            track = TrackFactory.createTrack(country, name, turn, distance);

            trackImpl.save(track);
            Toast.makeText(this,  "Successfully added", Toast.LENGTH_LONG).show();

            txtTrackName.setText("");
            txtTrackCountry.setText("");
            txtNumOfTurns.setText("");
            txtTrackLength.setText("");
        }
    }

    public void clickAllTracks(View view)
    {
        Intent i = new Intent(view.getContext(), AllTracksActivity.class);
        startActivity(i);
    }

    public void instantiateWidgets()
    {
        txtTrackName        =(EditText)findViewById(R.id.txtTrackName);
        txtTrackCountry     =(EditText)findViewById(R.id.txtTrackCountry);
        txtNumOfTurns       =(EditText)findViewById(R.id.txtNumOfTurns);
        txtTrackLength      =(EditText)findViewById(R.id.txtTrackLength);

        btnAddTrack         =(Button)findViewById(R.id.btnAddTrack);
        btnViewTracks       =(Button)findViewById(R.id.btnViewTracks);
    }
}
