package com.example.marin.qrticket.util;

import com.example.marin.qrticket.model.Evento;
import com.example.marin.qrticket.model.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by marin on 02/04/2019.
 */

public interface RetrofitUtil {

    //USUARIO ---------------------------------------------------------------------

    @POST("usuarios")
    Call<Void> inserirUsuario(@Body Usuario usuario);

    @POST("usuarios/login")
    Call<Usuario> logar(@Body Usuario usuario);

    @PUT("usuarios")
    Call<Void> editarUsuario(@Body Usuario usuario);

    @GET("usuarios/{id}")
    Call<Usuario> pegarIdUsuario(@Path("id") int id);

    @DELETE("usuarios/{id}")
    Call<Void> deletarUsuario(@Path("id") int id);


   // -------------------------------------------------------------------------------

    //EVENTO-------------------------------------------------------------------------

    @GET("eventos")
    Call<List<Evento>> listarEventos();

    @GET("eventos/{id}")
    Call<Evento>pegarIdEvento(@Path("id") int id);


    //-------------------------------------------------------------------------------
    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.137.1/testeAPI/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

}

