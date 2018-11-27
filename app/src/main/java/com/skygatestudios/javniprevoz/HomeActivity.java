package com.skygatestudios.javniprevoz;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.skygatestudios.javniprevoz.Adapter.ListLineAdapter;
import com.skygatestudios.javniprevoz.DataBase.DatabaseHelper;
import com.skygatestudios.javniprevoz.Java.Lines;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private ListView lw;
    private ListLineAdapter adapter;

    private List<Lines> mLines;
    private DatabaseHelper dbHelp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        dbHelp = new DatabaseHelper(this);
        //Provjera da li postoji bazaa!

        File database  = getApplicationContext().getDatabasePath(DatabaseHelper.DBNAME);
        if (false == database.exists()){

            dbHelp.getReadableDatabase();
            //kopiranje DB

            if (copyDataBase(this)){
                Toast.makeText(this,"Copy database scusses",Toast.LENGTH_SHORT ).show();

            }else{
                Toast.makeText(this,"Copy database error",Toast.LENGTH_SHORT ).show();
                return;
            }
        }

        //Dobijanje Listee

        mLines = dbHelp.getListLines();

        // Inicijalizacija adaptera

        adapter = new ListLineAdapter(this, mLines);

        //Postavljanje adaptera za ListView

        lw.setAdapter(adapter);




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



    @Override
    public void onBackPressed() {
        finish();
        moveTaskToBack(true);


    }


}
