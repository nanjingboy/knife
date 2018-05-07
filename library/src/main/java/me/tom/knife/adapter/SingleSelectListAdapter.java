package me.tom.knife.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import me.tom.knife.R;
import me.tom.knife.model.KVMap;

public class SingleSelectListAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;

    private List<KVMap> mData;
    private String mSelectedKey;

    private static class ViewHolder {
        TextView titleTextView;
        ImageView selectedImageView;
    }

    public SingleSelectListAdapter(Context context) {
        mContext = context;
        mData = new ArrayList<>();
        mInflater = LayoutInflater.from(context);
    }

    public void setData(List<KVMap> data) {
        mData = data;
        notifyDataSetChanged();
    }

    public void setSelectedKey(String selectedKey) {
        mSelectedKey = selectedKey;
        notifyDataSetChanged();
    }

    public int getSelectedValuePosition() {
        for (int index = 0; index < mData.size(); index++) {
            if (mData.get(index).key.equals(mSelectedKey)) {
                return index;
            }
        }
        return -1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.single_select_list_item, null, false);
            holder = new ViewHolder();
            holder.titleTextView = convertView.findViewById(R.id.titleTextView);
            holder.selectedImageView = convertView.findViewById(R.id.selectedImageView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        KVMap map = mData.get(position);
        holder.titleTextView.setText(map.value);
        if (mSelectedKey != null && mSelectedKey.equals(map.key)) {
            holder.selectedImageView.setVisibility(View.VISIBLE);
            holder.titleTextView.setTextColor(ContextCompat.getColor(mContext, R.color.colorPrimary));
        } else {
            holder.selectedImageView.setVisibility(View.GONE);
            holder.titleTextView.setTextColor(ContextCompat.getColor(mContext, R.color.colorSingleSelectFont));
        }
        convertView.setBackgroundResource(android.R.color.white);
        return convertView;
    }
}
