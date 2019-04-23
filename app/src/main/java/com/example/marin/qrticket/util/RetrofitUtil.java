package com.example.marin.qrticket.util;

import com.example.marin.qrticket.model.Empresa;
import com.example.marin.qrticket.model.Usuario;

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


    //EMPRESA ----------------------------------------------------------------------

    @POST("empresas")
    Call<Void> inserirEmpresa(@Body Empresa empresa);

    @PUT("empresas")
    Call<Void> editarEmpresa(@Body Empresa empresa);

    @GET("empresas/{id}")
    Call<Empresa> pegarIdEmpresa(@Path("id") int id);

    @DELETE("empresas/{id}")
    Call<Void> deletarEmpresa(@Path("id") int id);

    // ----------------------------------------------------------------------------


    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.137.1/testeAPI/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

}

