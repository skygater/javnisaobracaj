package com.skygatestudios.javniprevoz.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.skygatestudios.javniprevoz.Java.Lines;
import com.skygatestudios.javniprevoz.R;

import java.util.List;

/**
 * Created by djordjekalezic on 26/10/2016.
 */

public class ListLineAdapter extends BaseAdapter {

    private Context mContext;
    private List<Lines> mLines;

    public ListLineAdapter(Context mContext, List<Lines> mLines) {
        this.mContext = mContext;
        this.mLines = mLines;
    }

    @Override
    public int getCount() {
        return mLines.size();
    }

    @Override
    public Object getItem(int position) {
        return mLines.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mLines.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = View.inflate(mContext, R.layout.item_layout,null);

        TextView tvDest= (TextView) v.findViewById(R.id.lineNumber);
        TextView tvName = (TextView) v.findViewById(R.id.lineName);
        TextView tvnameEnd = (TextView) v.findViewById(R.id.lineNumberEnd);


        tvName.setText(mLines.get(position).getName());
        tvDest.setText(mLines.get(position).getStart());
        tvnameEnd.setText(mLines.get(position).getEnd());

        v.setTag( mLines.get(position).getId());


        return v;
    }


}
