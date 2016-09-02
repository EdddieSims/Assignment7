package com.edevstudios.driverstandings;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.edevstudios.driverstandings.conf.factory.DriverFactory;
import com.edevstudios.driverstandings.conf.factory.util.App;
import com.edevstudios.driverstandings.domain.Driver;
import com.edevstudios.driverstandings.repository.domain.Impl.DriverRepositoryImpl;
import com.edevstudios.driverstandings.services.DriverService;
import com.edevstudios.driverstandings.services.Impl.DriverServiceImpl;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * Created by Edmund.Simons on 2016/06/03.
 */
public class DriverActivity extends AppCompatActivity
{
    private DriverServiceImpl driverServiceImpl;
    private boolean isBound = false;

    private EditText txtDriverName;
    private EditText txtDriverSurname;
    private EditText txtDriverCountry;
    private EditText txtDriverTeam;

    private Button btnAddDriver;
    private Button btnViewDrivers;
    private Button btnGoToUpdateDriver;

    Driver driver;
    HashMap<String, String> raceDriver;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver);

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
            //Driver newEntity = driverServiceImpl.save(driver);

            Thread driverThread = new Thread(new Runnable()
            {
                @Override
                public void run()
                {
                    DriverService service = new DriverServiceImpl();
                    service.save(driver);
                }
            });

            driverThread.start();
            try
            {
                driverThread.join();
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }

            //Toast.makeText(this, newEntity.getName() + " Successfully added", Toast.LENGTH_LONG).show();

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

    public void clickUpdateDriver(View view)
    {
        Intent i = new Intent(view.getContext(), DriverUpdateActivity.class);
        startActivity(i);
    }

    public void initialiseWidgets()
    {
        txtDriverName = (EditText) findViewById(R.id.txtDriverName);
        txtDriverSurname = (EditText)findViewById(R.id.txtUpdateDriverSurname);
        txtDriverCountry = (EditText)findViewById(R.id.txtUpdateDriverCountry);
        txtDriverTeam = (EditText)findViewById(R.id.txtUpdateDriverTeam);

        btnAddDriver = (Button)findViewById(R.id.btnUpdateAddDriver);
        btnViewDrivers = (Button)findViewById(R.id.btnViewDrivers);
    }
}
