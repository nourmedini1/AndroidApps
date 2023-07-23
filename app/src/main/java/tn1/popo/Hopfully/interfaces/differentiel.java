package tn1.popo.Hopfully.interfaces;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import tn1.popo.Hopfully.R;

public class differentiel extends AppCompatActivity {
    MaterialButton backode,homeode,submitode;
    RadioGroup radioGroup;
    RadioButton first,second,third;
    TextView titleode ;
    int checkedButton = -1 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_differentiel);
        backode = findViewById(R.id.backode);
        homeode =findViewById(R.id.homeode);
        submitode = findViewById(R.id.submitode);
        radioGroup = findViewById(R.id.rdode);
        first = findViewById(R.id.rdode1);
        second = findViewById(R.id.rdode2);
        third = findViewById(R.id.rdode3);
        titleode = findViewById(R.id.titleode);
        backode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        homeode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        submitode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                checkedButton = radioGroup.getCheckedRadioButtonId();
                if(checkedButton == R.id.rdode1) {
                    intent = new Intent(getApplicationContext(),FirstOrder.class);
                    startActivity(intent);
                    finish();
                }
                else if(checkedButton == R.id.rdode2) {
                    intent = new Intent(getApplicationContext(),SecondOrder.class);
                    startActivity(intent);
                    finish();
                }
                else if(checkedButton == R.id.rdode3) {
                    intent = new Intent(getApplicationContext(),ThirdOrder.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(getApplicationContext(),"please choose the type of the Ode",Toast.LENGTH_SHORT).show();
                }

            }
        });

}}