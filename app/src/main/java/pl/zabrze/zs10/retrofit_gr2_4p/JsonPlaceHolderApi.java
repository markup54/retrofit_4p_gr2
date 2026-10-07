package pl.zabrze.zs10.retrofit_gr2_4p;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {
    @GET("db.json")
    public Call<List<Pytanie>> getPytania();
}
