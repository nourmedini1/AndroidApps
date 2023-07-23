package tn1.popo.Hopfully.interfaces;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
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

public class integral extends AppCompatActivity {
    MaterialButton backint,homeint,submitint;
    RadioGroup radioGroup;
    RadioButton primitive,definite;
    TextView titleint ;
    int checkedButton = -1 ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_integral);
        backint = findViewById(R.id.backint);
        homeint =findViewById(R.id.homeint);
        submitint = findViewById(R.id.submitint);
        radioGroup = findViewById(R.id.rdint);
        primitive = findViewById(R.id.rdint1);
        definite = findViewById(R.id.rdint2);
        titleint = findViewById(R.id.titleint);
        backint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        homeint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        submitint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                checkedButton = radioGroup.getCheckedRadioButtonId();
                if(checkedButton == R.id.rdint1) {
                    intent = new Intent(getApplicationContext(),Primitive.class);
                    startActivity(intent);
                    finish();
                }
                else if(checkedButton == R.id.rdint2) {
                    intent = new Intent(getApplicationContext(),Definite.class);
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