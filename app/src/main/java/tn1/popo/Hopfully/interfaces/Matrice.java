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
import tn1.popo.Hopfully.JSONHandlers.JSONHandlerMatrix;
import tn1.popo.Hopfully.R;
import tn1.popo.Hopfully.results.ResultEigen;
import tn1.popo.Hopfully.results.ResultMatrix;

public class Matrice extends AppCompatActivity {
    EditText rowEdit;
    EditText columnEdit;
    MaterialButton genereigen,submiteigen,homeeigen,backeigen;
    int rows = 0,columns = 0;
    int oldrow = 0 , oldcol = 0 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrice);
        rowEdit =findViewById(R.id.rowmat);
        columnEdit =findViewById(R.id.colmat);
        genereigen =findViewById(R.id.genmat);
        backeigen = findViewById(R.id.bmat);
        homeeigen = findViewById(R.id.hmat);
        submiteigen = findViewById(R.id.submat);
        TableLayout matrixTable = findViewById(R.id.matmat);
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
                oldrow = rows ;
                oldcol = columns ;
                rows = Integer.parseInt(rowEdit.getText().toString());
                columns = Integer.parseInt(columnEdit.getText().toString());
                if(rows == 0 ){
                    Toast.makeText(Matrice.this, "please enter the number of rows", Toast.LENGTH_SHORT).show();
                }
                else if(rows > 8) {
                    Toast.makeText(
                            Matrice.this, "the max number of rows is 8", Toast.LENGTH_SHORT).show();
                }
                else if(columns == 0 ){
                    Toast.makeText(Matrice.this, "please enter the number of columns", Toast.LENGTH_SHORT).show();
                }
                else if(columns > 8) {
                    Toast.makeText(Matrice.this, "the max number of columns is 8", Toast.LENGTH_SHORT).show();
                }
                else if(columns != rows ){
                    Toast.makeText(Matrice.this, "this matrix will not be invertible,the determinant is 0", Toast.LENGTH_SHORT).show();
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
                String inverse="",determinant="",rank="";
                double[][] matrix = new double[rows][columns];
                for (int i = 0; i < rows; i++) {
                    TableRow row = (TableRow) matrixTable.getChildAt(i);
                    for (int j = 0; j < columns; j++) {
                        EditText cell = (EditText) row.getChildAt(j);
                        try {
                            matrix[i][j] = Double.parseDouble(cell.getText().toString());
                        } catch (NumberFormatException ex) { // handle your exception
                            Toast.makeText(Matrice.this, "invalid input in the matrix", Toast.LENGTH_SHORT).show();
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
                PyObject matrixx = py.getModule("matrixx");
                JSONHandlerMatrix jsonHandlerMatrix = new JSONHandlerMatrix();
                try {
                    String detparametres = jsonHandlerMatrix.determinantJSON(rows,matrixToSend);
                    String invparametres = jsonHandlerMatrix.inverseMatrixJSON(rows,matrixToSend);
                    String rankparametres = jsonHandlerMatrix.rankJSON(rows,matrixToSend);
                    try{
                        determinant = matrixx.callAttr("calculate_determinant",detparametres).toString();
                        inverse = matrixx.callAttr("inverse_matrix",invparametres).toString();
                        rank = matrixx.callAttr("calculate_rank",rankparametres).toString();
                        Intent intent = new Intent(getApplicationContext(), ResultMatrix.class);
                        intent.putExtra("resultdet",determinant);
                        intent.putExtra("resultinv",inverse);
                        intent.putExtra("resultrank",rank);
                        startActivity(intent);
                        finish();
                    }catch (PyException p){
                        p.printStackTrace();
                        Toast.makeText(Matrice.this, "something went wrong", Toast.LENGTH_SHORT).show();
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