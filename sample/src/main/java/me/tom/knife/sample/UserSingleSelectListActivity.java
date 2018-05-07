package me.tom.knife.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import me.tom.knife.activity.AbstractSingleSelectListActivity;
import me.tom.knife.model.KVMap;

public class UserSingleSelectListActivity extends AbstractSingleSelectListActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<KVMap> data = new ArrayList<>();
        for (int index = 0; index < 12; index++) {
            KVMap map = new KVMap();
            map.key = "Tom-" + index;
            map.value = "Tom-" + index;
            data.add(map);
        }
        reloadData(data);
    }

    @Override
    protected ListView setContentView() {
        setContentView(R.layout.activity_user_single_select);
        return findViewById(R.id.listView);
    }
}
