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

import com.edevstudios.driverstandings.conf.factory.CarFactory;
import com.edevstudios.driverstandings.domain.Car;
import com.edevstudios.driverstandings.repository.domain.Impl.CarRepositoryImpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Edmund.Simons on 2016/05/30.
 */
public class CarActivity extends AppCompatActivity
{
    private EditText carMake;
    private EditText carModel;
    private Spinner carYear;
    private Button addCar;
    Car formulaOneCar;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);

        initialiseAll();
    }

    public void initialiseAll()
    {
        addCar = (Button)findViewById(R.id.btnAdCarToDB);
        carMake = (EditText)findViewById(R.id.txtCarMake);
        carModel = (EditText)findViewById(R.id.txtCarModel);
        carYear = (Spinner)findViewById(R.id.spinCarYear);

        final List<String> list=new ArrayList<String>();
        list.add("2012");
        list.add("2013");
        list.add("2014");
        list.add("2015");
        list.add("2016");

        ArrayAdapter<String> adp1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        adp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        carYear.setAdapter(adp1);
    }

    public void addCar(View view)
    {
        newCar(view);
    }

    public void newCar(View view)
    {
        CarRepositoryImpl carImple = new CarRepositoryImpl(this);
        String make = carMake.getText().toString();
        String model = carModel.getText().toString();
        String sYear = carYear.getSelectedItem().toString();
        int year = Integer.parseInt(sYear);

        if(make.matches("") || model.matches(""))
        {
            Toast.makeText(this, "Please complete all fields", Toast.LENGTH_LONG).show();
        }
        else
        {
            formulaOneCar = CarFactory.createCar(make, model, year);
            carImple.save(formulaOneCar);

            Toast.makeText(this, make + " " + model + " Successfully added", Toast.LENGTH_LONG).show();

            carMake.setText("");
            carModel.setText("");
            carYear.setSelection(0);
        }

    }

    public void showAll(View view)
    {
        Intent i = new Intent(this, AllCarsActivity.class);
        startActivity(i);
    }
}
