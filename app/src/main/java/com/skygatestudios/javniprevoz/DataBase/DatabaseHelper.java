package com.skygatestudios.javniprevoz.DataBase;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;
import android.widget.TextView;

import com.skygatestudios.javniprevoz.Java.Lines;
import com.skygatestudios.javniprevoz.Java.Ruta;
import com.skygatestudios.javniprevoz.Java.Stanice;
import com.skygatestudios.javniprevoz.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by djordjekalezic on 26/10/2016.
 */

public class DatabaseHelper extends SQLiteOpenHelper {


    public static final  String DBNAME = "testdb4.db";
    public static final String DBLOCATION = "/data/data/com.skygatestudios.javniprevoz/databases/";

    private Context mContext;
    private SQLiteDatabase mDatabase;

    public DatabaseHelper (Context context){
        super(context,DBNAME,null,1);
        this.mContext = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void openDatabase (){

        String dbPath = mContext.getDatabasePath(DBNAME).getPath();

        if(mDatabase != null && mDatabase.isOpen()){
            return;
        }
        mDatabase = SQLiteDatabase.openDatabase(dbPath,null,SQLiteDatabase.OPEN_READWRITE);

    }

    public void closeDatabase(){
        if(mDatabase != null) {
            mDatabase.close();
        }
    }

    public List<Lines> getListLines (){
        Lines lines = null;
        List<Lines> lineList = new ArrayList<>();
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM RUTA", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            lines = new Lines(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3));
            lineList.add(lines);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return lineList;
    }


        public Ruta uzetiRutu (int id) {
            Ruta ruta = null;
            openDatabase();
            Cursor cursor = mDatabase.rawQuery("Select r.namer,r.startr,r.endr,t.starttable,t.endtable, t.firststart,t.firstend,t.secondstart,t.secondend  from ruta r, timeTable t where r.idrut =? and t.idt = r.idrut; ", new String[]{String.valueOf(id)});// kako si ubacio ID
            cursor.moveToFirst();
            ruta = new Ruta( cursor.getString(0), cursor.getString(1), cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6),cursor.getString(7),cursor.getString(8));
            //Only 1 resul
            cursor.close();
            closeDatabase();
            return ruta;
        }

    public List<Stanice> getListStations (int id,int sort){
        Stanice stanice = null;
        List<Stanice> lineList = new ArrayList<>();
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("Select s.idstan, s.names from stanica s, konekcija k where s.IDSTAN = k.STANICA and k.ruta = ? order by k.sortStat asc ", new String[]{String.valueOf(id)});
        if(sort != 0) {
             cursor = mDatabase.rawQuery("Select s.idstan, s.names from stanica s, konekcija k where s.IDSTAN = k.STANICA and k.ruta = ? order by k.sortStat desc ", new String[]{String.valueOf(id)});
        }else {

        }
            cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            stanice = new Stanice(cursor.getInt(0),cursor.getString(1));
            lineList.add(stanice);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return lineList;
    }






}
