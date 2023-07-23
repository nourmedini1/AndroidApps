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

public class MultiAdd extends AppCompatActivity {
    MaterialButton backM,homepM,submitM;
    RadioGroup radioGroup;
    RadioButton rd1,rd2;
    TextView titlem ;
    int checkedButton = -1 ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_add);
        backM = findViewById(R.id.bckk);
        homepM =findViewById(R.id.hmm);
        submitM = findViewById(R.id.subm);
        radioGroup = findViewById(R.id.rdm);
        rd1 = findViewById(R.id.rdm1);
        rd2 = findViewById(R.id.rdm2);
        titlem = findViewById(R.id.tm);
        backM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        homepM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        submitM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                checkedButton = radioGroup.getCheckedRadioButtonId();
                if(checkedButton == R.id.rdm1) {
                    intent = new Intent(getApplicationContext(),Matrice1.class);
                    intent.putExtra("operation","+");
                    startActivity(intent);
                    finish();
                }
                else if(checkedButton == R.id.rdm2) {
                    intent = new Intent(getApplicationContext(),Matrice1.class);
                    intent.putExtra("operation","*");
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