package com.skygatestudios.javniprevoz.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.skygatestudios.javniprevoz.Java.Stanice;
import com.skygatestudios.javniprevoz.R;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by djordjekalezic on 31/10/2016.
 */

public class ListStationAdapter extends BaseAdapter {
    private Context mContext;
    private List<Stanice> mStations;

    public ListStationAdapter(Context mContext, List<Stanice> mStations) {
        this.mContext = mContext;
        this.mStations = mStations;
    }



    @Override
    public int getCount() {
        return mStations.size();
    }

    @Override
    public Object getItem(int position1) {
        return mStations.get(position1);
    }

    @Override
    public long getItemId(int position1) {
        return mStations.get(position1).getId();
    }

    @Override
    public View getView(int position1, View convertView, ViewGroup parent) {

        View v = View.inflate(mContext, R.layout.station_items,null);

      //  TextView textView =(TextView) v.findViewById(R.id.idSt);
        TextView tvDest= (TextView) v.findViewById(R.id.stanicadb);

//        textView.setText(mStations.get(position1).getId());
        tvDest.setText(mStations.get(position1).getNameS());

        v.setTag( mStations.get(position1).getId());
        return v;
    }
}
