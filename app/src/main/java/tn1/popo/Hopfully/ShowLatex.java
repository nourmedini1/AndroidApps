package tn1.popo.Hopfully;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import ru.noties.jlatexmath.JLatexMathDrawable;
import ru.noties.jlatexmath.JLatexMathView;

public class ShowLatex extends AppCompatActivity {
    JLatexMathView mathView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_latex);
        String latex = (String) getIntent().getStringExtra("latex");
        mathView = (JLatexMathView) findViewById(R.id.mathdev);
        final JLatexMathDrawable drawable = JLatexMathDrawable.builder(latex)
                .textSize(70)
                .padding(8)
                .background(0xFFffffff)
                .align(JLatexMathDrawable.ALIGN_RIGHT)
                .build();
        mathView.setLatexDrawable(drawable);
    }
}