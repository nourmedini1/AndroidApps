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
import tn1.popo.Hopfully.interfaces.dev_limits;

public class ResultDevLimites extends AppCompatActivity {
    JLatexMathView mathView ;
    MaterialButton homedevres,backdevres;
    TextView title ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_dev_limites);
        homedevres = findViewById(R.id.homedevres);
        backdevres = findViewById(R.id.backdevres);
        mathView = findViewById(R.id.mathdev);
        title = findViewById(R.id.titledevres);
        String result = (String) getIntent().getStringExtra("resultDevLimites");
        System.out.println(result);
        mathView = (JLatexMathView) findViewById(R.id.mathdev);
        final JLatexMathDrawable drawable = JLatexMathDrawable.builder(result)
                .textSize(70)
                .build();
        System.out.println("1");
        mathView.setLatexDrawable(drawable);
        System.out.println("2");
        homedevres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        backdevres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), dev_limits.class);
                startActivity(intent);
                finish();
            }
        });


    }
}