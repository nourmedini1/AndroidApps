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
import tn1.popo.Hopfully.interfaces.Matrice;
import tn1.popo.Hopfully.interfaces.eignvalue_vector;

public class ResultMatrix extends AppCompatActivity {
    TextView ranktxt,invtxt,dettxt,title;
    JLatexMathView mathdet,mathinv,mathrank;
    MaterialButton home,back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_matrix);
        ranktxt = findViewById(R.id.ranktxt);
        invtxt = findViewById(R.id.invtxt);
        dettxt = findViewById(R.id.dettxt);
        mathdet = findViewById(R.id.det);
        mathinv = findViewById(R.id.inv);
        mathrank = findViewById(R.id.rank);
        title = findViewById(R.id.titlematres);
        home = findViewById(R.id.hresmat);
        back = findViewById(R.id.bresmat);
        String det = (String) getIntent().getStringExtra("resultdet");
        String inv = (String) getIntent().getStringExtra("resultinv");
        String rank = (String) getIntent().getStringExtra("resultrank");
        final JLatexMathDrawable invdrawable = JLatexMathDrawable.builder(inv)
                .textSize(70)
                .build();
        System.out.println(1);

        final JLatexMathDrawable detdrawable = JLatexMathDrawable.builder(det)
                .textSize(70)
                .build();
        System.out.println(2);
        final JLatexMathDrawable rankdrawable = JLatexMathDrawable.builder(rank)
                .textSize(70)
                .build();
        System.out.println(2);

        mathrank.setLatexDrawable(rankdrawable);
        mathinv.setLatexDrawable(invdrawable);
        mathdet.setLatexDrawable(detdrawable);
        System.out.println(3);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Matrice.class);
                startActivity(intent);
                finish();
            }
        });

    }
}