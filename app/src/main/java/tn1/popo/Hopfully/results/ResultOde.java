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
import tn1.popo.Hopfully.interfaces.differentiel;
import tn1.popo.Hopfully.interfaces.integral;

public class ResultOde extends AppCompatActivity {
    JLatexMathView mathViewode ;
    MaterialButton homeoderes,backoderes;
    TextView titleoderes ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_ode);
        homeoderes = findViewById(R.id.homeoderes);
        backoderes = findViewById(R.id.backoderes);
        mathViewode = findViewById(R.id.mathoderes);
        titleoderes = findViewById(R.id.titleoderes);
        String result = (String) getIntent().getStringExtra("resultdiffeq");
        System.out.println(result);
        final JLatexMathDrawable drawable = JLatexMathDrawable.builder(result)
                .textSize(70)
                .build();
        System.out.println("1");
        mathViewode.setLatexDrawable(drawable);
        System.out.println("2");

        homeoderes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        backoderes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), differentiel.class);
                startActivity(intent);
                finish();
            }
        });

    }
}