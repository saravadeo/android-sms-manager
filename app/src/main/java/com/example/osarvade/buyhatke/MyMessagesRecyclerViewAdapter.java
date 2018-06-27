package com.example.osarvade.buyhatke;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.osarvade.buyhatke.MessagesFragment.OnListFragmentInteractionListener;
import com.example.osarvade.buyhatke.dummy.DummyContent.DummyItem;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author onkar
 */
public class MyMessagesRecyclerViewAdapter extends RecyclerView.Adapter<MyMessagesRecyclerViewAdapter.ViewHolder> {

    private final List<DummyItem> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MyMessagesRecyclerViewAdapter(List<DummyItem> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_messages, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        SimpleDateFormat dtFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        holder.mItem = mValues.get(position);
        holder.name.setText(mValues.get(position).getNumberName());
        holder.latest_message.setText(mValues.get(position).getLatestMessage());
        holder.list_image.setText(mValues.get(position).getNumberName().charAt(0) + "");
        holder.time.setText(dtFormat.format(mValues.get(position).getTime()).toString());
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
        public final TextView name;
        public final TextView list_image;
        public final TextView latest_message;
        public final TextView time;
        public DummyItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            name = (TextView) view.findViewById(R.id.name);
            list_image = (TextView) view.findViewById(R.id.list_image);
            latest_message = (TextView) view.findViewById(R.id.latest_message);
            time = (TextView) view.findViewById(R.id.time);
        }

    }
}
