package com.vibhu.nitjsr.farzi.catfact;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.vibhu.nitjsr.farzi.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView factTv,lenghtTV;
    private SwipeRefreshLayout refreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        factTv=findViewById(R.id.idFactTV);
        lenghtTV=findViewById(R.id.idLengthTV);
        refreshLayout=findViewById(R.id.refreshLayout);
        publishRandomFact();
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                publishRandomFact();
                refreshLayout.setRefreshing(false);
            }
        });
    }
    /*private void publishRandomFact(){
        Call<List<FactResult>> call = RetrofitClient.getInstance().getMyApi().publishRandomFact();
        call.enqueue(new Callback<List<FactResult>>() {
            @Override
            public void onResponse(Call<List<FactResult>> call, Response<List<FactResult>> response) {
                Log.d("####",""+(response==null));//response null nahi hai as per results
                if(response.isSuccessful()){
                    //List<FactResult> list = response.body();

                    Log.d("####",""+list.size());
                    String factKyaHai = list.get(0).getFact();
                    String lengthKyaHai = "" + list.get(0).getLength();
                    factTv.setText(factKyaHai);
                    lenghtTV.setText(lengthKyaHai);
                }
                else {
                    List<FactResult> list = response.body();
                    Log.d("####",""+list.size());
                    String factKyaHai = list.get(0).getFact();
                    String lengthKyaHai = "" + list.get(0).getLength();
                    factTv.setText(factKyaHai);
                    lenghtTV.setText(lengthKyaHai);
                    Log.d("####", "response failed");
                }
            }

            @Override
            public void onFailure(Call<List<FactResult>> call, Throwable t) {
                Log.d("####","Some Error occured "+t.getMessage());
            }
        });
    }
     */
    private void publishRandomFact(){
        Call<FactResult> call = RetrofitClient.getInstance().getMyApi().publishRandomFact();
        call.enqueue(new Callback<FactResult>() {
            @Override
            public void onResponse(Call<FactResult> call, Response<FactResult> response) {

                FactResult fr = new FactResult("kuch nahi",9);

                fr=response.body();
                Log.d("####",""+response.code());

                String factKyaHai = fr.getFact();
                Log.d("#### fact ",factKyaHai);
                String lengthKyaHai = ""+fr.getLength();
                if(response.isSuccessful()){

                    factTv.setText(factKyaHai);
                    lenghtTV.setText(lengthKyaHai);
                }
                else{
                    Log.d("####", "response failed  "+factKyaHai);
                }
            }

            @Override
            public void onFailure(Call<FactResult> call, Throwable t) {
                Log.d("####","Some Error occured "+t.getMessage());
            }
        });
    }
}