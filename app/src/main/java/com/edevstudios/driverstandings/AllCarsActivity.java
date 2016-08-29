package com.edevstudios.driverstandings;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.edevstudios.driverstandings.domain.Car;
import com.edevstudios.driverstandings.repository.domain.Impl.CarRepositoryImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Edmund.Simons on 2016/05/30.
 */
public class AllCarsActivity extends AppCompatActivity
{
    private ListView allCars;
    ArrayList<String> aListOfCars;
    Set<Car> listOfCars;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actitvity_all_cars);
        allCars = (ListView)findViewById(R.id.listAllCars);

        populateList();
    }

    public void populateList()
    {
        CarRepositoryImpl carImple = new CarRepositoryImpl(this);
        aListOfCars = new ArrayList<>();
        listOfCars = new HashSet<>();
        listOfCars = carImple.findAll();

        for(Car car: listOfCars)
        {
            //Toast.makeText(this, car.getId().toString(), Toast.LENGTH_LONG).show();
            aListOfCars.add(car.getMake());
        }
        //Toast.makeText(this, "", Toast.LENGTH_LONG).show();
        ArrayAdapter<String> dbCars = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, aListOfCars);
        allCars.setAdapter(dbCars);
    }
}
