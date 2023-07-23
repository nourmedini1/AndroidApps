package tn1.popo.Hopfully.interfaces;

import static com.google.android.material.internal.ViewUtils.dpToPx;

import com.chaquo.python.PyException;
import com.google.android.material.internal.ViewUtils;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.InputType;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;
import com.google.android.material.button.MaterialButton;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import tn1.popo.Hopfully.JSONHandlers.JSONHandlerEigenV;
import tn1.popo.Hopfully.JSONHandlers.JSONHandlerLinearSystem;
import tn1.popo.Hopfully.R;
import tn1.popo.Hopfully.results.ResultEigen;
import tn1.popo.Hopfully.results.ResultLinear;

public class linear_system extends AppCompatActivity {
    EditText nbeqs;
    MultiAutoCompleteTextView nbvars;
    MaterialButton gensys,subsys,homesys,backsys;
    int eqs = 0,vars = 0;
    int oldeqs = 0 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_system);
        nbeqs =findViewById(R.id.rowsys);
        nbvars =findViewById(R.id.colsys);
        gensys =findViewById(R.id.gensys);
        subsys = findViewById(R.id.subsys);
        backsys = findViewById(R.id.backsys);
        homesys = findViewById(R.id.homesys);
        String[] variablSeggestions = {"alpha","beta","gamma","delta","epsilon","zeta","eta","theta","iota","kappa",
                "lambda","mu","nu","xi","omicron","rho","sigma","tau","upsilon","phi","chi","psi","omega"};
        ArrayAdapter<String> varAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, variablSeggestions);
        nbvars.setAdapter(varAdapter);
        nbvars.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        TableLayout matrixTable = findViewById(R.id.matsys);
        for (int i = 0; i < 5; i++) {
            TableRow tableRow = (TableRow) matrixTable.getChildAt(i);
            tableRow.setVisibility(View.INVISIBLE);
        }

        backsys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        homesys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        gensys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                oldeqs = eqs ;
                eqs = Integer.parseInt(nbeqs.getText().toString());
                if(eqs == 0 ){
                    Toast.makeText(linear_system.this, "please enter the number of equations", Toast.LENGTH_SHORT).show();
                }
                else if(eqs > 5) {
                    Toast.makeText(linear_system.this, "the max number of equations is 5", Toast.LENGTH_SHORT).show();
                }
                else if(nbvars.getText().toString().trim().equals("")){
                    Toast.makeText(linear_system.this, "please enter the variables used ", Toast.LENGTH_SHORT).show();
                }
                else {
                    for (int i = 0; i < oldeqs; i++) {
                        TableRow tableRow = (TableRow) matrixTable.getChildAt(i);
                        tableRow.setVisibility(View.INVISIBLE);
                    }

                    for (int i = 0; i < eqs; i++) {
                        TableRow tableRow = (TableRow) matrixTable.getChildAt(i);
                        tableRow.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

        subsys.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String equations="",sm="",result="";
                for (int i = 0; i < eqs; i++) {
                    TableRow row = (TableRow) matrixTable.getChildAt(i);
                    EditText cell1 = (EditText) row.getChildAt(0);
                    EditText cell2 = (EditText) row.getChildAt(2);
                    try {
                        equations+=cell1.getText().toString()+",";
                        sm+=cell2.getText().toString()+",";
                    } catch (Exception ex) { // handle your exception
                        Toast.makeText(linear_system.this, "invalid input in the linear system", Toast.LENGTH_SHORT).show();
                    }

                    }
                if(!Python.isStarted()) {
                    Python.start(new AndroidPlatform(getApplicationContext()));
                }
                Python py = Python.getInstance() ;
                PyObject linear = py.getModule("linear_system_solve");
                JSONHandlerLinearSystem jsonHandlerLinearSystem = new JSONHandlerLinearSystem();
                String vars = nbvars.getText().toString();
                if(vars.endsWith(",")){
                    vars = vars.substring(0,vars.length()-1);
                }
                try {
                    String parametres = jsonHandlerLinearSystem.linearSystemSolveJSON(equations,vars,sm);
                    try{
                        result = linear.callAttr("linear_system_solve",parametres).toString();
                        Intent intent = new Intent(getApplicationContext(), ResultLinear.class);
                        intent.putExtra("resultlinear",result);
                        startActivity(intent);
                        finish();
                    }catch (PyException p){
                        p.printStackTrace();
                        Toast.makeText(linear_system.this, "this linear system doesn't have a solution", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"wrong parameters :(",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });












    }
}