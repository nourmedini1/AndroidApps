package tn1.popo.Hopfully.results;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import ru.noties.jlatexmath.JLatexMathDrawable;
import ru.noties.jlatexmath.JLatexMathView;
import tn1.popo.Hopfully.R;
import tn1.popo.Hopfully.interfaces.MainActivity;
import tn1.popo.Hopfully.interfaces.derive;
import tn1.popo.Hopfully.interfaces.dev_limits;

public class ResultDerivee extends AppCompatActivity {
    JLatexMathView mathViewderive ;
    MaterialButton homederiveres,backderiveres;
    TextView titlederiveres ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_derivee);
        homederiveres = findViewById(R.id.homederiveres);
        backderiveres = findViewById(R.id.backderiveres);
        mathViewderive = findViewById(R.id.mathderive);
        titlederiveres = findViewById(R.id.titlederiveres);
        String result = (String) getIntent().getStringExtra("resultderiv");
        System.out.println(result);
        mathViewderive = (JLatexMathView) findViewById(R.id.mathderive);
        final JLatexMathDrawable drawable = JLatexMathDrawable.builder(result)
                .textSize(70)
                .build();
        System.out.println("1");
        mathViewderive.setLatexDrawable(drawable);
        System.out.println("2");

        homederiveres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        backderiveres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), derive.class);
                startActivity(intent);
                finish();
            }
        });

    }
}