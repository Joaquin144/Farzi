package com.vibhu.nitjsr.farzi.catfact;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/*                          ###########################
        Baar baar retrofit2 ka instance bana kar api call karna is a bad idea
        so we make one instance and use t repeatedly to call api
 */
public class RetrofitClient {

    private static RetrofitClient instance = null;
    private Api myApi;

    public RetrofitClient() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        myApi = retrofit.create(Api.class);
    }

    public static synchronized RetrofitClient getInstance(){
        if(instance == null)
            instance = new RetrofitClient();
        return instance;
    }

    public Api getMyApi() {
        return myApi;
    }

}
