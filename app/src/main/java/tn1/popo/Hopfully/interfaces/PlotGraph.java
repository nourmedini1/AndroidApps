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

public class PlotGraph extends AppCompatActivity {
    MaterialButton backint,homeint,submitint;
    RadioGroup radioGroup;
    RadioButton primitive,definite;
    TextView titleint ;
    int checkedButton = -1 ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plot_graph);
        backint = findViewById(R.id.bp);
        homeint =findViewById(R.id.hp);
        submitint = findViewById(R.id.subp);
        radioGroup = findViewById(R.id.rdp);
        primitive = findViewById(R.id.rdp1);
        definite = findViewById(R.id.rdp2);
        titleint = findViewById(R.id.titlep);
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
                if(checkedButton == R.id.rdp1) {
                    intent = new Intent(getApplicationContext(),TwoD.class);
                    startActivity(intent);
                    finish();
                }
                else if(checkedButton == R.id.rdp2) {
                    intent = new Intent(getApplicationContext(),ThreeD.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(getApplicationContext(),"please choose the type of the plot",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}