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

import tn1.popo.Hopfully.JSONHandlers.JSONHandlerIntegral;
import tn1.popo.Hopfully.JSONHandlers.JSONHandlerLimits;
import tn1.popo.Hopfully.R;
import tn1.popo.Hopfully.results.ResultIntegral;
import tn1.popo.Hopfully.results.ResultLimit;

public class limitOne extends AppCompatActivity {
    TextView titlelimone;
    MaterialButton homelimone,backlimone,submitlimone;
    MultiAutoCompleteTextView functionEditText,variableEditText,boundEditText;
    RadioGroup rdlimone ;
    RadioButton rdlimonep,rdlimonen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_limit_one);
        titlelimone = findViewById(R.id.titlelimone);
        homelimone = findViewById(R.id.homelimone);
        backlimone = findViewById(R.id.backlimone);
        submitlimone = findViewById(R.id.sublimone);
        variableEditText = findViewById(R.id.variableslimone);
        functionEditText = findViewById(R.id.functionslimone);
        boundEditText = findViewById(R.id.bound1limone);
        rdlimone = findViewById(R.id.rdlimone);
        rdlimonep = findViewById(R.id.rdlimone1);
        rdlimonen = findViewById(R.id.rdlimone2);
        homelimone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        backlimone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),limits.class);
                startActivity(intent);
                finish();
            }
        });
        //autocomplete
        String[] suggestions = {"exp()", "ln()", "sin()", "cos()", "tan()", "asin()", "acos()","atan()","acosh()",
                "asinh()","atanh()","cosh()","sinh()","tanh()","pi","sqrt()",
                "alpha","beta","gamma","delta","epsilon","zeta","eta","theta","iota","kappa",
                "lambda","mu","nu","xi","omicron","rho","sigma","tau","upsilon","phi","chi","psi","omega","-oo","oo"};

        String[] variablSeggestions = {"alpha","beta","gamma","delta","epsilon","zeta","eta","theta","iota","kappa",
                "lambda","mu","nu","xi","omicron","rho","sigma","tau","upsilon","phi","chi","psi","omega"};
        ArrayAdapter<String> segAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, suggestions);
        ArrayAdapter<String> varAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, variablSeggestions);

        functionEditText.setAdapter(segAdapter);
        functionEditText.setTokenizer(new SpaceTokenizer());
        boundEditText.setAdapter(segAdapter);
        boundEditText.setTokenizer(new SpaceTokenizer());
        variableEditText.setAdapter(varAdapter);
        variableEditText.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        submitlimone.setOnClickListener(new View.OnClickListener() {
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
                else if(boundEditText.getText().toString().trim().equals("")){
                    Toast.makeText(getApplicationContext(),"please choose your limit's bound",
                            Toast.LENGTH_SHORT).show();
                }
                else{
                    if(!Python.isStarted()) {
                        Python.start(new AndroidPlatform(getApplicationContext()));
                    }
                    Python py = Python.getInstance() ;
                    PyObject limit = py.getModule("limits");
                    JSONHandlerLimits jsonHandlerLimits = new JSONHandlerLimits();
                    String function = functionEditText.getText().toString();
                    String variables = variableEditText.getText().toString().trim().replaceAll("\\s","");;
                    String bound = boundEditText.getText().toString().trim().replaceAll("\\s","");;
                    String sign ="+";
                    if(rdlimone.getCheckedRadioButtonId()==R.id.rdlimone2){
                        sign = "-";
                    }
                    if(variables.endsWith(",")){
                        variables = variables.substring(0,variables.length()-1);
                    }
                    try {
                        parametres = jsonHandlerLimits.limitSingleVariableJSON(function,variables,sign,bound);
                        System.out.println("before callattr");

                        System.out.println("after callattr");
                        System.out.println("reshome"+result);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(),"wrong parameters :(",
                                Toast.LENGTH_SHORT).show();
                    }
                    try {
                        result = limit.callAttr("calculate_limit_single_variable",parametres).toString();
                        Intent intent = new Intent(getApplicationContext(), ResultLimit.class);
                        System.out.println("before intent");
                        intent.putExtra("resultlimit",result);
                        startActivity(intent);
                        System.out.println("after intent");
                    }catch (PyException ex){
                        Toast.makeText(getApplicationContext(),"something went wrong",
                                Toast.LENGTH_SHORT).show();
                    }

                }

            }

        });

    }
}