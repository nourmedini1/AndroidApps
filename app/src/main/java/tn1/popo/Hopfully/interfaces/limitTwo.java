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

import tn1.popo.Hopfully.JSONHandlers.JSONHandlerLimits;
import tn1.popo.Hopfully.R;
import tn1.popo.Hopfully.results.ResultLimit;

public class limitTwo extends AppCompatActivity {
    TextView titlelimtwo;
    MaterialButton homelimtwo,backlimtwo,submitlimtwo;
    MultiAutoCompleteTextView functionEditText,variableEditText,bound1EditText,bound2EditText;
    RadioGroup rd1lim,rd2lim ;
    RadioButton rd1lim1,rd1lim2,rd2lim1,rd2lim2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_limit_two);
        titlelimtwo = findViewById(R.id.titlelimtwo);
        homelimtwo = findViewById(R.id.homelimtwo);
        backlimtwo = findViewById(R.id.backlimtwo);
        submitlimtwo = findViewById(R.id.sublimtwo);
        variableEditText = findViewById(R.id.variableslimtwo);
        functionEditText = findViewById(R.id.functionslimtwo);
        bound1EditText = findViewById(R.id.bound1limtwo);
        bound2EditText = findViewById(R.id.bound2limtwo);
        rd1lim = findViewById(R.id.rd1limtwo);
        rd1lim1 = findViewById(R.id.rd1limtwo1);
        rd1lim2 = findViewById(R.id.rd1limtwo2);
        rd2lim = findViewById(R.id.rdlimtwo);
        rd2lim1 = findViewById(R.id.rdlimtwo1);
        rd2lim2 = findViewById(R.id.rdlimtwo2);
        homelimtwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        backlimtwo.setOnClickListener(new View.OnClickListener() {
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
        bound1EditText.setAdapter(segAdapter);
        bound1EditText.setTokenizer(new SpaceTokenizer());
        bound2EditText.setAdapter(segAdapter);
        bound2EditText.setTokenizer(new SpaceTokenizer());
        functionEditText.setAdapter(segAdapter);
        functionEditText.setTokenizer(new SpaceTokenizer());
        variableEditText.setAdapter(varAdapter);
        variableEditText.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());

        submitlimtwo.setOnClickListener(new View.OnClickListener() {
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
                else if(bound1EditText.getText().toString().trim().equals("")){
                    Toast.makeText(getApplicationContext(),"please choose your limit's bound1",
                            Toast.LENGTH_SHORT).show();
                }
                else if(bound2EditText.getText().toString().trim().equals("")){
                    Toast.makeText(getApplicationContext(),"please choose your limit's bound2",
                            Toast.LENGTH_SHORT).show();
                }
                else{
                    if(!Python.isStarted()) {
                        Python.start(new AndroidPlatform(getApplicationContext()));
                    }
                    Python py = Python.getInstance() ;
                    PyObject limits = py.getModule("limits");
                    JSONHandlerLimits jsonHandlerLimits = new JSONHandlerLimits();
                    String function = functionEditText.getText().toString();
                    String variables = variableEditText.getText().toString().trim().replaceAll("\\s","");;
                    String bound1 = bound1EditText.getText().toString().trim().replaceAll("\\s","");;
                    String bound2 = bound2EditText.getText().toString().trim().replaceAll("\\s","");;
                    String sign1 ="+";
                    String sign2 ="+";
                    if(rd1lim.getCheckedRadioButtonId()==R.id.rd1limtwo2){
                        sign1 = "-";
                    }
                    if(rd2lim.getCheckedRadioButtonId()==R.id.rdlimtwo2){
                        sign2 = "-";
                    }
                    if(!variables.endsWith(",")){
                        variables = variables+",";
                    }
                    try {
                        parametres = jsonHandlerLimits.limitDoubleVariableJSON(function,variables,sign1,bound1,sign2,bound2);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(),"wrong parameters :(",
                                Toast.LENGTH_SHORT).show();
                    }
                    try {
                        result = limits.callAttr("calculate_limit_two_variables",parametres).toString();
                        Intent intent = new Intent(getApplicationContext(), ResultLimit.class);
                        intent.putExtra("resultlimit",result);
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