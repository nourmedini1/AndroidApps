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

public class Primitive extends AppCompatActivity {
    MaterialButton backprim,homeprim,submitprim;
    RadioGroup radioGroup;
    RadioButton single,doubl,triple;
    TextView titleprim ;
    int checkedButton = -1 ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primitive);
        backprim = findViewById(R.id.backprim);
        homeprim =findViewById(R.id.homeprim);
        submitprim = findViewById(R.id.submitprim);
        radioGroup = findViewById(R.id.rdprim);
        single = findViewById(R.id.rdprim1);
        doubl = findViewById(R.id.rdprim2);
        triple = findViewById(R.id.rdprim3);
        titleprim = findViewById(R.id.titleprim);
        backprim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(getApplicationContext(),integral.class);
                startActivity(intent);
                finish();
            }
        });
        homeprim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        submitprim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                checkedButton = radioGroup.getCheckedRadioButtonId();
                if(checkedButton == R.id.rdprim1) {
                    intent = new Intent(getApplicationContext(),SinglePrimitive.class);
                    startActivity(intent);
                    finish();
                }
                else if(checkedButton == R.id.rdprim2) {
                    intent = new Intent(getApplicationContext(),DoublePrimitive.class);
                    startActivity(intent);
                    finish();
                }
                else if(checkedButton == R.id.rdprim3) {
                    intent = new Intent(getApplicationContext(),TriplePrimitive.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(getApplicationContext(),"please choose the type of the primitive",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}