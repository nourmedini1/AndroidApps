package tn1.popo.Hopfully;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;

public class MainActivity2 extends AppCompatActivity {
    Button btn ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        TableLayout matrixTable = findViewById(R.id.matrix_table);
        btn = (Button) findViewById(R.id.button2);
        int rows = (int) getIntent().getIntExtra("rows",0);
        int columns = (int) getIntent().getIntExtra("cols",0);
        if (rows > 8 || columns > 8) {
            // show error message
            return;
        }

// add rows and columns as needed
        for (int i = matrixTable.getChildCount(); i < rows; i++) {
            TableRow row = new TableRow(this);
            for (int j = 0; j < columns; j++) {
                EditText cell = new EditText(this);
                row.addView(cell);
            }
            matrixTable.addView(row);
        }
    btn.setOnClickListener(new View.OnClickListener() {
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

                    }

                }
            }
            for (int i = 0; i < rows; i++) {

                for (int j = 0; j < columns; j++) {
                    System.out.println(matrix[i][j]);
                }
            }

        }
    });
// retrieve input and construct matrix


    }}

