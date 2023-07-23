
package tn1.popo.Hopfully.interfaces;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import tn1.popo.Hopfully.R;

public class derive extends AppCompatActivity {
    MaterialButton back,home,derive;
    RadioGroup radioGroup;
    RadioButton symbolic,valued;
    TextView title ;
    int checkedButton = -1 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_derive);
        back = findViewById(R.id.materialbutton2);
        home =findViewById(R.id.materialButton);
        derive = findViewById(R.id.materialbutton3);
        radioGroup = findViewById(R.id.rdcomops);
        symbolic = findViewById(R.id.rdderive1);
        valued = findViewById(R.id.rdderive2);
        title = findViewById(R.id.titlederive);
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
                if(checkedButton == R.id.rdderive1) {
                    intent = new Intent(getApplicationContext(),derivee_symbolic.class);
                    startActivity(intent);
                    finish();
                }
                else if(checkedButton == R.id.rdderive2) {
                    intent = new Intent(getApplicationContext(),derivee_valued.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    Toast.makeText(getApplicationContext(),"please choose the type of the derivative",Toast.LENGTH_SHORT).show();
                }

            }
        });




    }

}