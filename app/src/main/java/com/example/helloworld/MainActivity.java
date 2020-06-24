package com.example.helloworld;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.model.ResponseClass;
import com.example.model.request;
import com.example.remote.APIUtils;
import com.example.remote.UserService;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TextView txtUsername;
    String valSelect;
    String data_search;
    UserService userService;
    Button btnSearch;
    Button btnLogout;
    JSONObject data;
    EditText search;
    ListView listItem;
    List<request> itemReq;
    TableLayout tabLay;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtUsername = (TextView) findViewById(R.id.txtUsername);
        userService = APIUtils.getUserService();
        btnSearch = (Button) findViewById(R.id.btnSearch);
        btnLogout = (Button) findViewById(R.id.btnLogout);
        search = (EditText) findViewById(R.id.searchText);
        tabLay = (TableLayout) findViewById(R.id.tableLayout);

        Bundle extras = getIntent().getExtras();

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        if (extras != null) {
            username = extras.getString("username");
            txtUsername.setText("Welcome " + username);
        }

        getData();

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getData();
            }
        });
    }

    public void getData(){
        data_search = search.getText().toString();
        data = new JSONObject();
        try {
            data.put("user_id", username);
            data.put("search_data", data_search);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Call<ResponseClass> call = userService.searchRes(data);
        call.enqueue(new Callback<ResponseClass>() {
            @Override
            public void onResponse(Call<ResponseClass> call, Response<ResponseClass> response) {
                tabLay.removeAllViews();
                itemReq = response.body().getList_request();
                TableRow tabHeader = new TableRow(getApplicationContext());
                TextView h1 = new TextView(getApplicationContext());
                TextView h2 = new TextView(getApplicationContext());
                TextView h3 = new TextView(getApplicationContext());
                h1.setWidth(300);
                h2.setWidth(300);
                h3.setWidth(300);
                h1.setText("name");
                h2.setText("date");
                h3.setText("device");
                h1.setTextColor(Color.BLACK);
                h2.setTextColor(Color.BLACK);
                h3.setTextColor(Color.BLACK);
                tabHeader.addView(h1);
                tabHeader.addView(h2);
                tabHeader.addView(h3);
                tabLay.addView(tabHeader);
                if (itemReq.size() > 0) {
                    for (int i = 0; i < itemReq.size(); i++) {
                        TableRow tabRow = new TableRow(getApplicationContext());
                        String device = itemReq.get(i).getDevice();
                        String date = itemReq.get(i).getRequest_date();
                        String name = itemReq.get(i).getName();
                        TextView tv1 = new TextView(getApplicationContext());
                        TextView tv2 = new TextView(getApplicationContext());
                        TextView tv3 = new TextView(getApplicationContext());
                        tv1.setWidth(300);
                        tv2.setWidth(300);
                        tv3.setWidth(300);
                        tv1.setText(name);
                        tv2.setText(date);
                        tv3.setText(device);
                        tabRow.addView(tv1);
                        tabRow.addView(tv3);
                        tabRow.addView(tv2);
                        tabLay.addView(tabRow);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseClass> call, Throwable t) {
            }
        });
    }
}