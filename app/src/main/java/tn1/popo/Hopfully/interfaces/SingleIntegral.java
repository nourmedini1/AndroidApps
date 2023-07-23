package tn1.popo.Hopfully.interfaces;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.chaquo.python.PyException;
import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;
import com.google.android.material.button.MaterialButton;

import org.json.JSONException;

import tn1.popo.Hopfully.JSONHandlers.JSONHandlerIntegral;
import tn1.popo.Hopfully.R;
import tn1.popo.Hopfully.results.ResultIntegral;

public class SingleIntegral extends AppCompatActivity {
    TextView titlesingleint;
    MaterialButton homesingleint,backsingleint,submitsingleint;
    MultiAutoCompleteTextView functionEditText,variableEditText,limitsEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_integral);
        titlesingleint = findViewById(R.id.titlesingleint);
        homesingleint = findViewById(R.id.homesingleint);
        backsingleint = findViewById(R.id.backsingleint);
        submitsingleint = findViewById(R.id.submitsingleint);
        variableEditText = findViewById(R.id.variablessingleint);
        functionEditText = findViewById(R.id.functionssingleint);
        limitsEditText = findViewById(R.id.limitssingleint);
        homesingleint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        backsingleint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Definite.class);
                startActivity(intent);
                finish();
            }
        });
        //autocomplete
        String[] limitsuggestions = {"exp()", "ln()", "sin()", "cos()", "tan()", "asin()", "acos()","atan()","acosh()",
                "asinh()","atanh()","cosh()","sinh()","tanh()","pi","sqrt()",
                "alpha","beta","gamma","delta","epsilon","zeta","eta","theta","iota","kappa",
                "lambda","mu","nu","xi","omicron","rho","sigma","tau","upsilon","phi","chi","psi","omega","-oo","oo"};

        String[] suggestions = {"exp()", "ln()", "sin()", "cos()", "tan()", "asin()", "acos()","atan()","acosh()",
                "asinh()","atanh()","cosh()","sinh()","tanh()","pi","sqrt()",
                "alpha","beta","gamma","delta","epsilon","zeta","eta","theta","iota","kappa",
                "lambda","mu","nu","xi","omicron","rho","sigma","tau","upsilon","phi","chi","psi","omega"};

        String[] variablSeggestions = {"alpha","beta","gamma","delta","epsilon","zeta","eta","theta","iota","kappa",
                "lambda","mu","nu","xi","omicron","rho","sigma","tau","upsilon","phi","chi","psi","omega"};
        ArrayAdapter<String> segAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, suggestions);
        ArrayAdapter<String> varAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, variablSeggestions);
        ArrayAdapter<String> valAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, limitsuggestions);

        functionEditText.setAdapter(segAdapter);
        functionEditText.setTokenizer(new SpaceTokenizer());
        variableEditText.setAdapter(varAdapter);
        variableEditText.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        limitsEditText.setAdapter(valAdapter);
        limitsEditText.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());


        submitsingleint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String parametres="",result="";
                if(functionEditText.getText().toString().trim().equals("")){
                    Toast.makeText(getApplicationContext(),"please give us the function",
                            Toast.LENGTH_SHORT).show();
                }
                else if(variableEditText.getText().toString().trim().equals("")){
                    Toast.makeText(getApplicationContext(),"please choose a variable",
                            Toast.LENGTH_SHORT).show();
                }
                else if(limitsEditText.getText().toString().trim().equals("") ){
                    Toast.makeText(getApplicationContext(),"please give us the integral limits",
                            Toast.LENGTH_SHORT).show();
                }
                else{
                    if(!Python.isStarted()) {
                        Python.start(new AndroidPlatform(getApplicationContext()));
                    }
                    Python py = Python.getInstance() ;
                    PyObject integrals = py.getModule("integrals");
                    JSONHandlerIntegral jsonHandlerintegral = new JSONHandlerIntegral();
                    String function = functionEditText.getText().toString();
                    String variable = variableEditText.getText().toString().trim();
                    String limits = limitsEditText.getText().toString().trim();
                    if(variable.endsWith(",")){
                        variable = variable.substring(0,variable.length()-1);
                    }
                    if(limits.endsWith(",")){
                        limits = limits.substring(0,limits.length()-1);
                    }
                    String[] lims = limits.split(",");
                    try {
                        parametres = jsonHandlerintegral.simpleIntegralJSON(function,variable,lims[1],lims[0]);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(),"wrong parameters :(",
                                Toast.LENGTH_SHORT).show();
                    }
                    try {
                        result = integrals.callAttr("simple_integral",parametres).toString();
                        Intent intent = new Intent(getApplicationContext(), ResultIntegral.class);
                        intent.putExtra("resultintegral",result);
                        startActivity(intent);
                    }catch (PyException exp){
                        Toast.makeText(getApplicationContext(),"something went wrong",
                                Toast.LENGTH_SHORT).show();
                    }


                }

            }

        });
    }
}