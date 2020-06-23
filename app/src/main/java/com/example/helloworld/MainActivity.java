package com.example.helloworld;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView txtUsername;
    ListView lvDevice;
    String[] device = {};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtUsername = (TextView) findViewById(R.id.txtUsername);
        lvDevice = (ListView) findViewById(R.id.lvDevice);

        Bundle extras = getIntent().getExtras();
        String username;
//        String username;

        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, device);
        lvDevice.setAdapter(arrayAdapter);

        if(extras != null){
            username = extras.getString("username");
            txtUsername.setText("Welcome " + username);
        }

    }
}