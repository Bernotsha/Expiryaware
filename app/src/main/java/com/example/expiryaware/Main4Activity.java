package com.example.expiryaware;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;



public class Main4Activity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    TextView tv;
    String message="Click the correct Expiry date";
    TextView text;
    Button b1;
    Button b2;
    String s3,s1,s2;
    DatePickerDialog.OnDateSetListener setListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        tv=(TextView)findViewById(R.id.text3);
        text=(TextView)findViewById(R.id.text4);
        tv.setText(getIntent().getStringExtra("result"));
        b1=(Button)findViewById(R.id.button5);
        b2=(Button)findViewById(R.id.button6);
        Toast.makeText(Main4Activity.this,message,Toast.LENGTH_LONG).show();
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Main4Activity.this,Main5Activity.class);
                String s= text.getText().toString();
                if(s.isEmpty()==false) {

                    i.putExtra("value2", s);
                    String st = tv.getText().toString();
                    i.putExtra("result", st);
                    i.putExtra("day", s3);
                    i.putExtra("month", s2);
                    i.putExtra("y", s1);
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(Main4Activity.this,"Choose the expiry date",Toast.LENGTH_LONG).show();
                }
            }
        });
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();

            }
        });

    }
    public void showDatePickerDialog(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this, this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)


        );
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        month=month+1;

        String date=dayOfMonth+"/"+month+"/"+year;
         s1 = Integer.toString(year);
        s2 = Integer.toString(month);
        s3 = Integer.toString(dayOfMonth);



        text.setText(date);
    }
}
