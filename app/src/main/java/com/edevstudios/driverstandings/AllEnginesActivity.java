package com.edevstudios.driverstandings;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.edevstudios.driverstandings.domain.Driver;
import com.edevstudios.driverstandings.domain.Engine;
import com.edevstudios.driverstandings.repository.domain.Impl.EngineRepositoryImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Edmund.Simons on 2016/06/04.
 */
public class AllEnginesActivity extends AppCompatActivity
{
    private ListView listAllEngines;

    ArrayList<String> aListOfEngines;
    Set<Engine> listOfEngines;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_engines);

        populateList();
    }

    public void populateList()
    {
        listAllEngines = (ListView)findViewById(R.id.listAllEngines);
        EngineRepositoryImpl engineImpl = new EngineRepositoryImpl(this);

        aListOfEngines = new ArrayList<>();
        listOfEngines = new HashSet<>();
        listOfEngines = engineImpl.findAll();

        for(Engine engine: listOfEngines)
        {
            aListOfEngines.add(engine.getBrand() + " " + engine.getModel());
        }

        ArrayAdapter<String> dbEngines = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, aListOfEngines);
        listAllEngines.setAdapter(dbEngines);

    }
}
