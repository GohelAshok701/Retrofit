package com.retrofit;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.retrofit.adapter.URLAdapter;
import com.retrofit.model.Example;
import com.retrofit.rest.ApiConstant;
import com.retrofit.rest.WebServiceCaller;
import com.retrofit.util.Utility;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    private RecyclerView rv_url;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv_url=findViewById(R.id.rv_url);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rv_url.setLayoutManager(linearLayoutManager);

        getUrls();

    }

    private void getUrls() {
        try {
            if (!Utility.isNetworkAvailable(this)) {
                Toast.makeText(this, R.string.no_internet_connection, Toast.LENGTH_SHORT).show();
            } else {
                progressDialog=new ProgressDialog(this);
                progressDialog.setMessage(getString(R.string.please_wait));
                progressDialog.setCancelable(false);
                progressDialog.show();
                WebServiceCaller.ApiInterface service = WebServiceCaller.getClient();
                Call<Example> call = service.getUrls(ApiConstant.Video); //TODO need to pass USER_ID.
                call.enqueue(new Callback<Example>() {
                    @Override
                    public void onResponse(Call<Example> call, Response<Example> response) {
                        if (response.isSuccessful()) {
                            progressDialog.dismiss();
                            List<Example.File> files=new ArrayList<>();
                            files.addAll(response.body().getFile());
                            URLAdapter urlAdapter = new URLAdapter(MainActivity.this,files);
                            rv_url.setAdapter(urlAdapter);
                        }
                    }

                    @Override
                    public void onFailure(Call<Example> call, Throwable t) {
                        t.printStackTrace();
                        progressDialog.dismiss();
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
