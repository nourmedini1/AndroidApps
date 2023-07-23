package tn1.popo.Hopfully.interfaces;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.chaquo.python.PyException;
import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;
import com.google.android.material.button.MaterialButton;

import org.json.JSONException;

import tn1.popo.Hopfully.JSONHandlers.JSONHandlerPlotGraph;
import tn1.popo.Hopfully.R;
import tn1.popo.Hopfully.results.ResultPlot;

public class ThreeD extends AppCompatActivity {
    TextView t3;
    MaterialButton h3,bk3,s3;
    EditText sx,ex,sy,ey ;
    MultiAutoCompleteTextView functionEditText,variableEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_d);
        t3 = findViewById(R.id.t3);
        h3 = findViewById(R.id.h3);
        bk3 = findViewById(R.id.bk3);
        s3 = findViewById(R.id.s3);
        variableEditText = findViewById(R.id.v3);
        functionEditText = findViewById(R.id.f3);
        sx = findViewById(R.id.sx);
        ex = findViewById(R.id.ex);
        sy = findViewById(R.id.sy);
        ey = findViewById(R.id.ey);

        h3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        bk3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),PlotGraph.class);
                startActivity(intent);
                finish();
            }
        });
        //autocomplete
        String[] suggestions = {"exp()", "ln()", "sin()", "cos()", "tan()", "asin()", "acos()","atan()","acosh()",
                "asinh()","atanh()","cosh()","sinh()","tanh()","pi","sqrt()",
                "alpha","beta","gamma","delta","epsilon","zeta","eta","theta","iota","kappa",
                "lambda","mu","nu","xi","omicron","rho","sigma","tau","upsilon","phi","chi","psi","omega"};

        String[] variablSeggestions = {"alpha","beta","gamma","delta","epsilon","zeta","eta","theta","iota","kappa",
                "lambda","mu","nu","xi","omicron","rho","sigma","tau","upsilon","phi","chi","psi","omega"};
        ArrayAdapter<String> segAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, suggestions);
        ArrayAdapter<String> varAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, variablSeggestions);

        functionEditText.setAdapter(segAdapter);
        functionEditText.setTokenizer(new SpaceTokenizer());
        variableEditText.setAdapter(varAdapter);
        variableEditText.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());



        s3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String parametres="",result="";

                if(functionEditText.getText().toString().trim().equals("")){
                    Toast.makeText(getApplicationContext(),"please give us the function",
                            Toast.LENGTH_SHORT).show();
                }
                else if(variableEditText.getText().toString().trim().equals("")){
                    Toast.makeText(getApplicationContext(),"please choose your variable",
                            Toast.LENGTH_SHORT).show();
                }
                else if(sx.getText().toString().trim().equals("")){
                    Toast.makeText(getApplicationContext(),"please choose the start boundx",
                            Toast.LENGTH_SHORT).show();
                }
                else if(ex.getText().toString().trim().equals("")){
                    Toast.makeText(getApplicationContext(),"please choose the end boundx",
                            Toast.LENGTH_SHORT).show();
                }
                else if(sy.getText().toString().trim().equals("")){
                    Toast.makeText(getApplicationContext(),"please choose the start boundy",
                            Toast.LENGTH_SHORT).show();
                }
                else if(ey.getText().toString().trim().equals("")){
                    Toast.makeText(getApplicationContext(),"please choose the end boundy",
                            Toast.LENGTH_SHORT).show();
                }
                else{
                    if(!Python.isStarted()) {
                        Python.start(new AndroidPlatform(getApplicationContext()));
                    }
                    Python py = Python.getInstance() ;
                    PyObject plot = py.getModule("show_graph");
                    JSONHandlerPlotGraph jsonHandlerPlotGraph = new JSONHandlerPlotGraph();
                    String function = functionEditText.getText().toString();
                    String variable = variableEditText.getText().toString().replaceAll("\\s",""); ;
                    double sxx = Double.parseDouble(sx.getText().toString());
                    double exx = Double.parseDouble(ex.getText().toString());
                    double syy = Double.parseDouble(sy.getText().toString());
                    double eyy = Double.parseDouble(ey.getText().toString());
                    if(!variable.endsWith(",")){
                        variable = variable+",";
                    }
                    try {
                        parametres = jsonHandlerPlotGraph.plotGraph3DJSON(function,variable,sxx,exx,syy,eyy);
                    } catch (JSONException ece) {
                        ece.printStackTrace();
                        Toast.makeText(getApplicationContext(),"wrong parameters :(",
                                Toast.LENGTH_SHORT).show();
                    }
                    try {
                        result = plot.callAttr("plot_graph_3d",parametres).toString();
                        Intent intent = new Intent(getApplicationContext(), ResultPlot.class);
                        intent.putExtra("resultplot",result);
                        startActivity(intent);
                    }
                    catch (PyException ex){
                        Toast.makeText(getApplicationContext(),"sorry something went wrong",
                                Toast.LENGTH_SHORT).show();


                    }




                }

            }

        });
    }
}