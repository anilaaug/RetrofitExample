package com.example.anila.retrofitexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.anila.retrofitexample.model.GetDataService;
import com.example.anila.retrofitexample.model.RetroPhoto;
import com.example.anila.retrofitexample.networks.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
       private RecyclerView mRecyclerView;
       private  CustomAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GetDataService service= RetrofitClient.getRetrofitInstance().create(GetDataService.class);
        Call<List<RetroPhoto>> call=service.getAllPhotos();
        call.enqueue(new Callback<List<RetroPhoto>>() {
            @Override
            public void onResponse(Call<List<RetroPhoto>> call, Response<List<RetroPhoto>> response) {
                generateDataList(response.body());

            }

            @Override
            public void onFailure(Call<List<RetroPhoto>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Something went wrong please try again later", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void generateDataList(List<RetroPhoto> photoList) {
        mRecyclerView=findViewById(R.id.recycler_view);
        mAdapter=new CustomAdapter(this,photoList);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(MainActivity.this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);

    }
}
