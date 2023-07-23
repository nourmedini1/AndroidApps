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
import tn1.popo.Hopfully.interfaces.derive;
import tn1.popo.Hopfully.interfaces.integral;

public class ResultIntegral extends AppCompatActivity {
    JLatexMathView mathViewint ;
    MaterialButton homeintres,backintres;
    TextView titleintres ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_integral);
        homeintres = findViewById(R.id.homeintres);
        backintres = findViewById(R.id.backintres);
        mathViewint = findViewById(R.id.mathintres);
        titleintres = findViewById(R.id.titleintres);
        String result = (String) getIntent().getStringExtra("resultintegral");
        System.out.println(result);
        final JLatexMathDrawable drawable = JLatexMathDrawable.builder(result)
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
                Intent intent = new Intent(getApplicationContext(), integral.class);
                startActivity(intent);
                finish();
            }
        });

    }
}