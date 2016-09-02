package com.edevstudios.driverstandings;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.edevstudios.driverstandings.domain.Car;
import com.edevstudios.driverstandings.domain.Driver;
import com.edevstudios.driverstandings.repository.domain.Impl.DriverRepositoryImpl;
import com.edevstudios.driverstandings.services.Impl.DriverServiceImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Edmund.Simons on 2016/06/03.
 */
public class AllDriverActivity extends AppCompatActivity
{
    ListView listAllDrivers;
    ArrayList<String> aListOfDrivers;
    Set<Driver> listOfDrivers;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_drivers);

        populateList();
    }

    public void populateList()
    {
        listAllDrivers = (ListView)findViewById(R.id.listAllDrivers);
        DriverRepositoryImpl driverImpl = new DriverRepositoryImpl(this);

        aListOfDrivers = new ArrayList<>();
        listOfDrivers = new HashSet<>();

        Thread allDriverThread = new Thread(new Runnable() {
            @Override
            public void run()
            {
                DriverServiceImpl service = new DriverServiceImpl();
                listOfDrivers = service.findAll();

                for(Driver driver: listOfDrivers)
                {
                    //Toast.makeText(this, car.getId().toString(), Toast.LENGTH_LONG).show();
                    aListOfDrivers.add(driver.getName());
                }
                //Toast.makeText(this, "", Toast.LENGTH_LONG).show();
            }
        });

        allDriverThread.start();

        try {

            allDriverThread.join();
        } catch (Exception ex) {

            ex.printStackTrace();
        }
        ArrayAdapter<String> dbDrivers = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, aListOfDrivers);
        listAllDrivers.setAdapter(dbDrivers);
    }
}
