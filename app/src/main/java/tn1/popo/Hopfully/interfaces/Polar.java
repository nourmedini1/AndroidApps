package tn1.popo.Hopfully.interfaces;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.chaquo.python.PyException;
import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;
import com.google.android.material.button.MaterialButton;

import org.json.JSONException;

import tn1.popo.Hopfully.JSONHandlers.JSONHandlerComplex;
import tn1.popo.Hopfully.R;
import tn1.popo.Hopfully.results.ResultComplexOps;
import tn1.popo.Hopfully.results.ResultPolar;

public class Polar extends AppCompatActivity {
    EditText r , i ;
    MaterialButton b, t , th ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_polar);
        r = findViewById(R.id.re1);
        i = findViewById(R.id.im1);
        b = findViewById(R.id.bc);
        t = findViewById(R.id.dar);
        th = findViewById(R.id.s);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Complex.class);
                startActivity(intent);
                finish();
            }
        });
        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        th.setOnClickListener(new View.OnClickListener() {
            String parametres="";String result="",ee = "";
            @Override
            public void onClick(View v) {
                if (!Python.isStarted()) {
                    Python.start(new AndroidPlatform(getApplicationContext()));
                }
                Python py = Python.getInstance();
                PyObject complex = py.getModule("complex");
                JSONHandlerComplex jsonHandlerComplex = new JSONHandlerComplex();
                double rr1 = Double.parseDouble(r.getText().toString());
                double ii1 = Double.parseDouble(i.getText().toString());
                try {
                    ee = jsonHandlerComplex.JSONLatexize(rr1,ii1);
                    parametres = jsonHandlerComplex.JSONPolar(rr1, ii1);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "wrong parameters :(",
                            Toast.LENGTH_SHORT).show();
                }
                try {
                    result = complex.callAttr("polar", parametres).toString();
                    String lat1 = complex.callAttr("latexize", ee).toString();
                    Intent intent = new Intent(getApplicationContext(), ResultPolar.class);
                    intent.putExtra("resultpolar", result);
                    intent.putExtra("lat1", lat1);
                    startActivity(intent);
                    finish();
                } catch (PyException ex) {
                    Toast.makeText(getApplicationContext(), "something went wrong",
                            Toast.LENGTH_SHORT).show();

                }


            }

        });


    }
}