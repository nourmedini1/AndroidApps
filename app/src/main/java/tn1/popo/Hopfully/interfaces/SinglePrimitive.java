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
import tn1.popo.Hopfully.JSONHandlers.JSONHandlerIntegral;
import tn1.popo.Hopfully.R;
import tn1.popo.Hopfully.results.ResultDerivee;
import tn1.popo.Hopfully.results.ResultIntegral;

public class SinglePrimitive extends AppCompatActivity {
    TextView titlesingleprim;
    MaterialButton homesingleprim,backsingleprim,submitsingleprim;
    MultiAutoCompleteTextView functionEditText,variableEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_primitive);
        titlesingleprim = findViewById(R.id.titlesingleprim);
        homesingleprim = findViewById(R.id.homesingleprim);
        backsingleprim = findViewById(R.id.backsingleprim);
        submitsingleprim = findViewById(R.id.submitsingleprim);
        variableEditText = findViewById(R.id.variablessingleprim);
        functionEditText = findViewById(R.id.functionssingleprim);
        homesingleprim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        backsingleprim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Primitive.class);
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

        submitsingleprim.setOnClickListener(new View.OnClickListener() {
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
                else{
                    if(!Python.isStarted()) {
                        Python.start(new AndroidPlatform(getApplicationContext()));
                    }
                    Python py = Python.getInstance() ;
                    PyObject integrals = py.getModule("integrals");
                    JSONHandlerIntegral jsonHandlerintegral = new JSONHandlerIntegral();
                    String function = functionEditText.getText().toString();
                    String variable = variableEditText.getText().toString().trim().replaceAll("\\s","");;
                    if(variable.endsWith(",")){
                        variable = variable.trim().substring(0,variable.length()-1);
                    }
                    try {
                        parametres = jsonHandlerintegral.simplePrimitiveJSON(function,variable);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(),"wrong parameters :(",
                                Toast.LENGTH_SHORT).show();
                    }
                    try {
                        result = integrals.callAttr("simple_primitive",parametres).toString();
                        Intent intent = new Intent(getApplicationContext(), ResultIntegral.class);
                        intent.putExtra("resultintegral",result);
                        startActivity(intent);
                    }catch (PyException ex) {
                        Toast.makeText(getApplicationContext(),"something went wrong",
                                Toast.LENGTH_SHORT).show();
                    }


                    }

                }

        });

    }
}