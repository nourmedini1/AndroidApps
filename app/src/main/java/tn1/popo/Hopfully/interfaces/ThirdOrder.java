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

import tn1.popo.Hopfully.JSONHandlers.JSONHandlerOde;
import tn1.popo.Hopfully.R;
import tn1.popo.Hopfully.results.ResultOde;

public class ThirdOrder extends AppCompatActivity {
    TextView titletode;
    MaterialButton hometode,backtode,submittode;
    MultiAutoCompleteTextView coef1EditText,variableEditText,coef2EditText,
            coef3EditText,coef4EditText,smEditText,constantEditText,ic1EditText,ic2EditText,ic3EditText,ic4EditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_order);
        titletode = findViewById(R.id.titletode);
        hometode = findViewById(R.id.hometode);
        backtode = findViewById(R.id.backtode);
        submittode = findViewById(R.id.submittode);
        variableEditText = findViewById(R.id.vartode);
        coef1EditText = findViewById(R.id.coef1tode);
        coef2EditText = findViewById(R.id.coef2tode);
        coef3EditText = findViewById(R.id.coef3tode);
        coef4EditText = findViewById(R.id.coef4tode);
        constantEditText = findViewById(R.id.consttode);
        smEditText = findViewById(R.id.smtode);
        ic1EditText = findViewById(R.id.ic1tode);
        ic2EditText = findViewById(R.id.ic2tode);
        ic3EditText = findViewById(R.id.ic3tode);
        ic4EditText = findViewById(R.id.ic4tode);
        hometode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        backtode.setOnClickListener(new View.OnClickListener() {
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
        coef3EditText.setAdapter(segAdapter);
        coef3EditText.setTokenizer(new SpaceTokenizer());
        coef4EditText.setAdapter(segAdapter);
        coef4EditText.setTokenizer(new SpaceTokenizer());
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
        ic3EditText.setAdapter(valAdapter);
        ic3EditText.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        ic4EditText.setAdapter(valAdapter);
        ic4EditText.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());



        submittode.setOnClickListener(new View.OnClickListener() {
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
                else if(coef3EditText.getText().toString().trim().equals("")){
                    Toast.makeText(getApplicationContext(),"please give us the coefficient for f''(x)",
                            Toast.LENGTH_SHORT).show();
                }
                else if(coef4EditText.getText().toString().trim().equals("")){
                    Toast.makeText(getApplicationContext(),"please give us the coefficient for f'''(x)",
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
                    String coef1 = coef1EditText.getText().toString().replaceAll("\\s","");;
                    String coef2 = coef2EditText.getText().toString().replaceAll("\\s","");;
                    String coef3 = coef3EditText.getText().toString().replaceAll("\\s","");;
                    String coef4 = coef4EditText.getText().toString().replaceAll("\\s","");;
                    String constant = constantEditText.getText().toString().replaceAll("\\s","");;
                    String ic1 = ic1EditText.getText().toString().trim().replaceAll("\\s","");;
                    String ic2 = ic2EditText.getText().toString().trim().replaceAll("\\s","");;
                    String ic3 = ic3EditText.getText().toString().trim().replaceAll("\\s","");;
                    String ic4 = ic4EditText.getText().toString().trim().replaceAll("\\s","");;
                    String sm = smEditText.getText().toString().trim().replaceAll("\\s","");;
                    String variable = variableEditText.getText().toString().trim().replaceAll("\\s","");;
                    if(!ic1.endsWith(",") && !ic1.equals("")){
                        ic1 = ic1+",";
                    }
                    if(!ic2.endsWith(",") && !ic2.equals("")){
                        ic2 = ic2+",";
                    }
                    if(!ic3.endsWith(",") && !ic3.equals("")){
                        ic3 = ic3+",";
                    }
                    if(!ic4.endsWith(",") && !ic4.equals("")){
                        ic4 = ic4+",";
                    }
                    if(variable.endsWith(",")){
                        variable = variable.substring(0,variable.length()-1);
                    }
                    try {
                        parametres = jsonHandlerOde.thirdOrderOdeJSON(coef1,coef2,coef3,coef4,constant,variable,sm,ic1,ic2,ic3,ic4);

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(),"wrong parameters :(",
                                Toast.LENGTH_SHORT).show();
                    }
                    try {
                        result = ode.callAttr("third_order_diff_eq",parametres).toString();
                        Intent intent = new Intent(getApplicationContext(), ResultOde.class);
                        intent.putExtra("resultdiffeq",result);
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