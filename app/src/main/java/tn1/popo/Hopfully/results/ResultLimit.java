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
import tn1.popo.Hopfully.interfaces.limits;

public class ResultLimit extends AppCompatActivity {
    JLatexMathView mathViewlim ;
    MaterialButton homelimres,backlimres;
    TextView titlelimres ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_limit);
        homelimres = findViewById(R.id.homelimres);
        backlimres = findViewById(R.id.backlimres);
        titlelimres = findViewById(R.id.titlelimres);
        String result = (String) getIntent().getStringExtra("resultlimit");

        mathViewlim = (JLatexMathView) findViewById(R.id.mathlim);
        final JLatexMathDrawable drawable = JLatexMathDrawable.builder(result)
                .textSize(70)
                .padding(8)

                .align(JLatexMathDrawable.ALIGN_RIGHT)
                .build();
        System.out.println(" here 1");
        mathViewlim.setLatexDrawable(drawable);
        System.out.println("2");

        homelimres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        backlimres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), limits.class);
                startActivity(intent);
                finish();
            }
        });

    }
}