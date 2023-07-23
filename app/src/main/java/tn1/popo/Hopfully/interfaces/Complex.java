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

public class Complex extends AppCompatActivity {
    MaterialButton back,home,derive;
    RadioGroup radioGroup;
    RadioButton polar,ops;
    TextView title ;
    int checkedButton = -1 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complex);
        back = findViewById(R.id.bcomp);
        home =findViewById(R.id.hcomp);
        derive = findViewById(R.id.subcomp);
        radioGroup = findViewById(R.id.rdcomp);
        polar = findViewById(R.id.rdcomp1);
        ops = findViewById(R.id.rdcomp2);
        title = findViewById(R.id.tcomp);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        derive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                checkedButton = radioGroup.getCheckedRadioButtonId();
                if(checkedButton == R.id.rdcomp1) {
                    intent = new Intent(getApplicationContext(),Polar.class);
                    startActivity(intent);
                    finish();
                }
                else if(checkedButton == R.id.rdcomp2) {
                    intent = new Intent(getApplicationContext(),ComplexOps.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(getApplicationContext(),"please choose the type of the operation",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}