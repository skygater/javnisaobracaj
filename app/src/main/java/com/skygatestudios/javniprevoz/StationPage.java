package com.skygatestudios.javniprevoz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class StationPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_page);

        Intent intent = getIntent();
        String nameS = intent.getStringExtra("name");
        int intId = intent.getIntExtra("position",0);


        TextView t = (TextView) findViewById(R.id.stanName) ;

        t.setText(intId+" "+nameS);
    }
}
