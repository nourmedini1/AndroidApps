package tn1.popo.Hopfully.interfaces;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import com.chaquo.python.PyException;
import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;
import com.google.android.material.button.MaterialButton;

import org.json.JSONException;

import tn1.popo.Hopfully.JSONHandlers.JSONHandlerEigenV;
import tn1.popo.Hopfully.JSONHandlers.JSONHandlerMatOps;
import tn1.popo.Hopfully.R;
import tn1.popo.Hopfully.results.ResultEigen;
import tn1.popo.Hopfully.results.ResultOps;

public class Matrice2 extends AppCompatActivity {
    EditText rowEdit;
    EditText columnEdit;
    MaterialButton genereigen,submiteigen,homeeigen,backeigen;
    int rows = 0,columns = 0;
    int oldrow = 0 , oldcol = 0 ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrice2);
        String oper = getIntent().getStringExtra("operation");
        String mat1 = getIntent().getStringExtra("mat1");
        String rr = getIntent().getStringExtra("rows");
        String cc = getIntent().getStringExtra("columns");
        int r = Integer.parseInt(rr);
        int c = Integer.parseInt(cc);
        rowEdit =findViewById(R.id.r2);
        columnEdit =findViewById(R.id.cc2);
        genereigen =findViewById(R.id.gen2);
        backeigen = findViewById(R.id.bb2);
        homeeigen = findViewById(R.id.h2);
        submiteigen = findViewById(R.id.sub2);
        TableLayout matrixTable = findViewById(R.id.mat2);
        backeigen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Matrice1.class);
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
                if (oper.equals("+") && (r != rows || c != columns)) {
                    Toast.makeText(Matrice2.this, "matrix size has to match the size of the first matrix", Toast.LENGTH_SHORT).show();
                } else if (oper.equals("*") && c != rows) {
                    Toast.makeText(Matrice2.this, "matrix size has to match the size of the first matrix", Toast.LENGTH_SHORT).show();
                }
                else if(rows == 0 ){
                    Toast.makeText(Matrice2.this, "please enter the number of rows", Toast.LENGTH_SHORT).show();
                }
                else if(rows > 8) {
                    Toast.makeText(Matrice2.this, "the max number of rows is 8", Toast.LENGTH_SHORT).show();
                }
                else if(columns == 0 ){
                    Toast.makeText(Matrice2.this, "please enter the number of columns", Toast.LENGTH_SHORT).show();
                }
                else if(columns > 8) {
                    Toast.makeText(Matrice2.this, "the max number of columns is 8", Toast.LENGTH_SHORT).show();
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
                String result = "", parametres = "";

                    double[][] matrix = new double[rows][columns];
                    for (int i = 0; i < rows; i++) {
                        TableRow row = (TableRow) matrixTable.getChildAt(i);
                        for (int j = 0; j < columns; j++) {
                            EditText cell = (EditText) row.getChildAt(j);
                            try {
                                matrix[i][j] = Double.parseDouble(cell.getText().toString());
                            } catch (NumberFormatException ex) { // handle your exception
                                Toast.makeText(Matrice2.this, "invalid input in the matrix", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }
                    String matrixToSend = "";
                    String temp;
                    for (int i = 0; i < rows; i++) {
                        temp = "";
                        for (int j = 0; j < columns; j++) {
                            temp += String.valueOf(matrix[i][j]) + ",";
                        }
                        matrixToSend += temp.substring(0, temp.length() - 1) + "&";
                    }
                    if (!Python.isStarted()) {
                        Python.start(new AndroidPlatform(getApplicationContext()));
                    }
                    Python py = Python.getInstance();
                    PyObject mat = py.getModule("multi_add");
                    JSONHandlerMatOps jsonHandlerMatOps = new JSONHandlerMatOps();

                    if (oper.equals("+")) {
                        try {
                            parametres = jsonHandlerMatOps.Add(columns, rows, mat1, matrixToSend);
                        } catch (JSONException ex) {
                            ex.printStackTrace();
                            Toast.makeText(Matrice2.this, "wrong input", Toast.LENGTH_SHORT).show();
                        }
                        try {
                            result = mat.callAttr("addition", parametres).toString();
                            Intent intent = new Intent(getApplicationContext(), ResultOps.class);
                            intent.putExtra("resultops", result);
                            startActivity(intent);
                            finish();

                        } catch (PyException p) {
                            p.printStackTrace();
                            Toast.makeText(Matrice2.this, "something went wrong !", Toast.LENGTH_SHORT).show();
                        }
                    } else {

                        try {
                            parametres = jsonHandlerMatOps.Multi(c, r, columns, rows, mat1, matrixToSend);
                        } catch (JSONException ex) {
                            ex.printStackTrace();
                            Toast.makeText(Matrice2.this, "wrong input", Toast.LENGTH_SHORT).show();
                        }

                        try {
                            result = mat.callAttr("multiplication", parametres).toString();
                            Intent intent = new Intent(getApplicationContext(), ResultOps.class);
                            intent.putExtra("resultops", result);
                            startActivity(intent);
                            finish();
                        } catch (PyException p) {
                            p.printStackTrace();
                            Toast.makeText(Matrice2.this, "something went wrong !", Toast.LENGTH_SHORT).show();
                        }



                }
            }

        });


        }
}