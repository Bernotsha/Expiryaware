package com.example.expiryaware;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;
import java.lang.String;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Main5Activity extends AppCompatActivity {
  DatabaseHelper myDb;
  TextView t1,t2,t3;
  Button b1,b2,b3;
  String i,j,k,m;
    private static final String CHANNEL_ID="simplifie coding";
    private static final String CHANNEL_NAME="simplifie coding";
    private static final String CHANNEL_DESC="simplifie coding Notification";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
        {

            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(CHANNEL_DESC);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);

        }
        myDb= new DatabaseHelper(this);
        t1=(TextView) findViewById(R.id.et1);
        t2=(TextView) findViewById(R.id.et2);
        t3=(TextView)findViewById(R.id.et3);
        b1=(Button)findViewById(R.id.bt);
        b2=(Button)findViewById(R.id.bt2);
        b3=(Button)findViewById(R.id.but);
        t1.setText(getIntent().getStringExtra("result"));
        t2.setText(getIntent().getStringExtra("value2"));
        i = getIntent().getStringExtra("day");
        j=getIntent().getStringExtra("month");
        k=getIntent().getStringExtra("y");
        int p=Integer.parseInt(i);
        int q=Integer.parseInt(j);
        int r = Integer.parseInt(k);
        Date today = new Date();
        q=q-1;
        Calendar my = Calendar.getInstance();
        my.set(r,q,p);
        Date nyd=my.getTime();
        if(nyd.getTime()>=today.getTime()) {
            long difference = (nyd.getTime() - today.getTime()) / 86400000;
            if(difference==1|| difference==0)
            {
                displayNotification();
            }
            t3.setText(Long.toString(difference));
        }
        else
        {

            Intent s = new Intent(Main5Activity.this,Main7Activity.class);
            startActivity(s);


        }
      /*  Cursor res = myDb.getAllData();
        int i=res.getCount();
        for(int j=i;j>0;j--)
        {
            Date now = new Date();
            long d = now.getTime();
            d=(d*24)/86400000;
            if(d%24==0)
            {


                int m=Integer.parseInt(res.getColumnName(3));
                 m=-1;
                boolean isUpdate = myDb.updateData(m,Integer.toString(j));

            }


        }*/
      b3.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent i = new Intent(Main5Activity.this,Main3Activity.class);
              startActivity(i);
          }
      });









            AddData();

        viewAll();

    }
    public void AddData()
    {
        if(t3.getText().toString().isEmpty()==false) {
            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean isInserted = myDb.insertData(t1.getText().toString(), t2.getText().toString(), t3.getText().toString());
                    if (isInserted = true) {
                        Toast.makeText(Main5Activity.this, "Data inserted", Toast.LENGTH_LONG).show();

                    } else {
                        Toast.makeText(Main5Activity.this, "Data is not inserted", Toast.LENGTH_LONG).show();

                    }
                }
            });
        }
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
    private void displayNotification(){
        NotificationCompat.Builder mBuilder =new NotificationCompat.Builder(this,CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_block_black_24dp)
                .setContentTitle("Expiry aware")
                .setContentText("Your one of the product is going to be expire check it out")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(1,mBuilder.build());

    }

}


