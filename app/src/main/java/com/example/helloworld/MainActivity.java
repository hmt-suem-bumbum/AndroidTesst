package com.example.helloworld;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
        search = (EditText) findViewById(R.id.searchText);
        tabLay = (TableLayout) findViewById(R.id.tableLayout);

        Bundle extras = getIntent().getExtras();


        if (extras != null) {
            username = extras.getString("username");
            txtUsername.setText("Welcome " + username);
        }
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        data_search = search.getText().toString();
        data = new JSONObject();
        try {
            data.put("user_id", username);
            data.put("search_data", data_search);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<ResponseClass> call = userService.searchRes(data);
                call.enqueue(new Callback<ResponseClass>() {
                    @Override
                    public void onResponse(Call<ResponseClass> call, Response<ResponseClass> response) {
                        tabLay.removeAllViews();
                        itemReq = response.body().getList_request();
                        if (itemReq.size() > 0) {
                            for (int i = 0; i < itemReq.size(); i++) {
                                TableRow tabRow = new TableRow(getApplicationContext());
                                String device = itemReq.get(i).getDevice();
                                String date = itemReq.get(i).getRequest_date();
                                String name = itemReq.get(i).getName();
                                TextView tv1 = new TextView(getApplicationContext());
                                TextView tv2 = new TextView(getApplicationContext());
                                TextView tv3 = new TextView(getApplicationContext());
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
        });
    }
}