package com.example.osarvade.contactmessage;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.osarvade.buyhatke.R;
import com.example.osarvade.contactmessage.ContactMesaagesFragment.OnListFragmentInteractionListener;
import com.example.osarvade.contactmessage.dummy.DummyContent.DummyItem;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author onkar
 */
public class MyContactMesaagesRecyclerViewAdapter extends RecyclerView.Adapter<MyContactMesaagesRecyclerViewAdapter.ViewHolder> {

    private final List<DummyItem> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyContactMesaagesRecyclerViewAdapter(List<DummyItem> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_contactmesaages, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        SimpleDateFormat dtFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        holder.mItem = mValues.get(position);
        holder.message.setText(mValues.get(position).getMessage());
        holder.time.setText(dtFormat.format(mValues.get(position).getTime()).toString());

        if (mValues.get(position).getIsRorS() != 1) {
            holder.mView.setBackgroundResource(R.color.colorPrimary);
            holder.mView.setPadding(20, 0, 0, 0);

        } else {
            holder.mView.setBackgroundColor(Color.WHITE);
            holder.mView.setPadding(0, 0, 20, 0);
        }
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView message;
        public final TextView time;
        public DummyItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            message = (TextView) view.findViewById(R.id.message);
            time = (TextView) view.findViewById(R.id.time);
        }
    }

}
