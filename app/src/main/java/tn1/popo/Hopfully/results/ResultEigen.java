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
import tn1.popo.Hopfully.interfaces.eignvalue_vector;

public class ResultEigen extends AppCompatActivity {
    JLatexMathView mathvector,mathvalue ;
    MaterialButton homeeigres,backeigres;
    TextView titleeigres,valtxt,vectxt ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_eigen);
        System.out.println(10);
        homeeigres = findViewById(R.id.hmres);
        backeigres = findViewById(R.id.bkres);
        titleeigres = findViewById(R.id.titleeigres);
        String result = (String) getIntent().getStringExtra("resulteigen");
        System.out.println(result);
        String[] res = result.split("ù");
        String vector = res[1];
        String value = res[0];
        mathvalue = (JLatexMathView) findViewById(R.id.mathvalue);
        mathvector = (JLatexMathView) findViewById(R.id.mathvector);
        valtxt = findViewById(R.id.valuetxt);
        vectxt = findViewById(R.id.vector);
        final JLatexMathDrawable vectordrawable = JLatexMathDrawable.builder(vector)
                .textSize(70)
                .build();
        System.out.println(1);

        final JLatexMathDrawable valuedrawable = JLatexMathDrawable.builder(value)
                .textSize(70)
                .build();
        System.out.println(2);

        mathvector.setLatexDrawable(vectordrawable);
        mathvalue.setLatexDrawable(valuedrawable);
        System.out.println(3);

        homeeigres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        backeigres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), eignvalue_vector.class);
                startActivity(intent);
                finish();
            }
        });

    }
}