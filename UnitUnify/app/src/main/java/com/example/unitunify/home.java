package com.example.unitunify;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class home extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String text;
    double value=0;
    double ans=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Spinner spinner=findViewById(R.id.spinner);
        EditText input=findViewById(R.id.edInput);
        EditText output=findViewById(R.id.edOutput);
        Button convert=findViewById(R.id.convertButton);

        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.unit, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                value= Double.parseDouble(String.valueOf(input.getText()));
                try {
                    if(text.equals("centimeters to Meters")){
                        ans=value/100;
                        output.setText(String.valueOf(ans)+" m");
                    }
                    else if(text.equals("Meters to centimeters"))
                    {
                        ans=value*100;
                        output.setText(String.valueOf(ans)+" cm");
                    }
                    else if(text.equals("Meters to Kilometers"))
                    {
                        ans=value/1000;
                        output.setText(String.valueOf(ans)+" km");
                    }
                    else if(text.equals("Kilometers to Meters"))
                    {
                        ans=value*1000;
                        output.setText(String.valueOf(ans)+" m");
                    }
                    else if(text.equals("centimeters to Kilometers"))
                    {
                        ans=value/100000;
                        output.setText(String.valueOf(ans)+" km");
                    }
                    else if(text.equals("Kilometers to centimeters"))
                    {
                        ans=value*100000;
                        output.setText(String.valueOf(ans)+" cm");
                    }
                    else if(text.equals("Grams to Kilograms"))
                    {
                        ans=value/1000;
                        output.setText(String.valueOf(ans)+" kg");
                    }
                    else if(text.equals("Kilograms to Grams"))
                    {
                        ans=value*1000;
                        output.setText(String.valueOf(ans)+" g");
                    }
                    else if(text.equals("Rs(India) to Dollar(USA)"))
                    {
                        ans=value/83.15;
                        output.setText("$"+String.valueOf(ans));
                    }
                    else if(text.equals("Dollar(USA) to Rs(India)"))
                    {
                        ans=value*83.15;
                        output.setText("₹"+String.valueOf(ans));
                    }

                }
                catch (Exception e)
                {
                    Toast.makeText(home.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        text=adapterView.getItemAtPosition(i).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}