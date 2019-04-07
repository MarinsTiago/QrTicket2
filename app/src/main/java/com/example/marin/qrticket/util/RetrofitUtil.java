package com.example.marin.qrticket.util;

import com.example.marin.qrticket.model.Usuario;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by marin on 02/04/2019.
 */

public interface RetrofitUtil {


    @GET("")
    Call<Usuario> logar(@Path("login") String login, @Path("senha") String senha);

    public static final Retrofit retrofit = new Retrofit.Builder().baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(GsonConverterFactory.create()).build();

}

