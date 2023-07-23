package tn1.popo.Hopfully.interfaces;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;
import com.google.android.material.button.MaterialButton;

import org.json.JSONException;

import tn1.popo.Hopfully.JSONHandlers.JSONHandlerMatOps;
import tn1.popo.Hopfully.JSONHandlers.JSONHandlerMatrix;
import tn1.popo.Hopfully.R;
import tn1.popo.Hopfully.results.ResultMatrix;
import tn1.popo.Hopfully.results.ResultOps;

public class Transpose extends AppCompatActivity {
    EditText rowEdit;
    EditText columnEdit;
    MaterialButton genereigen,submiteigen,homeeigen,backeigen;
    int rows = 0,columns = 0;
    int oldrow = 0 , oldcol = 0 ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transpose);
        rowEdit =findViewById(R.id.rowtrans);
        columnEdit =findViewById(R.id.coltrans);
        genereigen =findViewById(R.id.gentrans);
        backeigen = findViewById(R.id.btrans);
        homeeigen = findViewById(R.id.htrans);
        submiteigen = findViewById(R.id.subtrans);
        TableLayout matrixTable = findViewById(R.id.mattrans);
        backeigen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MatrixOps.class);
                startActivity(intent);
                finish();
            }
        });
        homeeigen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        genereigen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                oldrow = rows ;
                oldcol = columns ;
                rows = Integer.parseInt(rowEdit.getText().toString());
                columns = Integer.parseInt(columnEdit.getText().toString());
                if(rows == 0 ){
                    Toast.makeText(Transpose.this, "please enter the number of rows", Toast.LENGTH_SHORT).show();
                }
                else if(rows > 8) {
                    Toast.makeText(
                            Transpose.this, "the max number of rows is 8", Toast.LENGTH_SHORT).show();
                }
                else if(columns == 0 ){
                    Toast.makeText(Transpose.this, "please enter the number of columns", Toast.LENGTH_SHORT).show();
                }
                else if(columns > 8) {
                    Toast.makeText(Transpose.this, "the max number of columns is 8", Toast.LENGTH_SHORT).show();
                }
                else {
                    if(oldrow !=0 && oldcol != 0) {
                        matrixTable.removeAllViews();
                    }
                    for (int i = matrixTable.getChildCount(); i < rows; i++) {
                        TableRow row = new TableRow(getApplicationContext());
                        for (int j = 0; j < columns; j++) {
                            EditText cell = new EditText(getApplicationContext());
                            row.addView(cell);
                        }
                        matrixTable.addView(row);
                    }


                }

            }
        });

        submiteigen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double[][] matrix = new double[rows][columns];
                for (int i = 0; i < rows; i++) {
                    TableRow row = (TableRow) matrixTable.getChildAt(i);
                    for (int j = 0; j < columns; j++) {
                        EditText cell = (EditText) row.getChildAt(j);
                        try {
                            matrix[i][j] = Double.parseDouble(cell.getText().toString());
                        } catch (NumberFormatException ex) { // handle your exception
                            Toast.makeText(Transpose.this, "invalid input in the matrix", Toast.LENGTH_SHORT).show();
                        }

                    }
                }
                String matrixToSend="";
                String temp;
                for (int i = 0; i < rows; i++) {
                    temp = "";
                    for (int j = 0; j < columns; j++) {
                        temp+= String.valueOf(matrix[i][j])+"," ;
                    }
                    matrixToSend += temp.substring(0,temp.length()-1)+"&";
                }
                if(!Python.isStarted()) {
                    Python.start(new AndroidPlatform(getApplicationContext()));
                }
                Python py = Python.getInstance() ;
                PyObject matrixx = py.getModule("multi_add");
                JSONHandlerMatOps jsonHandlerMatOps = new JSONHandlerMatOps();
                try {
                    String parametres = jsonHandlerMatOps.Trans(columns,rows,matrixToSend);
                    String result = matrixx.callAttr("transpose",parametres).toString();
                    Intent intent = new Intent(getApplicationContext(), ResultOps.class);
                    intent.putExtra("resultops",result);
                    startActivity(intent);
                    finish();
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),"wrong parameters :(",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}