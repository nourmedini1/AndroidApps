package tn1.popo.Hopfully.interfaces;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.chaquo.python.PyException;
import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;
import com.google.android.material.button.MaterialButton;

import org.json.JSONException;

import tn1.popo.Hopfully.JSONHandlers.JSONHandlerComplex;
import tn1.popo.Hopfully.JSONHandlers.JSONHandlerLimits;
import tn1.popo.Hopfully.R;
import tn1.popo.Hopfully.results.ResultComplexOps;
import tn1.popo.Hopfully.results.ResultLimit;

public class ComplexOps extends AppCompatActivity {
    EditText re1,img1,re2,img2;
    MaterialButton sub,b,h;
    RadioButton r1 , r2 , r3 ;
    RadioGroup r ;
    int checkedRadioButton = -1 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_com_ops);
        re1 = findViewById(R.id.real1);
        re2 = findViewById(R.id.real2);
        img2 = findViewById(R.id.img2);
        img1 = findViewById(R.id.img1);
        sub = findViewById(R.id.subcomops);
        b = findViewById(R.id.bcomops);
        h = findViewById(R.id.hcomops);
        r = findViewById(R.id.rdcomops);
        r1 = findViewById(R.id.rdcomops1);
        r2 = findViewById(R.id.rdcomops2);
        r3 = findViewById(R.id.rdcomops3);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Complex.class);
                startActivity(intent);
                finish();
            }
        });
        h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        sub.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String parametres = "", result = "";
                checkedRadioButton = r.getCheckedRadioButtonId();
                if (r1.getText().toString().trim().equals("")) {
                    Toast.makeText(ComplexOps.this, "please give us the real part", Toast.LENGTH_SHORT).show();
                } else if (img1.getText().toString().trim().equals("")) {
                    Toast.makeText(ComplexOps.this, "please give us the imaginary part", Toast.LENGTH_SHORT).show();
                } else if (r2.getText().toString().trim().equals("")) {
                    Toast.makeText(ComplexOps.this, "please give us the real part", Toast.LENGTH_SHORT).show();
                } else if (img2.getText().toString().trim().equals("")) {
                    Toast.makeText(ComplexOps.this, "please give us the imaginary part", Toast.LENGTH_SHORT).show();
                } else if (checkedRadioButton == -1) {
                    Toast.makeText(ComplexOps.this, "please choose an operation", Toast.LENGTH_SHORT).show();
                } else if (checkedRadioButton == R.id.rdcomops1) {
                    if (!Python.isStarted()) {
                        Python.start(new AndroidPlatform(getApplicationContext()));
                    }
                    Python py = Python.getInstance();
                    PyObject complex = py.getModule("complex");
                    JSONHandlerComplex jsonHandlerComplex = new JSONHandlerComplex();
                    double rr1 = Double.parseDouble(re1.getText().toString());
                    double ii1 = Double.parseDouble(img1.getText().toString());
                    double rr2 = Double.parseDouble(re2.getText().toString());
                    double ii2 = Double.parseDouble(img2.getText().toString());
                    try {
                        parametres = jsonHandlerComplex.JSONComplexOps(rr1, ii1, rr2, ii2, "*");
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), "wrong parameters :(",
                                Toast.LENGTH_SHORT).show();
                    }
                    try {
                        result = complex.callAttr("complex_ops", parametres).toString();
                        String pol1 = jsonHandlerComplex.JSONPolar(rr1, ii1);
                        String pol2 = jsonHandlerComplex.JSONPolar(rr2, ii2);
                        String latex1 = jsonHandlerComplex.JSONLatexize(rr1, ii1);
                        String latex2 = jsonHandlerComplex.JSONLatexize(rr2, ii2);
                        String poli1 = complex.callAttr("polar", pol1).toString();
                        String poli2 = complex.callAttr("polar", pol2).toString();
                        String lat1 = complex.callAttr("latexize", latex1).toString();
                        String lat2 = complex.callAttr("latexize", latex2).toString();
                        Intent intent = new Intent(getApplicationContext(), ResultComplexOps.class);
                        intent.putExtra("resultcomp", result);
                        intent.putExtra("lat1", lat1);
                        intent.putExtra("lat2", lat2);
                        intent.putExtra("poli1", poli1);
                        intent.putExtra("poli2", poli2);
                        startActivity(intent);
                        finish();
                    } catch (PyException ex) {
                        Toast.makeText(getApplicationContext(), "something went wrong",
                                Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } else if (checkedRadioButton == R.id.rdcomops2) {
                    if (!Python.isStarted()) {
                        Python.start(new AndroidPlatform(getApplicationContext()));
                    }
                    Python py = Python.getInstance();
                    PyObject complex = py.getModule("complex");
                    JSONHandlerComplex jsonHandlerComplex = new JSONHandlerComplex();
                    double rr1 = Double.parseDouble(re1.getText().toString());
                    double ii1 = Double.parseDouble(img1.getText().toString());
                    double rr2 = Double.parseDouble(re2.getText().toString());
                    double ii2 = Double.parseDouble(img2.getText().toString());
                    try {
                        parametres = jsonHandlerComplex.JSONComplexOps(rr1, ii1, rr2, ii2, "+");
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), "wrong parameters :(",
                                Toast.LENGTH_SHORT).show();
                    }
                    try {
                        result = complex.callAttr("complex_ops", parametres).toString();
                        String pol1 = jsonHandlerComplex.JSONPolar(rr1, ii1);
                        String pol2 = jsonHandlerComplex.JSONPolar(rr2, ii2);
                        String latex1 = jsonHandlerComplex.JSONLatexize(rr1, ii1);
                        String latex2 = jsonHandlerComplex.JSONLatexize(rr2, ii2);
                        String poli1 = complex.callAttr("polar", pol1).toString();
                        String poli2 = complex.callAttr("polar", pol2).toString();
                        String lat1 = complex.callAttr("latexize", latex1).toString();
                        String lat2 = complex.callAttr("latexize", latex2).toString();
                        Intent intent = new Intent(getApplicationContext(), ResultComplexOps.class);
                        intent.putExtra("resultcomp", result);
                        intent.putExtra("lat1", lat1);
                        intent.putExtra("lat2", lat2);
                        intent.putExtra("poli1", poli1);
                        intent.putExtra("poli2", poli2);
                        startActivity(intent);
                        finish();
                    } catch (PyException ex) {
                        Toast.makeText(getApplicationContext(), "something went wrong",
                                Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
                else if(checkedRadioButton == R.id.rdcomops3) {
                    if(!Python.isStarted()) {
                        Python.start(new AndroidPlatform(getApplicationContext()));
                    }
                    Python py = Python.getInstance() ;
                    PyObject complex = py.getModule("complex");
                    JSONHandlerComplex jsonHandlerComplex = new JSONHandlerComplex();
                    double rr1 = Double.parseDouble(re1.getText().toString());
                    double ii1 = Double.parseDouble(img1.getText().toString());
                    double rr2 = Double.parseDouble(re2.getText().toString());
                    double ii2 = Double.parseDouble(img2.getText().toString());
                    try {
                        parametres = jsonHandlerComplex.JSONComplexOps(rr1,ii1,rr2,ii2,"-");
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(),"wrong parameters :(",
                                Toast.LENGTH_SHORT).show();
                    }
                    try {
                        result = complex.callAttr("complex_ops",parametres).toString();
                        String pol1 = jsonHandlerComplex.JSONPolar(rr1,ii1);
                        String pol2 = jsonHandlerComplex.JSONPolar(rr2,ii2);
                        String latex1 = jsonHandlerComplex.JSONLatexize(rr1,ii1);
                        String latex2 = jsonHandlerComplex.JSONLatexize(rr2,ii2);
                        String poli1 = complex.callAttr("polar",pol1).toString();
                        String poli2 = complex.callAttr("polar",pol2).toString();
                        String lat1 = complex.callAttr("latexize",latex1).toString();
                        String lat2 = complex.callAttr("latexize",latex2).toString();
                        Intent intent = new Intent(getApplicationContext(), ResultComplexOps.class);
                        intent.putExtra("resultcomp",result);
                        intent.putExtra("lat1",lat1);
                        intent.putExtra("lat2",lat2);
                        intent.putExtra("poli1",poli1);
                        intent.putExtra("poli2",poli2);
                        startActivity(intent);
                        finish();
                    }catch (PyException ex){
                        Toast.makeText(getApplicationContext(),"something went wrong",
                                Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }



            }
            }
        });
    }

    }
