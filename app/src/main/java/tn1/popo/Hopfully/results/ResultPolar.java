package tn1.popo.Hopfully.results;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import ru.noties.jlatexmath.JLatexMathDrawable;
import ru.noties.jlatexmath.JLatexMathView;
import tn1.popo.Hopfully.R;
import tn1.popo.Hopfully.interfaces.MainActivity;
import tn1.popo.Hopfully.interfaces.Polar;
import tn1.popo.Hopfully.interfaces.integral;

public class ResultPolar extends AppCompatActivity {
    JLatexMathView mathViewint ;
    MaterialButton homeintres,backintres;
    TextView titleintres ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_polar);
        homeintres = findViewById(R.id.hpol);
        backintres = findViewById(R.id.bpol);
        mathViewint = findViewById(R.id.polres);
        titleintres = findViewById(R.id.titpol);
        String result = (String) getIntent().getStringExtra("resultpolar");
        String lat1 = (String) getIntent().getStringExtra("lat1");
        final JLatexMathDrawable drawable = JLatexMathDrawable.builder(lat1+" = "+result)
                .textSize(70)
                .build();
        System.out.println("1");
        mathViewint.setLatexDrawable(drawable);
        System.out.println("2");

        homeintres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        backintres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Polar.class);
                startActivity(intent);
                finish();
            }
        });

    }
}