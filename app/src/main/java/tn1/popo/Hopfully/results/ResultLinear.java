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
import tn1.popo.Hopfully.interfaces.eignvalue_vector;
import tn1.popo.Hopfully.interfaces.linear_system;

public class ResultLinear extends AppCompatActivity {
    JLatexMathView mathsys,mathlin ;
    MaterialButton hlin,blin;
    TextView titlelin,systxt,restxt ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_linear);
        hlin = findViewById(R.id.hlinres);
        blin = findViewById(R.id.blinres);
        titlelin = findViewById(R.id.titlelinres);
        String result = (String) getIntent().getStringExtra("resultlinear");
        System.out.println(result);
        String[] res = result.split("&");
        String resl = res[1];
        String sys = res[0];
        mathlin = (JLatexMathView) findViewById(R.id.mathsys);
        mathsys = (JLatexMathView) findViewById(R.id.mathlinres);
        systxt = findViewById(R.id.sys);
        restxt = findViewById(R.id.linres);
        final JLatexMathDrawable sysdrawable = JLatexMathDrawable.builder(sys)
                .textSize(70)
                .build();
        System.out.println(1);

        final JLatexMathDrawable linresdrawable = JLatexMathDrawable.builder(resl)
                .textSize(70)
                .build();
        System.out.println(2);

        mathsys.setLatexDrawable(linresdrawable);
        mathlin.setLatexDrawable(sysdrawable);
        System.out.println(3);

        hlin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        blin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), linear_system.class);
                startActivity(intent);
                finish();
            }
        });

    }
}