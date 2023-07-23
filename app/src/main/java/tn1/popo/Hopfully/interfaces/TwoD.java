package tn1.popo.Hopfully.interfaces;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.chaquo.python.PyException;
import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;
import com.google.android.material.button.MaterialButton;

import org.json.JSONException;

import tn1.popo.Hopfully.JSONHandlers.JSONHandlerDerivee;
import tn1.popo.Hopfully.JSONHandlers.JSONHandlerPlotGraph;
import tn1.popo.Hopfully.R;
import tn1.popo.Hopfully.results.ResultDerivee;
import tn1.popo.Hopfully.results.ResultPlot;

public class TwoD extends AppCompatActivity {
    TextView titletwo;
    MaterialButton htwo,btwo,subtwo;
    EditText precision,start,end ;
    MultiAutoCompleteTextView functionEditText,variableEditText;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_d);
        titletwo = findViewById(R.id.titletwo);
        htwo = findViewById(R.id.htwo);
        btwo = findViewById(R.id.btwo);
        subtwo = findViewById(R.id.subtwo);
        precision = findViewById(R.id.precision);
        variableEditText = findViewById(R.id.vartwo);
        functionEditText = findViewById(R.id.functwo);
        start = findViewById(R.id.start);
        end = findViewById(R.id.end);

        htwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btwo.setOnClickListener(new View.OnClickListener() {
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



        subtwo.setOnClickListener(new View.OnClickListener() {
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
                else if(precision.getText().toString().trim().equals("")){
                    Toast.makeText(getApplicationContext(),"please choose the precision (effects speed)",
                            Toast.LENGTH_SHORT).show();
                }
                else if(start.getText().toString().trim().equals("")){
                    Toast.makeText(getApplicationContext(),"please choose the start bound",
                            Toast.LENGTH_SHORT).show();
                }
                else if(end.getText().toString().trim().equals("")){
                    Toast.makeText(getApplicationContext(),"please choose the end bound",
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
                    double s = Double.parseDouble(start.getText().toString());
                    double e = Double.parseDouble(end.getText().toString());
                    if(variable.endsWith(",")){
                        variable = variable.substring(0,variable.length()-1);
                    }
                    int prec = Integer.parseInt(precision.getText().toString());
                        try {
                            parametres = jsonHandlerPlotGraph.plotGraph2DJSON(function,variable,s,e,prec);
                        } catch (JSONException ece) {
                            ece.printStackTrace();
                            Toast.makeText(getApplicationContext(),"wrong parameters :(",
                                    Toast.LENGTH_SHORT).show();
                        }
                    try {
                        result = plot.callAttr("plot_graph_2d",parametres).toString();
                        System.out.println(result);
                        Intent intent = new Intent(getApplicationContext(), ResultPlot.class);
                        intent.putExtra("resultplot",result);
                        startActivity(intent);
                        finish();
                    }
                    catch (PyException ex){
                        ex.printStackTrace();
                        Toast.makeText(getApplicationContext(),"sorry something went wrong",
                                Toast.LENGTH_SHORT).show();


                    }




                    }

                }

        });
    }
}