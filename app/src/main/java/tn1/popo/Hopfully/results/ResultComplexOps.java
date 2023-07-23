package tn1.popo.Hopfully.results;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;

import ru.noties.jlatexmath.JLatexMathDrawable;
import ru.noties.jlatexmath.JLatexMathView;
import tn1.popo.Hopfully.R;
import tn1.popo.Hopfully.interfaces.Complex;
import tn1.popo.Hopfully.interfaces.ComplexOps;
import tn1.popo.Hopfully.interfaces.MainActivity;

public class ResultComplexOps extends AppCompatActivity {
    JLatexMathView a1,a2,a3,a4,a5,a6;
    MaterialButton but1,but2 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_complex_ops);
        a1 = findViewById(R.id.cart1);
        a3 = findViewById(R.id.cart2);
        a5 = findViewById(R.id.cart3);
        a2 = findViewById(R.id.pol1);
        a4 = findViewById(R.id.pol2);
        a6 = findViewById(R.id.pol3);
        but1 = findViewById(R.id.bkk);
        but2 = findViewById(R.id.hom);
        String cart1 = getIntent().getStringExtra("lat1");
        String cart2 = getIntent().getStringExtra("lat2");
        String poli1 = getIntent().getStringExtra("poli1");
        String poli2 = getIntent().getStringExtra("poli2");
        String[] res = getIntent().getStringExtra("resultcomp").split("&");
        final JLatexMathDrawable rankdrawable = JLatexMathDrawable.builder(cart1)
                .textSize(70)
                .build();
        a1.setLatexDrawable(rankdrawable);
        final JLatexMathDrawable rankdrawable1 = JLatexMathDrawable.builder(poli1)
                .textSize(70)
                .build();
        a2.setLatexDrawable(rankdrawable1);
        final JLatexMathDrawable rankdrawable2 = JLatexMathDrawable.builder(cart2)
                .textSize(70)
                .build();
        a3.setLatexDrawable(rankdrawable2);
        final JLatexMathDrawable rankdrawable3 = JLatexMathDrawable.builder(poli2)
                .textSize(70)
                .build();
        a4.setLatexDrawable(rankdrawable3);
        final JLatexMathDrawable rankdrawable4 = JLatexMathDrawable.builder(res[0])
                .textSize(70)
                .build();
        a5.setLatexDrawable(rankdrawable4);
        final JLatexMathDrawable rankdrawable5 = JLatexMathDrawable.builder(res[1])
                .textSize(70)
                .build();
        a6.setLatexDrawable(rankdrawable5);
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ComplexOps.class);
                startActivity(intent);
                finish();
            }
        });
        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}