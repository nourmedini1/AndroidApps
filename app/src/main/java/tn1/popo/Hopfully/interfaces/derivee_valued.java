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
import tn1.popo.Hopfully.R;
import tn1.popo.Hopfully.results.ResultDerivee;

public class derivee_valued extends AppCompatActivity {
    TextView titleval;
    MaterialButton homeval, backval, submitval;
    EditText orderedit ;
    MultiAutoCompleteTextView functionEditText,variableEditText,nearedit;
    RadioGroup rd ;
    RadioButton deriv,partial;
    int checkedRadiButton = -1;
    boolean doIntent = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_derivee_valued);
        nearedit = findViewById(R.id.nearval);
        titleval = findViewById(R.id.titleval);
        homeval = findViewById(R.id.homeval);
        backval = findViewById(R.id.backval);
        submitval = findViewById(R.id.submitval);
        orderedit = findViewById(R.id.order_editval);
        variableEditText = findViewById(R.id.variablesval);
        functionEditText = findViewById(R.id.functionsval);
        rd = findViewById(R.id.rdval);
        deriv = findViewById(R.id.rdval1);
        partial = findViewById(R.id.rdval2);
        homeval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        backval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        //autocomplete
        String[] valueSeggestions = {"exp()", "ln()", "sin()", "cos()", "tan()", "asin()", "acos()","atan()","acosh()",
                "asinh()","atanh()","cosh()","sinh()","tanh()","pi","sqrt()","-oo","oo"};
        String[] suggestions = {"exp()", "ln()", "sin()", "cos()", "tan()", "asin()", "acos()","atan()","acosh()",
                "asinh()","atanh()","cosh()","sinh()","tanh()","pi","sqrt()",
                "alpha","beta","gamma","delta","epsilon","zeta","eta","theta","iota","kappa",
                "lambda","mu","nu","xi","omicron","rho","sigma","tau","upsilon","phi","chi","psi","omega"};

        String[] variablSeggestions = {"alpha","beta","gamma","delta","epsilon","zeta","eta","theta","iota","kappa",
                "lambda","mu","nu","xi","omicron","rho","sigma","tau","upsilon","phi","chi","psi","omega"};
        ArrayAdapter<String> segAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, suggestions);
        ArrayAdapter<String> varAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, variablSeggestions);
        ArrayAdapter<String> valAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, valueSeggestions);

        functionEditText.setAdapter(segAdapter);
        functionEditText.setTokenizer(new SpaceTokenizer());
        variableEditText.setAdapter(varAdapter);
        variableEditText.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        nearedit.setAdapter(valAdapter);
        nearedit.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());

        submitval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String parametres="",result="";
                checkedRadiButton = rd.getCheckedRadioButtonId();
                if(checkedRadiButton == -1 ){
                    Toast.makeText(getApplicationContext(),"choose the type of the derivative",
                            Toast.LENGTH_SHORT).show();
                }
                else if(functionEditText.getText().toString().trim().equals("")){
                    Toast.makeText(getApplicationContext(),"please give us the function",
                            Toast.LENGTH_SHORT).show();
                }
                else if(variableEditText.getText().toString().trim().equals("")){
                    Toast.makeText(getApplicationContext(),"please choose your variable",
                            Toast.LENGTH_SHORT).show();
                }
                else if(orderedit.getText().toString().trim().equals("")){
                    Toast.makeText(getApplicationContext(),"please choose the order of the derivative",
                            Toast.LENGTH_SHORT).show();
                }
                else if(nearedit.getText().toString().trim().equals("")){
                    Toast.makeText(getApplicationContext(),"where are we deriving the function",
                            Toast.LENGTH_SHORT).show();
                }
                else{
                    if(!Python.isStarted()) {
                        Python.start(new AndroidPlatform(getApplicationContext()));
                    }
                    Python py = Python.getInstance() ;
                    PyObject derivee = py.getModule("derivee");
                    JSONHandlerDerivee jsonHandlerDerivee = new JSONHandlerDerivee();
                    String function = functionEditText.getText().toString();
                    String variable = variableEditText.getText().toString().trim().replaceAll("\\s","");;
                    if(variable.endsWith(",")){
                        variable = variable.substring(0,variable.length()-1);
                    }
                    String near = nearedit.getText().toString().trim();
                    if(near.endsWith(",")){
                        near = near.substring(0,near.length()-1);                    }
                    int order = Integer.parseInt(orderedit.getText().toString());

                    if(checkedRadiButton == R.id.rdval1){
                        try {
                            parametres = jsonHandlerDerivee.oneDimentionalDerivativeValueJSON(function,order,variable,near);
                            result = derivee.callAttr("one_dimentional_derivative_value",parametres).toString();
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(),"wrong parameters :(",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        try {
                            try {
                                parametres = jsonHandlerDerivee.partialDerivativeValueJSON(function,order,variable,near,10);
                                result = derivee.callAttr("partial_derivative_value",parametres).toString();
                            }catch (PyException ex){
                                Toast.makeText(getApplicationContext(),"something went wrong",
                                        Toast.LENGTH_SHORT).show();
                                doIntent = false;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(),"wrong parameters :(",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                    if(doIntent){
                        Intent intent = new Intent(getApplicationContext(), ResultDerivee.class);
                        intent.putExtra("resultderiv",result);
                        startActivity(intent);
                    }

                }
            }
        });
    }
}