package com.tim.tdlibrary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    private String [] data ={"验证码"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,R.layout.item_main,data);//适配器
        ListView listView = (ListView) findViewById(R.id.SimpleListView); //找到ListView布局
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0 :
                        MainActivity.this.startActivity(new Intent(MainActivity.this, ActivationActivity.class));
                        break;
                    default:
                        break;
                }
            }
        });
    }
}
