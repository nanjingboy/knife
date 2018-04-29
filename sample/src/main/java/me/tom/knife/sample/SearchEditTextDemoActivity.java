package me.tom.knife.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import me.tom.knife.SearchEditText;

public class SearchEditTextDemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_edit_text_demo);
        SearchEditText editText = findViewById(R.id.searchEditText);
        editText.setListener(new SearchEditText.ISearchEditListener() {
            @Override
            public void onSubmit(String value) {
                Toast.makeText(SearchEditTextDemoActivity.this, "On Submit:" + value, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
