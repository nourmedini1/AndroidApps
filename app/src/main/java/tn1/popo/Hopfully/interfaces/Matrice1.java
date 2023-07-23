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

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;
import com.google.android.material.button.MaterialButton;

import org.json.JSONException;

import tn1.popo.Hopfully.JSONHandlers.JSONHandlerEigenV;
import tn1.popo.Hopfully.JSONHandlers.JSONHandlerMatOps;
import tn1.popo.Hopfully.R;
import tn1.popo.Hopfully.results.ResultEigen;

public class Matrice1 extends AppCompatActivity {
    EditText rowEdit;
    EditText columnEdit;
    MaterialButton genereigen,submiteigen,homeeigen,backeigen;
    int rows = 0,columns = 0;
    int oldrow = 0 , oldcol = 0 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrice1);
        String oper = getIntent().getStringExtra("operation");
        rowEdit =findViewById(R.id.r1);
        columnEdit =findViewById(R.id.cc1);
        genereigen =findViewById(R.id.gen1);
        backeigen = findViewById(R.id.bb1);
        homeeigen = findViewById(R.id.h1);
        submiteigen = findViewById(R.id.sub1);
        TableLayout matrixTable = findViewById(R.id.mat1);
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
                    Toast.makeText(Matrice1.this, "please enter the number of rows", Toast.LENGTH_SHORT).show();
                }
                else if(rows > 8) {
                    Toast.makeText(Matrice1.this, "the max number of rows is 8", Toast.LENGTH_SHORT).show();
                }
                else if(columns == 0 ){
                    Toast.makeText(Matrice1.this, "please enter the number of columns", Toast.LENGTH_SHORT).show();
                }
                else if(columns > 8) {
                    Toast.makeText(Matrice1.this, "the max number of columns is 8", Toast.LENGTH_SHORT).show();
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
                double[][] matrix = new double[rows][columns];
                for (int i = 0; i < rows; i++) {
                    TableRow row = (TableRow) matrixTable.getChildAt(i);
                    for (int j = 0; j < columns; j++) {
                        EditText cell = (EditText) row.getChildAt(j);
                        try {
                            matrix[i][j] = Double.parseDouble(cell.getText().toString());
                        } catch (NumberFormatException ex) { // handle your exception
                            Toast.makeText(Matrice1.this, "invalid input in the matrix", Toast.LENGTH_SHORT).show();
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
                Intent intent = new Intent(getApplicationContext(), Matrice2.class);
                intent.putExtra("mat1", matrixToSend);
                System.out.println(rows);
                System.out.println(columns);
                intent.putExtra("operation", oper);
                intent.putExtra("rows",String.valueOf(rows));
                intent.putExtra("columns",String.valueOf(columns));
                startActivity(intent);
                finish();

            }
        });



        }
    }
