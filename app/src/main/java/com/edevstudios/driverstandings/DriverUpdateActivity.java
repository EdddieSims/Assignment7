package com.edevstudios.driverstandings;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.edevstudios.driverstandings.conf.factory.DriverFactory;
import com.edevstudios.driverstandings.conf.factory.util.App;
import com.edevstudios.driverstandings.domain.Driver;
import com.edevstudios.driverstandings.repository.domain.Impl.DriverRepositoryImpl;
import com.edevstudios.driverstandings.services.Impl.DriverServiceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Edmund.Simons on 2016/06/06.
 */
public class DriverUpdateActivity extends AppCompatActivity
{
    private DriverServiceImpl driverServiceImpl;
    private boolean isBound = false;

    private Spinner spinDriver;
    private EditText txtUpdateDriverName;
    private EditText txtUpdateDriverSurname;
    private EditText txtUpdateDriverCountry;
    private EditText txtUpdateDriverTeam;

    private Button btnUpdateAddDriver;
    private Button btnViewDrivers;
    private Button btnDeleteUser;

    Driver driver;
    Driver updatedDriver;
    HashMap<String, String> raceDriver;
    ArrayList<Long> driverID = new ArrayList<>();
    int tempID = 0;
    Long dID;

    DriverRepositoryImpl driverImpl;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_drivers);

        Intent intent = new Intent(this, DriverServiceImpl.class);
        App.context = this.getApplicationContext();
        driverServiceImpl = DriverServiceImpl.getInstance();
        bindService(intent, connection, Context.BIND_AUTO_CREATE);

        initialiseWidgets();
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            DriverServiceImpl.DriverServiceLocalBinder binder
                    = (DriverServiceImpl.DriverServiceLocalBinder)service;
            driverServiceImpl = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };

    public void clickUpdateDriver(View view)
    {
        String name = txtUpdateDriverName.getText().toString();
        String surname = txtUpdateDriverSurname.getText().toString();
        String country = txtUpdateDriverCountry.getText().toString();
        String team = txtUpdateDriverTeam.getText().toString();

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

            updatedDriver = new Driver.Builder(name).copy(driver)
                    .surname(surname)
                    .country(country)
                    .team(team)
                    .build();
            driverImpl.update(updatedDriver);
            Toast.makeText(this,  "Update Successful", Toast.LENGTH_LONG).show();

            txtUpdateDriverName.setText("");
            txtUpdateDriverSurname.setText("");
            txtUpdateDriverCountry.setText("");
            txtUpdateDriverTeam.setText("");
        }
    }

    public void clickSelectDriver(View view)
    {
        String driverName;
        if(spinDriver != null && spinDriver.getSelectedItem() !=null)
        {
            driverName = spinDriver.getSelectedItem().toString();
            tempID = spinDriver.getSelectedItemPosition();
        }
        else
        {
            driverName = "";
        }

        if(driverName.matches(""))
        {
            Toast.makeText(this, "There are no drivers in the database", Toast.LENGTH_LONG).show();
        }
        else
        {
            dID = driverID.get(tempID);
            driver = driverServiceImpl.findById(dID);

            txtUpdateDriverName.setText(driver.getName());
            txtUpdateDriverSurname.setText(driver.getSurname());
            txtUpdateDriverCountry.setText(driver.getCountry());
            txtUpdateDriverTeam.setText(driver.getTeam());
        }
    }

    public void clickDeleteDriver(View view)
    {
        String name = txtUpdateDriverName.getText().toString();
        String surname = txtUpdateDriverSurname.getText().toString();
        String country = txtUpdateDriverCountry.getText().toString();
        String team = txtUpdateDriverTeam.getText().toString();

        if(name.matches("") || surname.matches("") || country.matches("") || team.matches(""))
        {
            Toast.makeText(this, "Please complete all fields", Toast.LENGTH_LONG).show();
        }
        else if(name.matches("") && surname.matches("") && country.matches("") && team.matches(""))
        {
            Toast.makeText(this, "First select a Driver before deleting", Toast.LENGTH_LONG).show();
        }
        else
        {
            String delName = driver.getName() + " " + driver.getSurname();
            driverServiceImpl.delete(driver);

            Toast.makeText(this, delName + " Successfully deleted", Toast.LENGTH_LONG).show();

            txtUpdateDriverName.setText("");
            txtUpdateDriverSurname.setText("");
            txtUpdateDriverCountry.setText("");
            txtUpdateDriverTeam.setText("");

            populateSpinner();
        }
    }

    public void initialiseWidgets()
    {
        spinDriver = (Spinner)findViewById(R.id.spinDriverSelect);
        txtUpdateDriverName = (EditText) findViewById(R.id.txtUpdateDriverName);
        txtUpdateDriverSurname = (EditText)findViewById(R.id.txtUpdateDriverSurname);
        txtUpdateDriverCountry = (EditText)findViewById(R.id.txtUpdateDriverCountry);
        txtUpdateDriverTeam = (EditText)findViewById(R.id.txtUpdateDriverTeam);

        btnUpdateAddDriver = (Button)findViewById(R.id.btnUpdateAddDriver);
        btnViewDrivers = (Button)findViewById(R.id.btnViewDrivers);
        btnDeleteUser = (Button)findViewById(R.id.btnDeleteUser);

        populateSpinner();
    }

    public void populateSpinner()
    {
        driverID.clear();
        driverImpl = new DriverRepositoryImpl(this);

        ArrayList<String> aListOfDrivers = new ArrayList<>();
        Set<Driver> listOfDrivers = new HashSet<>();
        listOfDrivers = driverServiceImpl.findAll();

        for(Driver driver: listOfDrivers)
        {
            aListOfDrivers.add(driver.getName());
            driverID.add(driver.getId());
        }

        ArrayAdapter<String> adp1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, aListOfDrivers);
        adp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinDriver.setAdapter(adp1);
    }
}
