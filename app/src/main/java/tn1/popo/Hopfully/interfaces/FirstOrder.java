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
import tn1.popo.Hopfully.JSONHandlers.JSONHandlerOde;
import tn1.popo.Hopfully.R;
import tn1.popo.Hopfully.results.ResultIntegral;
import tn1.popo.Hopfully.results.ResultOde;

public class FirstOrder extends AppCompatActivity {
    TextView titlefirstode;
    MaterialButton homefirstode,backfirstode,submitfirstode;
    MultiAutoCompleteTextView coef1EditText,variableEditText,coef2EditText,smEditText,constantEditText,ic1EditText,ic2EditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_order);
        titlefirstode = findViewById(R.id.titlefirstode);
        homefirstode = findViewById(R.id.homefirstode);
        backfirstode = findViewById(R.id.backfirstode);
        submitfirstode = findViewById(R.id.submitfirstode);
        variableEditText = findViewById(R.id.variablefirsrode);
        coef1EditText = findViewById(R.id.coef1firstode);
        coef2EditText = findViewById(R.id.coef2firstode);
        constantEditText = findViewById(R.id.constantfirstode);
        smEditText = findViewById(R.id.smfirstode);
        ic1EditText = findViewById(R.id.ic1firstode);
        ic2EditText = findViewById(R.id.ic2firstode);
        homefirstode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        backfirstode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),differentiel.class);
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

        coef1EditText.setAdapter(segAdapter);
        coef1EditText.setTokenizer(new SpaceTokenizer());
        coef2EditText.setAdapter(segAdapter);
        coef2EditText.setTokenizer(new SpaceTokenizer());
        constantEditText.setAdapter(segAdapter);
        constantEditText.setTokenizer(new SpaceTokenizer());
        smEditText.setAdapter(segAdapter);
        smEditText.setTokenizer(new SpaceTokenizer());
        variableEditText.setAdapter(varAdapter);
        variableEditText.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        ic1EditText.setAdapter(valAdapter);
        ic1EditText.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        ic2EditText.setAdapter(valAdapter);
        ic2EditText.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());

        submitfirstode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String parametres="",result="";
                if(coef1EditText.getText().toString().trim().equals("")){
                    Toast.makeText(getApplicationContext(),"please give us the coefficient for f(x)",
                            Toast.LENGTH_SHORT).show();
                }
                else if(coef2EditText.getText().toString().trim().equals("")){
                    Toast.makeText(getApplicationContext(),"please give us the coefficient for f'(x)",
                            Toast.LENGTH_SHORT).show();
                }
                else if(constantEditText.getText().toString().trim().equals("")){
                    Toast.makeText(getApplicationContext(),"please give us the constant",
                            Toast.LENGTH_SHORT).show();
                }
                else if(smEditText.getText().toString().trim().equals("")){
                    Toast.makeText(getApplicationContext(),"please give us the second member of the ode",
                            Toast.LENGTH_SHORT).show();
                }
                else if(variableEditText.getText().toString().trim().equals("")){
                    Toast.makeText(getApplicationContext(),"please choose your variable",
                            Toast.LENGTH_SHORT).show();
                }
                else{
                    if(!Python.isStarted()) {
                        Python.start(new AndroidPlatform(getApplicationContext()));
                    }
                    Python py = Python.getInstance() ;
                    PyObject ode = py.getModule("equation_differentielle");
                    JSONHandlerOde jsonHandlerOde = new JSONHandlerOde();
                    String coef1 = coef1EditText.getText().toString().trim().replaceAll("\\s","");;
                    String coef2 = coef2EditText.getText().toString().trim().replaceAll("\\s","");;
                    String constant = constantEditText.getText().toString().trim().replaceAll("\\s","");;
                    String ic1 = ic1EditText.getText().toString().trim().replaceAll("\\s","");;
                    String ic2 = ic2EditText.getText().toString().trim().replaceAll("\\s","");;
                    String sm = smEditText.getText().toString().trim().replaceAll("\\s","");;
                    String variable = variableEditText.getText().toString().trim().replaceAll("\\s","");;
                    if(!ic1.endsWith(",") && !ic1.equals("")){
                        ic1 = ic1+",";
                    }
                    if(!ic2.endsWith(",") && !ic2.equals("")){
                        ic2 = ic2+",";
                    }
                    if(variable.endsWith(",")){
                        variable = variable.substring(0,variable.length()-1);
                    }
                    try {

                            parametres = jsonHandlerOde.firstOrderOdeJSON(coef1,coef2,constant,variable,sm,ic1,ic2);


                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(),"wrong parameters :(",
                                Toast.LENGTH_SHORT).show();
                    }
                    try {
                        result = ode.callAttr("first_order_diff_eq",parametres).toString();
                        Intent intent = new Intent(getApplicationContext(), ResultOde.class);
                        intent.putExtra("resultdiffeq",result);
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