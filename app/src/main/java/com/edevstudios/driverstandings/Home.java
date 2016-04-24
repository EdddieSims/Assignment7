package com.edevstudios.driverstandings;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
        btnGoToCars = (Button)findViewById(R.id.btnGoToCars);

        btnGoToCars.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                newCar(v);
            }
        });
    }

    public void newCar(View view)
    {
        CarRepositoryImpl carImple = new CarRepositoryImpl(this);
        String make = "Mercedes Benz";
        String model = "Indie";
        int year = 2014;

        Car formulaOneCar = CarFactory.createCar(make, model, year);
        carImple.save(formulaOneCar);
        Long id = 1L;
        Car returnCar = carImple.findById(id);

        String carMake = returnCar.getMake();
        Toast.makeText(Home.this, carMake, Toast.LENGTH_LONG).show();
    }
}
