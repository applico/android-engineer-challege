package com.applicotest.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.applicotest.R;
import com.applicotest.model.Data;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nirajan on 9/23/2015.
 */
public class MyAdapter extends BaseAdapter {
    private Context context;
    private List<Data> dataList = new ArrayList<Data>();

    public MyAdapter(Context context, List<Data> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    /**
     * How many items are in the data set represented by this Adapter.
     *
     * @return Count of items.
     */
    @Override
    public int getCount() {
        return dataList.size();
    }

    /**
     * Get the data item associated with the specified position in the data set.
     *
     * @param position Position of the item whose data we want within the adapter's
     *                 data set.
     * @return The data at the specified position.
     */
    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    /**
     * Get the row id associated with the specified position in the list.
     *
     * @param position The position of the item within the adapter's data set whose row id we want.
     * @return The id of the item at the specified position.
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Get a View that displays the data at the specified position in the data set. You can either
     * create a View manually or inflate it from an XML layout file. When the View is inflated, the
     * parent View (GridView, ListView...) will apply default layout parameters unless you use
     * {@link LayoutInflater#inflate(int, ViewGroup, boolean)}
     * to specify a root view and to prevent attachment to the root.
     *
     * @param position    The position of the item within the adapter's data set of the item whose view
     *                    we want.
     * @param convertView The old view to reuse, if possible. Note: You should check that this view
     *                    is non-null and of an appropriate type before using. If it is not possible to convert
     *                    this view to display the correct data, this method can create a new view.
     *                    Heterogeneous lists can specify their number of view types, so that this View is
     *                    always of the right type (see {@link #getViewTypeCount()} and
     *                    {@link #getItemViewType(int)}).
     * @param parent      The parent that this view will eventually be attached to
     * @return A View corresponding to the data at the specified position.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.person_image);
            viewHolder.personName = (TextView) convertView.findViewById(R.id.sender_name);
            viewHolder.commitUrl = (TextView) convertView.findViewById(R.id.commit_url);
            viewHolder.commitMsg = (TextView) convertView.findViewById(R.id.commit_msg);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Data item = (Data) getItem(position);

        viewHolder.personName.setText(item.getCommit().getAuthor().getName());
        viewHolder.commitUrl.setText(item.getCommit().getCommitUrl());
        viewHolder.commitMsg.setText(item.getCommit().getMessage());

        Picasso.with(context)
                .load(item.getCommitter().getImageUrl())
                .placeholder(R.drawable.no_image)
                .error(R.drawable.no_image)
                .tag(context)
                .into(viewHolder.imageView);


        return convertView;
    }


    static class ViewHolder {
        ImageView imageView;
        TextView personName;
        TextView commitUrl;
        TextView commitMsg;
    }
}
