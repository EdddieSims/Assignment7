package com.edevstudios.driverstandings;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.edevstudios.driverstandings.conf.factory.CarFactory;
import com.edevstudios.driverstandings.domain.Car;
import com.edevstudios.driverstandings.repository.domain.Impl.CarRepositoryImpl;

public class Home extends AppCompatActivity
{
    private Button btnGoToCars;
    private Button btnGoToDrivers;
    private Button btnGoToEngine;
    private Button btnGoToRaces;
    private Button btnGoToLeader;
    private Button btnGoToSponsor;
    private Button btnGoToStandings;
    private Button btnGoToTrack;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        createButtons();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void createButtons()
    {
        btnGoToCars         = (Button)findViewById(R.id.btnGoToCars);
        btnGoToDrivers      = (Button)findViewById(R.id.btnGoToDriver);
        btnGoToEngine       = (Button)findViewById(R.id.btnGoToEngine);
        btnGoToRaces        = (Button)findViewById(R.id.btnGoToRaces);
        btnGoToLeader       = (Button)findViewById(R.id.btnGoToLeader);
        btnGoToSponsor      = (Button)findViewById(R.id.btnGoToSponsor);
        btnGoToStandings    = (Button)findViewById(R.id.btnGoToStandings);
        btnGoToTrack        = (Button)findViewById(R.id.btnGoToTrack);
    }

    public void clickCar(View view)
    {
        Intent i = new Intent(view.getContext(), CarActivity.class);
        startActivity(i);
    }

    public void clickDriver(View view)
    {
        Intent i = new Intent(view.getContext(), DriverActivity.class);
        startActivity(i);
    }

    public void clickEngine(View view)
    {
        Intent i = new Intent(view.getContext(), EngineActivity.class);
        startActivity(i);
    }

    public void clickRaces(View view)
    {
        Intent i = new Intent(view.getContext(), RacesActivity.class);
        startActivity(i);
    }

    public void clickLeaderboard(View view)
    {
        Intent i = new Intent(view.getContext(), LeaderboardActivity.class);
        startActivity(i);
    }

    public void clickSponsor(View view)
    {
        Intent i = new Intent(view.getContext(), SponsorActivity.class);
        startActivity(i);
    }

    public void clickStandings(View view)
    {
        Intent i = new Intent(view.getContext(), StandingsActivity.class);
        startActivity(i);
    }

    public void clickTrack(View view)
    {
        Intent i = new Intent(view.getContext(), TrackActivity.class);
        startActivity(i);
    }
}
