package tn1.popo.Hopfully.results;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import java.util.Arrays;

import tn1.popo.Hopfully.R;
import tn1.popo.Hopfully.interfaces.Definite;
import tn1.popo.Hopfully.interfaces.MainActivity;
import tn1.popo.Hopfully.interfaces.PlotGraph;

public class ResultPlot extends AppCompatActivity {
    MaterialButton hm , bk ;
    ImageView graph ;
    TextView tit ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_plot);
        tit = findViewById(R.id.tddd);
        hm = findViewById(R.id.hddd);
        bk = findViewById(R.id.bddd);
        graph = findViewById(R.id.imageView);
        hm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        bk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PlotGraph.class);
                startActivity(intent);
                finish();
            }
        });
        String result = getIntent().getStringExtra("resultplot");
        byte[] bytes  = Base64.decode(result, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        graph.setImageDrawable(null);
        graph.setImageBitmap(decodedByte);

    }
}