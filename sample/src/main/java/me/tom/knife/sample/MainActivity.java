package me.tom.knife.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.clearEditTextDemo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ClearEditTextDemoActivity.class));
            }
        });

        findViewById(R.id.searchEditTextDemo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SearchEditTextDemoActivity.class));
            }
        });
        findViewById(R.id.titleAndValueTextViewDemo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TitleAndValueTextViewDemoActivity.class));
            }
        });
        findViewById(R.id.rightIconTitleAndValueTextViewDemo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RightIconTitleAndValueTextViewActivity.class));
            }
        });
    }
}