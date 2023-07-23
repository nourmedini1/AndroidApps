package tn1.popo.Hopfully.interfaces;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import tn1.popo.Hopfully.R;

public class Definite extends AppCompatActivity {
    MaterialButton backdefint,homedefint,submitdefint;
    RadioGroup radioGroup;
    RadioButton singledef,doubldef,tripledef;
    TextView titledefint ;
    int checkedButton = -1 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_definite);
        backdefint = findViewById(R.id.backdefint);
        homedefint =findViewById(R.id.homedefint);
        submitdefint = findViewById(R.id.submitdefint);
        radioGroup = findViewById(R.id.rddefint);
        singledef = findViewById(R.id.rddefint1);
        doubldef = findViewById(R.id.rddefint2);
        tripledef = findViewById(R.id.rddefint3);
        titledefint = findViewById(R.id.titledefint);
        backdefint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(getApplicationContext(),integral.class);
                startActivity(intent);
                finish();
            }
        });
        homedefint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        submitdefint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                checkedButton = radioGroup.getCheckedRadioButtonId();
                if(checkedButton == R.id.rddefint1) {
                    intent = new Intent(getApplicationContext(),SingleIntegral.class);
                    startActivity(intent);
                    finish();
                }
                else if(checkedButton == R.id.rddefint2) {
                    intent = new Intent(getApplicationContext(),DoubleIntegral.class);
                    startActivity(intent);
                    finish();
                }
                else if(checkedButton == R.id.rddefint3) {
                    intent = new Intent(getApplicationContext(),TripleIntegral.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(getApplicationContext(),"please choose the type of the integral",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}