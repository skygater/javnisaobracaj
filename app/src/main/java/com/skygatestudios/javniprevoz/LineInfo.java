package com.skygatestudios.javniprevoz;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.desnyki.library.infinitymenu.ChildScrollView;
import com.desnyki.library.infinitymenu.RootScrollView;
import com.skygatestudios.javniprevoz.Adapter.ListStationAdapter;
import com.skygatestudios.javniprevoz.DataBase.DatabaseHelper;
import com.skygatestudios.javniprevoz.Java.Ruta;
import com.skygatestudios.javniprevoz.Java.Stanice;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class LineInfo extends AppCompatActivity {

    DatabaseHelper db;
    Ruta ruta;

    RelativeLayout bar1;
    RelativeLayout bar2;
    RelativeLayout bar3;

    ChildScrollView childScrollView;
    RootScrollView rootScrollView;

    int position = 0;
    int theLine = 0;

    final LinearLayout[] childContainer = new LinearLayout[5]; //used to cache and access child views, no children have been hurt in this container


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_line_info);

        Intent intent = getIntent();
        position = intent.getIntExtra("position", 0);
        theLine = intent.getIntExtra("number",0);

        if(theLine != 0){
            position = theLine;
        }else {
            position = position + 1;
        }



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


        ruta = db.uzetiRutu(position);

        TextView nameR = (TextView) findViewById(R.id.brojlinije);
        TextView startR = (TextView) findViewById(R.id.starts);
        TextView endR = (TextView) findViewById(R.id.ends);
        TextView staniRut = (TextView) findViewById(R.id.stanicaLinija);

        TextView firstStart = (TextView)  findViewById(R.id.firststart);
        TextView firstEnd = (TextView) findViewById(R.id.firstend);

        TextView secondStart = (TextView)  findViewById(R.id.secondstart);
        TextView secondEnd = (TextView) findViewById(R.id.secondend);




        nameR.setText("Linija - "+ruta.getNameR());
        staniRut.setText("Stanice linije "+ruta.getNameR());
        startR.setText(ruta.getStartR());
        endR.setText(ruta.getEndR());

        firstStart.setText(ruta.getFirstStart());
        firstEnd.setText(ruta.getFirstEnd());
        secondEnd.setText(ruta.getSecondEnd());
        secondStart.setText(ruta.getSecondStart());


        // Za Scroll

        rootScrollView = (RootScrollView) findViewById(R.id.menu_scroll_view);
        childScrollView = (ChildScrollView) findViewById(R.id.child_scroll_view);

        childScrollView.setBackgroundScrollView(rootScrollView);
        childScrollView.setCloseDistance(50);

        bar1 = (RelativeLayout) findViewById(R.id.bar1);
        childContainer[1] = (LinearLayout) getLayoutInflater().inflate(R.layout.time_item, null);
        bar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                childScrollView.addView(childContainer[1], 0, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                childScrollView.openWithAnim(bar1,false,true);
                TextView startTable = (TextView) findViewById(R.id.timetable);
                startTable.setText(ruta.getStartTable());
            }
        });
        bar2 = (RelativeLayout) findViewById(R.id.bar2);
        childContainer[2] = (LinearLayout) getLayoutInflater().inflate(R.layout.time_item2, null);
        bar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                childScrollView.addView(childContainer[2], 0, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                childScrollView.openWithAnim(bar2,false,true);
                TextView endTable = (TextView) findViewById(R.id.timetable2);
               endTable.setText(ruta.getEndTable());
            }
        });



        bar3 = (RelativeLayout) findViewById(R.id.bar3);
       // childContainer[3] = (LinearLayout) getLayoutInflater().inflate(R.layout.station_list, null);
        bar3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainIntent = new Intent(LineInfo.this,StationActivity.class);
                mainIntent.putExtra("position",position);
                mainIntent.putExtra("brLinije", ruta.getNameR());
                mainIntent.putExtra("start",ruta.getStartR());
                mainIntent.putExtra("end",ruta.getEndR());
                startActivity(mainIntent);

            }
        });

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
        Intent myIntent = new Intent(LineInfo.this, HomeActivity.class);
       LineInfo.this.startActivity(myIntent);

    }

}
