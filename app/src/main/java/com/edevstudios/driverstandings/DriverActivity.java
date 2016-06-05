package com.edevstudios.driverstandings;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.edevstudios.driverstandings.conf.factory.DriverFactory;
import com.edevstudios.driverstandings.domain.Driver;
import com.edevstudios.driverstandings.repository.domain.Impl.DriverRepositoryImpl;

import java.util.HashMap;

/**
 * Created by Edmund.Simons on 2016/06/03.
 */
public class DriverActivity extends AppCompatActivity
{
    private EditText txtDriverName;
    private EditText txtDriverSurname;
    private EditText txtDriverCountry;
    private EditText txtDriverTeam;

    private Button btnAddDriver;
    private Button btnViewDrivers;

    Driver driver;
    HashMap<String, String> raceDriver;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver);

        initialiseWidgets();
    }

    public void clickAddDriver(View view)
    {
        DriverRepositoryImpl driverImpl = new DriverRepositoryImpl(this);

        String name = txtDriverName.getText().toString();
        String surname = txtDriverSurname.getText().toString();
        String country = txtDriverCountry.getText().toString();
        String team = txtDriverTeam.getText().toString();

        if(name.matches("") || surname.matches("") || country.matches("") || team.matches(""))
        {
            Toast.makeText(this,  "Please complete all fields", Toast.LENGTH_LONG).show();
        }
        else
        {
            raceDriver = new HashMap<String, String>();
            raceDriver.put("name",name);
            raceDriver.put("surname",surname);
            raceDriver.put("country",country);
            raceDriver.put("team",team);

            driver = DriverFactory.createDriver(raceDriver, 0, 0, 0);

            driverImpl.save(driver);
            Toast.makeText(this,  "Successfully added", Toast.LENGTH_LONG).show();

            txtDriverName.setText("");
            txtDriverSurname.setText("");
            txtDriverCountry.setText("");
            txtDriverTeam.setText("");
        }
    }

    public void clickViewDrivers(View view)
    {
        Intent i = new Intent(view.getContext(), AllDriverActivity.class);
        startActivity(i);
    }

    public void initialiseWidgets()
    {
        txtDriverName = (EditText) findViewById(R.id.txtDriverName);
        txtDriverSurname = (EditText)findViewById(R.id.txtDriverSurname);
        txtDriverCountry = (EditText)findViewById(R.id.txtDriverCountry);
        txtDriverTeam = (EditText)findViewById(R.id.txtDriverTeam);

        btnAddDriver = (Button)findViewById(R.id.btnAddDriver);
        btnViewDrivers = (Button)findViewById(R.id.btnViewDrivers);
    }
}
