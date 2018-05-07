package me.tom.knife.sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import me.tom.knife.TitleEditText;

public class MainActivity extends AppCompatActivity {

    static final int REQUEST_USER_SELECT = 32;

    private String mSelectedUserKey;


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
        findViewById(R.id.titleEditTextDemo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TitleEditTextDemoActivity.class));
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

        findViewById(R.id.singleSelectListActivityDemo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, UserSingleSelectListActivity.class);
                intent.putExtra("key", mSelectedUserKey);
                startActivityForResult(intent, REQUEST_USER_SELECT);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case REQUEST_USER_SELECT:
                    mSelectedUserKey = data.getStringExtra("key");
                    Toast.makeText(this, "Selected User:" + mSelectedUserKey, Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
}