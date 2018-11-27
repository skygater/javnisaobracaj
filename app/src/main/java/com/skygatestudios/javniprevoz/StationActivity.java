package com.skygatestudios.javniprevoz;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.skygatestudios.javniprevoz.Adapter.ListStationAdapter;
import com.skygatestudios.javniprevoz.DataBase.DatabaseHelper;
import com.skygatestudios.javniprevoz.Java.Stanice;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class StationActivity extends AppCompatActivity {


    DatabaseHelper db;
    List<Stanice> stanice;

    ListStationAdapter sAdapter;
    ListView listView;
    int numb;
    String lineNumber;

    int ascDesc = 0;
    String st1;
    String st2;

    TextView stanica1;
    TextView stanica2;
    TextView linija;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.station_list);

        Intent intent = getIntent();
        numb = intent.getIntExtra("position", 0);
        lineNumber = intent.getStringExtra("brLinije");
        st1 = intent.getStringExtra("start");
        st2 = intent.getStringExtra("end");

        stanica1 = (TextView) findViewById(R.id.stanica1);
         stanica2 = (TextView) findViewById(R.id.stanica2);
        linija = (TextView) findViewById(R.id.numlinije);

        stanica1.setText(st1);
        stanica2.setText(st2);
        linija.setText("Stanice linije "+lineNumber);

        db = new DatabaseHelper(this);
        File database  = getApplicationContext().getDatabasePath(DatabaseHelper.DBNAME);
        if (false == database.exists()){

            db.getReadableDatabase();
            //kopiranje DB

            if (copyDataBase(this)){
                Toast.makeText(this,"Copy database scusses",Toast.LENGTH_SHORT ).show();

            }else{
                Toast.makeText(this,"Copy database error",Toast.LENGTH_SHORT ).show();
                return;
            }
        }

        //TextView numlinije = (TextView) findViewById(R.id.numlinije);
        //numlinije.setText("Stanice linije - "+lineNumber);

        stanice = db.getListStations(numb,ascDesc);

        listView = (ListView) findViewById(R.id.stanlist);
        listView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent mainIntent = new Intent(StationActivity.this,StationPage.class);
                int stanId = stanice.get(position).getId();
                String name = stanice.get(position).getNameS();
                mainIntent.putExtra("position",stanId);
                mainIntent.putExtra("name",name);
                startActivity(mainIntent);
            }
        });
        sAdapter = new ListStationAdapter(this,stanice);
        listView.setAdapter(sAdapter);





    }

    private boolean copyDataBase (Context context){

        try {
            InputStream inputStream = context.getAssets().open(DatabaseHelper.DBNAME);
            String outFileName = DatabaseHelper.DBLOCATION + DatabaseHelper.DBNAME;

            OutputStream outputStream = new FileOutputStream(outFileName);

            byte [] buff = new byte[1024];
            int length = 0;
            while ((length = inputStream.read(buff)) > 0){
                outputStream.write(buff,0,length);
            }
            outputStream.flush();
            outputStream.close();
            Log.v("MainActivity","DB Copied");
            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    public void backClick(View v){
        Intent myIntent = new Intent(StationActivity.this, LineInfo.class);
        myIntent.putExtra("number",numb);
        StationActivity.this.startActivity(myIntent);

    }

    public  void changeDirection(View v){

        if (ascDesc == 0){
            ascDesc =1;
            stanica2.setText(st1);
            stanica1.setText(st2);
            stanice = db.getListStations(numb,ascDesc);
            listView = (ListView) findViewById(R.id.stanlist);
            sAdapter = new ListStationAdapter(this,stanice);
            listView.setAdapter(sAdapter);

        }else{
            ascDesc=0;
            stanica1.setText(st1);
            stanica2.setText(st2);
            stanice = db.getListStations(numb,ascDesc);
            listView = (ListView) findViewById(R.id.stanlist);
            sAdapter = new ListStationAdapter(this,stanice);
            listView.setAdapter(sAdapter);
        }
    }

}
