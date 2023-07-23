package tn1.popo.Hopfully.interfaces;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.ipsec.ike.TransportModeChildSessionParams;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import tn1.popo.Hopfully.R;

public class MatrixOps extends AppCompatActivity {
    MaterialButton backprim,homeprim,submitprim;
    RadioGroup radioGroup;
    RadioButton single,doubl,triple;
    TextView titleprim ;
    int checkedButton = -1 ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrix_ops);
        backprim = findViewById(R.id.bmatt);
        homeprim =findViewById(R.id.hmatt);
        submitprim = findViewById(R.id.submatt);
        radioGroup = findViewById(R.id.rdmatt);
        single = findViewById(R.id.rdmatt1);
        doubl = findViewById(R.id.rdmatt2);
        triple = findViewById(R.id.rdmatt3);
        titleprim = findViewById(R.id.titmat);
        backprim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(getApplicationContext(),MainActivity.class);
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
                if(checkedButton == R.id.rdmatt1) {
                    intent = new Intent(getApplicationContext(),Matrice.class);
                    startActivity(intent);
                    finish();
                }
                else if(checkedButton == R.id.rdmatt2) {
                    intent = new Intent(getApplicationContext(), Transpose.class);
                    startActivity(intent);
                    finish();
                }
                else if(checkedButton == R.id.rdmatt3) {
                    intent = new Intent(getApplicationContext(),MultiAdd.class);
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