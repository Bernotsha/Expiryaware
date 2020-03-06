package com.example.expiryaware;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    DatabaseHelper myDb;
    Button b1,b2;

    String message="There is nothing permanent except you";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        myDb= new DatabaseHelper(this);

        b2=findViewById(R.id.button3);
        b1=findViewById(R.id.button2);
        Cursor res = myDb.getAllData();
        int i=res.getCount();
        for(int j=i;j>0;j--)
        {
            int m=10;
            boolean isUpdate = myDb.updateData(m,Integer.toString(j));

        }
        viewAll();
        Toast.makeText(Main2Activity.this,message,Toast.LENGTH_LONG).show();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(Main2Activity.this,Main3Activity.class);
                startActivity(intent);
            }
        });
    }
    public void viewAll(){
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myDb.getAllData();
                if(res.getCount()==0)
                {
                    showMessage("Error","Nothing found");

                    return;
                }
                StringBuffer buffer=new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("Id :"+res.getString(0)+"\n");
                    buffer.append("Product Name : "+res.getString(1)+"\n");
                    buffer.append("Expiry date : "+res.getString(2)+"\n");
                    buffer.append("Remaining days : "+res.getString(3)+"\n\n");
                }
                showMessage("Data",buffer.toString());
            }
        });
    }
    public void showMessage(String title,String Message)
    {
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}



