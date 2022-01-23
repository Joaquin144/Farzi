package com.vibhu.nitjsr.farzi.catfact;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    String BASE_URL = "https://catfact.ninja/";
    @GET("fact")
    //Call<List<FactResult>> publishRandomFact();
    Call<FactResult> publishRandomFact();
}
