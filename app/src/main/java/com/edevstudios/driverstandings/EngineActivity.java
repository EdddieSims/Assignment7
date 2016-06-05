package com.edevstudios.driverstandings;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.edevstudios.driverstandings.conf.factory.EngineFactory;
import com.edevstudios.driverstandings.domain.Engine;
import com.edevstudios.driverstandings.repository.domain.Impl.EngineRepositoryImpl;

/**
 * Created by Edmund.Simons on 2016/06/03.
 */
public class EngineActivity  extends AppCompatActivity
{
    private EditText txtEngineBrand;
    private EditText txtEngineModel;
    private EditText txtEnginePistons;
    private EditText txtEnginePower;

    private Button btnAddEngine;
    private Button btnAllEngines;

    Engine engine;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_engine);

        instantiateWidgets();
    }

    public void clickAddEngine(View view)
    {
        EngineRepositoryImpl engineImpl = new EngineRepositoryImpl(this);
        String brand = txtEngineBrand.getText().toString();
        String model = txtEngineModel.getText().toString();
        String pistons = txtEnginePistons.getText().toString();
        String power = txtEnginePower.getText().toString();

        if(brand.matches("") || model.matches("") || pistons.matches("") || power.matches(""))
        {
            Toast.makeText(this,  "Please complete all fields", Toast.LENGTH_LONG).show();
        }
        else
        {
            int numPistons = Integer.parseInt(pistons);
            double powerOut = Double.parseDouble(power);
            //engine = EngineFactory.createEngine("Mercedes Benz", "AMG-12/6", 8, 4.5);
            engine = EngineFactory.createEngine(brand, model, numPistons, powerOut);
            engineImpl.save(engine);
            Toast.makeText(this,  "Successfully added", Toast.LENGTH_LONG).show();

            txtEngineBrand.setText("");
            txtEngineModel.setText("");
            txtEnginePistons.setText("");
            txtEnginePower.setText("");
        }
    }

    public void clickViewEngines(View view)
    {
        Intent i = new Intent(view.getContext(), AllEnginesActivity.class);
        startActivity(i);
    }

    public void instantiateWidgets()
    {
        txtEngineBrand = (EditText)findViewById(R.id.txtEngineBrand);
        txtEngineModel = (EditText)findViewById(R.id.txtEngineModel);
        txtEnginePistons = (EditText)findViewById(R.id.txtEnginePistons);
        txtEnginePower = (EditText)findViewById(R.id.txtEnginePower);

        btnAddEngine = (Button) findViewById(R.id.btnAddEngine);
        btnAllEngines = (Button) findViewById(R.id.btnAllEngines);
    }
}
