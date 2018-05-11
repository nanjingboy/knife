package me.tom.knife;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import me.tom.knife.adapter.SingleSelectListAdapter;
import me.tom.knife.model.KVMap;

public class SingleSelectDialog {

    private AlertDialog mAlertDialog;
    private SingleSelectListAdapter mAdapter;

    private IItemClickListener mListener;

    public interface IItemClickListener {
        void onItemClick(KVMap map);
    }

    public SingleSelectDialog(Context context, String[] values) {
        ArrayList<KVMap> data = new ArrayList<>();
        for (String value: values) {
            KVMap map = new KVMap();
            map.key = value;
            map.value = value;
            data.add(map);
        }
        init(context, data);
    }

    public SingleSelectDialog(Context context, ArrayList<KVMap> data) {
        init(context, data);
    }

    public void show(String selectedValue, IItemClickListener listener) {
        mListener = listener;
        mAdapter.setSelectedKey(selectedValue);
        if (!isShowing()) {
            mAlertDialog.show();
        }
    }

    private void init(Context context, ArrayList<KVMap> data) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.dialog_single_select, null);
        ListView listView = view.findViewById(R.id.listView);
        mAdapter = new SingleSelectListAdapter(context);
        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                KVMap map = (KVMap) mAdapter.getItem(position);
                mAdapter.setSelectedKey(map.key);
                if (mListener != null) {
                    mListener.onItemClick(map);
                }
                dismiss();
            }
        });
        mAdapter.setData(data);
        mAlertDialog = new AlertDialog.Builder(context).create();
        mAlertDialog.getWindow().setGravity(Gravity.CENTER);
        mAlertDialog.setCancelable(true);
        mAlertDialog.setCanceledOnTouchOutside(true);
        mAlertDialog.setView(view);
    }

    private boolean isShowing() {
        return mAlertDialog.isShowing();
    }

    private void dismiss() {
        if (isShowing()) {
            mAlertDialog.dismiss();
        }
    }
}
