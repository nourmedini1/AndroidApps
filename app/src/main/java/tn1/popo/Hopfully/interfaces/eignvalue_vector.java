package tn1.popo.Hopfully.interfaces;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.chaquo.python.PyException;
import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;
import com.google.android.material.button.MaterialButton;

import org.json.JSONException;

import tn1.popo.Hopfully.JSONHandlers.JSONHandlerEigenV;
import tn1.popo.Hopfully.JSONHandlers.JSONHandlerIntegral;
import tn1.popo.Hopfully.R;
import tn1.popo.Hopfully.results.ResultEigen;

public class eignvalue_vector extends AppCompatActivity {
   EditText rowEdit;
   EditText columnEdit;
   MaterialButton genereigen,submiteigen,homeeigen,backeigen;
    int rows = 0,columns = 0;
    int oldrow = 0 , oldcol = 0 ;
    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_eignvalue_vector);
            rowEdit =findViewById(R.id.roweigen);
            columnEdit =findViewById(R.id.coleigen);
            genereigen =findViewById(R.id.matrixeigen);
            backeigen = findViewById(R.id.backeigen);
            homeeigen = findViewById(R.id.homeeigen);
            submiteigen = findViewById(R.id.subeigen);
            TableLayout matrixTable = findViewById(R.id.mateigen);
            backeigen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
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
                    oldcol = columns;
                    oldrow = rows ;
                    rows = Integer.parseInt(rowEdit.getText().toString());
                    columns = Integer.parseInt(columnEdit.getText().toString());
                    if(rows == 0 ){
                        Toast.makeText(eignvalue_vector.this, "please enter the number of rows", Toast.LENGTH_SHORT).show();
                    }
                    else if(rows > 8) {
                        Toast.makeText(eignvalue_vector.this, "the max number of rows is 8", Toast.LENGTH_SHORT).show();
                    }
                    else if(columns == 0 ){
                        Toast.makeText(eignvalue_vector.this, "please enter the number of columns", Toast.LENGTH_SHORT).show();
                    }
                    else if(columns > 8) {
                        Toast.makeText(eignvalue_vector.this, "the max number of columns is 8", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        if(oldrow !=0 && oldcol != 0) {
                                matrixTable.removeAllViews();
                            }

                        for (int i = matrixTable.getChildCount(); i < rows; i++) {
                            TableRow row = new TableRow(getApplicationContext());
                            for (int j = 0; j < columns; j++) {
                                EditText cell = new EditText(getApplicationContext());
                                cell.setGravity(Gravity.CENTER_HORIZONTAL);
                                cell.setInputType(InputType.TYPE_CLASS_NUMBER);
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
                    String result="";
                    double[][] matrix = new double[rows][columns];
                    for (int i = 0; i < rows; i++) {
                        TableRow row = (TableRow) matrixTable.getChildAt(i);
                        for (int j = 0; j < columns; j++) {
                            EditText cell = (EditText) row.getChildAt(j);
                            try {
                                matrix[i][j] = Double.parseDouble(cell.getText().toString());
                            } catch (NumberFormatException ex) { // handle your exception
                                Toast.makeText(eignvalue_vector.this, "invalid input in the matrix", Toast.LENGTH_SHORT).show();
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
                    PyObject eigen = py.getModule("eigen");
                    JSONHandlerEigenV jsonHandlerEigenV = new JSONHandlerEigenV();
                    try {
                        String parametres = jsonHandlerEigenV.eigenVJSON(matrixToSend);
                        try {
                            result = eigen.callAttr("eigenvector",parametres).toString();
                            Intent intent = new Intent(getApplicationContext(), ResultEigen.class);
                            intent.putExtra("resulteigen",result);
                            startActivity(intent);
                            finish();
                        }catch (PyException p){
                            p.printStackTrace();
                            Toast.makeText(eignvalue_vector.this, "something went wrong", Toast.LENGTH_SHORT).show();
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