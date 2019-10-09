package com.example.ranyass.inventoryapp_stage1;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    // databaseHandler;
    private DatabaseHandler db;

    private FloatingActionButton butAddItem;
    private EditText ediIteamName;
    private EditText ediQuantity;
    private EditText ediPurveyorName;
    private EditText ediCost;
    private Spinner spiCategory;
    private ArrayList<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ediIteamName = (EditText) findViewById(R.id.ediIteamName);
        ediQuantity = (EditText) findViewById(R.id.ediQuantity);
        ediPurveyorName = (EditText) findViewById(R.id.ediPurveyorName);
        ediCost = (EditText) findViewById(R.id.ediCost);
        spiCategory = (Spinner) findViewById(R.id.spiCategory);

        String[] items = new String[]{"Smart phones", "Computers", "Video Games","Computer supplies"};
        ArrayAdapter<String> SpinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        spiCategory.setAdapter(SpinnerAdapter);


        butAddItem = (FloatingActionButton) findViewById(R.id.butAddItem);
        butAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(ediIteamName.length()>0&ediQuantity.length()>0&ediPurveyorName.length()>0&ediCost.length()>0)
                {
                    list = new ArrayList<String>();
                    list.add(ediIteamName.getText().toString());
                    list.add(spiCategory.getSelectedItem().toString());
                    list.add(ediQuantity.getText().toString());
                    list.add(ediPurveyorName.getText().toString());
                    list.add(ediCost.getText().toString());

                    AddData(list);
                    ediIteamName.setText("");
                    ediQuantity.setText("");
                    ediPurveyorName.setText("");
                    ediCost.setText("");

                }
                else
                {
                    toastMessage("Please fill the blanks first.");
                }
            }
        });
    }

    public void AddData(ArrayList<String> newEntry)
    {
        db = new DatabaseHandler(this);
        db.addData(newEntry);
        toastMessage("New item is added.");
    }

    private void toastMessage(String Message)
    {
        Toast.makeText(this,Message,Toast.LENGTH_LONG).show();
    }

    // create an action bar button
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    // handle button activities
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent k = new Intent(this, ProductsActivity.class);
        startActivity(k);
        return super.onOptionsItemSelected(item);
    }
}
