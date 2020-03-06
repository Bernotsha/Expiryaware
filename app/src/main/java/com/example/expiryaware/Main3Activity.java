package com.example.expiryaware;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class Main3Activity extends AppCompatActivity {
   EditText resultText;
   String message="Tell your Product Name";
   Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        resultText=(EditText) findViewById(R.id.text1);
        b1=findViewById(R.id.button4);
        Toast.makeText(Main3Activity.this,message,Toast.LENGTH_LONG).show();

            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(Main3Activity.this, Main4Activity.class);
                    String value = resultText.getText().toString();
                    if(value.isEmpty()==false) {
                        i.putExtra("result", value);
                        startActivity(i);
                    }
                    else {
                        Intent m = new Intent(Main3Activity.this,Main6Activity.class);
                        startActivity(m);


                    }

                }
            });

    }
    public void onButtonClick(View v)
    {
        if(v.getId()== R.id.image1)
        {

            promptSpeechInput();
        }
    }
    public void promptSpeechInput()
    {
        Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        i.putExtra(RecognizerIntent.EXTRA_PROMPT,"product name");
        try {


            startActivityForResult(i, 100);
        }
        catch (ActivityNotFoundException a)
        {
            Toast.makeText(Main3Activity.this,"Sorry your device speech language",Toast.LENGTH_LONG).show();
        }
    }
    public void onActivityResult(int request_code,int result_code,Intent i)
    {
        super.onActivityResult(request_code,result_code,i);
        switch (request_code)
        {
            case 100:if(result_code==RESULT_OK && i!=null)
            {
                ArrayList<String> result =i.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                assert result != null;
                resultText.setText(result.get(0));
                break;

            }
            case 200:
            {
                break;
            }

        }
    }
}
