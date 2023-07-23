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

public class TripleIntegral extends AppCompatActivity {
    TextView titletriple;
    MaterialButton hometripleint,backtripleint,submittripleint;
    MultiAutoCompleteTextView functionEditText,variableEditText,xlimitsEditText,ylimitsEditText,zlimitsEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_triple_integral);
        titletriple = findViewById(R.id.titletripleint);
        hometripleint = findViewById(R.id.hometripleint);
        backtripleint = findViewById(R.id.backtripleint);
        submittripleint = findViewById(R.id.submittripleint);
        variableEditText = findViewById(R.id.variablestripleint);
        functionEditText = findViewById(R.id.functionstripleint);
        xlimitsEditText = findViewById(R.id.xlimitstripleint);
        ylimitsEditText = findViewById(R.id.ylimitstripleint);
        zlimitsEditText = findViewById(R.id.zlimitstripleint);
        hometripleint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        backtripleint.setOnClickListener(new View.OnClickListener() {
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
        xlimitsEditText.setAdapter(valAdapter);
        xlimitsEditText.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        ylimitsEditText.setAdapter(valAdapter);
        ylimitsEditText.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        zlimitsEditText.setAdapter(valAdapter);
        zlimitsEditText.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());

        submittripleint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String parametres="",result="";
                if(functionEditText.getText().toString().trim().equals("")){
                    Toast.makeText(getApplicationContext(),"please give us the function",
                            Toast.LENGTH_SHORT).show();
                }
                else if(variableEditText.getText().toString().trim().equals("")){
                    Toast.makeText(getApplicationContext(),"please choose your variables",
                            Toast.LENGTH_SHORT).show();
                }
                else if(xlimitsEditText.getText().toString().trim().equals("") ||
                        ylimitsEditText.getText().toString().trim().equals("") ||
                        zlimitsEditText.getText().toString().trim().equals("")){
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
                    String xlimits = xlimitsEditText.getText().toString().trim();
                    String ylimits = ylimitsEditText.getText().toString().trim();
                    String zlimits = zlimitsEditText.getText().toString().trim();
                    if(!variable.endsWith(",")){
                        variable = variable+",";
                    }
                    if(!xlimits.endsWith(",")){
                        xlimits = xlimits+",";
                    }
                    if(!ylimits.endsWith(",")){
                        ylimits = ylimits+",";
                    }
                    if(!zlimits.endsWith(",")){
                        zlimits = zlimits+",";
                    }
                    try {
                        parametres = jsonHandlerintegral.tripleIntegralJSON(function,variable,xlimits,ylimits,zlimits);

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(),"wrong parameters :(",
                                Toast.LENGTH_SHORT).show();
                    }
                    try {
                        result = integrals.callAttr("triple_integral",parametres).toString();
                        Intent intent = new Intent(getApplicationContext(), ResultIntegral.class);
                        intent.putExtra("resultintegral",result);
                        startActivity(intent);
                    }catch (PyException ex){
                        Toast.makeText(getApplicationContext(),"something went wrong",
                                Toast.LENGTH_SHORT).show();
                    }


                }

            }

        });
    }
}