package me.tom.knife.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import me.tom.knife.adapter.SingleSelectListAdapter;
import me.tom.knife.model.KVMap;

abstract public class AbstractSingleSelectListActivity extends AppCompatActivity {

    private KVMap mNoneValue;
    private ListView mListView;
    private SingleSelectListAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView();
        mNoneValue = getNoneValue();
        mAdapter = new SingleSelectListAdapter(this);
        String key = getIntent().getStringExtra("key");
        if (key == null && mNoneValue != null) {
            mAdapter.setSelectedKey(mNoneValue.key);
        } else {
            mAdapter.setSelectedKey(key);
        }
        mListView = findViewById(getListViewId());
        mListView.setAdapter(mAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                KVMap map = (KVMap) mAdapter.getItem(position);
                mAdapter.setSelectedKey(map.key);
                onListItemClick(map);
            }
        });
    }

    abstract protected KVMap getNoneValue();
    abstract protected int getListViewId();
    abstract protected void setContentView();

    protected void onListItemClick(KVMap data) {
        String key = data.key;
        String value = data.value;
        if (mNoneValue != null && key.equals(mNoneValue.key)) {
            key = null;
            value = null;
        }
        Intent intent = new Intent();
        intent.putExtra("key", key);
        intent.putExtra("value", value);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    protected void reloadData(List<KVMap> data) {
        if (mNoneValue != null) {
            data.add(0, mNoneValue);
        }
        mAdapter.setData(data);
        initListViewSelection();
    }

    protected void initListViewSelection() {
        int position = mAdapter.getSelectedValuePosition();
        if (position != -1) {
            position -= 1;
        }
        mListView.setSelection(position);
    }
}
