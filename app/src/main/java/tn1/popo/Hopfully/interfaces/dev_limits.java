package tn1.popo.Hopfully.interfaces;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.chaquo.python.PyException;
import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;
import com.google.android.material.button.MaterialButton;

import org.json.JSONException;

import tn1.popo.Hopfully.JSONHandlers.JSONHandlerDevLimites;
import tn1.popo.Hopfully.R;
import tn1.popo.Hopfully.results.ResultDevLimites;

public class dev_limits extends AppCompatActivity {
    private MultiAutoCompleteTextView functionEditText;
    private MultiAutoCompleteTextView variableEditText,valueEdittext;
    private EditText ordreEditText;
    boolean doIntent = true ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dev);
        functionEditText = findViewById(R.id.functionsdev);
        variableEditText = findViewById(R.id.variablesdev);
        valueEdittext = findViewById(R.id.value);
        MaterialButton home = findViewById(R.id.homedev);
        MaterialButton back = findViewById(R.id.backdev);
        ordreEditText = findViewById(R.id.order_editdev);
        MaterialButton submitButton = findViewById(R.id.submitdev);
        TextView title = findViewById(R.id.titledev);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
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
        String[] valueSeggestions = {"exp()", "ln()", "sin()", "cos()", "tan()", "asin()", "acos()","atan()","acosh()",
                "asinh()","atanh()","cosh()","sinh()","tanh()","pi","sqrt()","-oo","oo"};
        // Créer un ArrayAdapter pour stocker les suggestions
        ArrayAdapter<String> segAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, suggestions);
        ArrayAdapter<String> varAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, variablSeggestions);
        ArrayAdapter<String> valAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, valueSeggestions);

        functionEditText.setAdapter(segAdapter);
        functionEditText.setTokenizer(new SpaceTokenizer());
        variableEditText.setAdapter(varAdapter);
        variableEditText.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        valueEdittext.setAdapter(valAdapter);
        valueEdittext.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());


        submitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String parameteres="";
                    if(functionEditText.getText().toString().trim().equals("")){
                        Toast.makeText(dev_limits.this, "please give us a function", Toast.LENGTH_SHORT).show();
                    }
                    else if(variableEditText.getText().toString().trim().equals("")){
                        Toast.makeText(dev_limits.this, "please choose your variable", Toast.LENGTH_SHORT).show();
                    }
                    else if(variableEditText.getText().toString().trim().equals("")){
                        Toast.makeText(dev_limits.this, "where are we going to apply the taylor series", Toast.LENGTH_SHORT).show();
                    }
                    else if(ordreEditText.getText().toString().trim().equals("")){
                        Toast.makeText(dev_limits.this, "give us the order of the series", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        String function = functionEditText.getText().toString();
                        String variable = variableEditText.getText().toString().trim().replaceAll("\\s","");;
                        String near = valueEdittext.getText().toString().trim().replaceAll("\\s","");;
                        if(variable.endsWith(",")){
                            variable = variable.substring(0,variable.length()-1);
                        }

                        if(near.endsWith(",")){
                            near = near.substring(0,near.length()-1);
                        }
                        int order = Integer.parseInt(ordreEditText.getText().toString());
                        JSONHandlerDevLimites jsonHandlerDevLimites = new JSONHandlerDevLimites();
                        try {
                            parameteres = jsonHandlerDevLimites.taylorSeriesJSON(near,order,function,variable);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(),"wrong parameter try again",Toast.LENGTH_LONG).show();
                        }
                        if(!Python.isStarted()) {
                            Python.start(new AndroidPlatform(getApplicationContext()));
                        }
                        Python py = Python.getInstance() ;
                        PyObject dev_limites = py.getModule("dev_limites");
                        try {
                            String result = dev_limites.callAttr("taylor_series",parameteres).toString();
                            Intent intent = new Intent(getApplicationContext(), ResultDevLimites.class);
                            intent.putExtra("resultDevLimites", result);
                            startActivity(intent);

                        }catch (PyException ex){
                            Toast.makeText(getApplicationContext(),"wrong parameter try again",Toast.LENGTH_LONG).show();
                        }

                    }


                }});




}}
