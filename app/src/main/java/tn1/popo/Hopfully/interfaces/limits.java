package tn1.popo.Hopfully.interfaces;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import tn1.popo.Hopfully.R;

public class limits extends AppCompatActivity {

    MaterialButton backlim,homelim,submitlim;
    RadioGroup radioGroup;
    RadioButton one,two,three;
    TextView titlelim ;
    int checkedButton = -1 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_limits);
        backlim = findViewById(R.id.backlim);
        homelim =findViewById(R.id.homelim);
        submitlim = findViewById(R.id.sublim);
        radioGroup = findViewById(R.id.rdlim);
        one = findViewById(R.id.rdlim1);
        two = findViewById(R.id.rdlim2);
        three = findViewById(R.id.rdlim3);
        titlelim = findViewById(R.id.titlelim);
        backlim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        homelim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        submitlim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                checkedButton = radioGroup.getCheckedRadioButtonId();
                if(checkedButton == R.id.rdlim1) {
                    intent = new Intent(getApplicationContext(),limitOne.class);
                    startActivity(intent);
                    finish();
                }
                else if(checkedButton == R.id.rdlim2) {
                    intent = new Intent(getApplicationContext(),limitTwo.class);
                    startActivity(intent);
                    finish();
                }
                else if(checkedButton == R.id.rdlim3) {
                    intent = new Intent(getApplicationContext(),limitThree.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(getApplicationContext(),"please choose the type of the limit",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}