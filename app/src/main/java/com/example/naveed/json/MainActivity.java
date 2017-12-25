package com.example.naveed.json;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView tvColor;
    private TextView tvHexValue;
    private Button bt1;
    private Gson gson;
    private Colors colors;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tvColor= (TextView) findViewById(R.id.tv1);
        tvHexValue= (TextView) findViewById(R.id.tv2);
        bt1= (Button) findViewById(R.id.btn1);


        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String jsonString = readFromFile();

                gson= new Gson();
                colors= new Colors();
                colors= gson.fromJson(jsonString, colors.getClass() );
                //show on UI
                tvColor.setText(colors.getColors().get(0).getColor());
                tvHexValue.setText(colors.getColors().get(0).getHexValue());

//                List<Color> allColors= colors.getColors();
//                int count = allColors.size();
//                for (int i=0; i<=count; i++){
//                    Color color= allColors.get(i);
//                }
            }


        });
    }

    private String readFromFile() {

        String fileName="data_json";
        StringBuffer stringBuffer= new StringBuffer();
        InputStream is= getResources().openRawResource(R.raw.json_data);

        //reading data from file
        int character;
        try {
            while((character= is.read()) != -1)
            {
                stringBuffer.append((char) character);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuffer.toString();
    }
}
