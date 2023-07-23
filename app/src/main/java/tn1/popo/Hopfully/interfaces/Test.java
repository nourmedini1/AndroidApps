package tn1.popo.Hopfully.interfaces;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.chaquo.python.PyException;
import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;

import org.json.JSONException;

import java.io.ByteArrayInputStream;
import java.util.Arrays;

import tn1.popo.Hopfully.JSONHandlers.JSONHandlerPlotGraph;
import tn1.popo.Hopfully.R;
import tn1.popo.Hopfully.results.ResultPlot;

public class Test extends AppCompatActivity {
    Button btn ;
    ImageView img ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        btn = findViewById(R.id.button);
        img = findViewById(R.id.imageView2);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String parametres = "", result = "";
                if (!Python.isStarted()) {
                    Python.start(new AndroidPlatform(getApplicationContext()));
                }
                Python py = Python.getInstance();
                PyObject plot = py.getModule("show_graph");
                JSONHandlerPlotGraph jsonHandlerPlotGraph = new JSONHandlerPlotGraph();

                }







        });
    }


}