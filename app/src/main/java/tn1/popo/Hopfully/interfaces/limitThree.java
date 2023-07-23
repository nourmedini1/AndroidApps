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

public class limitThree extends AppCompatActivity {
    TextView titlethree;
    MaterialButton homethree,backthree,subthree;
    MultiAutoCompleteTextView functionEditText,variableEditText,bound1EditText,bound2EditText,bound3EditText;
    RadioGroup a,c,d ;
    RadioButton a1,a2,c1,c2,d1,d2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_limit_three);
        titlethree = findViewById(R.id.titlethree);
        homethree = findViewById(R.id.hm);
        backthree = findViewById(R.id.bck);
        subthree = findViewById(R.id.sub);
        variableEditText = findViewById(R.id.varthree);
        functionEditText = findViewById(R.id.funthree);
        bound1EditText = findViewById(R.id.b1);
        bound2EditText = findViewById(R.id.b2);
        bound3EditText = findViewById(R.id.b3);
        a = findViewById(R.id.a);
        a1 = findViewById(R.id.a1);
        a2 = findViewById(R.id.a2);
        c = findViewById(R.id.c);
        c1 = findViewById(R.id.c1);
        c2 = findViewById(R.id.c2);
        d = findViewById(R.id.d);
        d1 = findViewById(R.id.d1);
        d2 = findViewById(R.id.d2);
        homethree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        backthree.setOnClickListener(new View.OnClickListener() {
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
        bound3EditText.setAdapter(segAdapter);
        bound3EditText.setTokenizer(new SpaceTokenizer());
        functionEditText.setAdapter(segAdapter);
        functionEditText.setTokenizer(new SpaceTokenizer());
        variableEditText.setAdapter(varAdapter);
        variableEditText.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());


        subthree.setOnClickListener(new View.OnClickListener() {
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
                else if(bound3EditText.getText().toString().trim().equals("")){
                    Toast.makeText(getApplicationContext(),"please choose your limit's bound3",
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
                    String bound1 = bound1EditText.getText().toString().trim().replaceAll("\\s","");;
                    String bound2 = bound2EditText.getText().toString().trim().replaceAll("\\s","");;
                    String bound3 = bound3EditText.getText().toString().trim().replaceAll("\\s","");;
                    String sign1 ="+";
                    String sign2 ="+";
                    String sign3 ="+";
                    if(a.getCheckedRadioButtonId()==R.id.a2){
                        sign1 = "-";
                    }
                    if(c.getCheckedRadioButtonId()==R.id.c2){
                        sign2 = "-";
                    }
                    if(d.getCheckedRadioButtonId()==R.id.d2){
                        sign3 = "-";
                    }
                    if(!variables.endsWith(",")){
                        variables = variables+",";
                    }
                    try {
                        parametres = jsonHandlerLimits.limitTripleVariableJSON(function,variables,
                                sign1,bound1,sign2,bound2,sign3,bound3);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(),"wrong parameters :(",
                                Toast.LENGTH_SHORT).show();
                    }
                    try {
                        result = limit.callAttr("calculate_limit_three_variables",parametres).toString();
                        Intent intent = new Intent(getApplicationContext(), ResultLimit.class);
                        intent.putExtra("resultlimit",result);
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