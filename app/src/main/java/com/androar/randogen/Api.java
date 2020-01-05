package com.androar.randogen;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by BG on 05/01/2020.
 */

public interface Api {
    String BASE_URL = "http://goforbg.com/";

    @GET("/friendss1.json")
    Call<List<Episode>> getEpisodes();
}