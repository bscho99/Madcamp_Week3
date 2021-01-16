package com.example.madcamp_week3;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface JsonPlaceHolderAPI {

    @GET("restfulapi/users/")
    Call<List<User>> getUsers();

    @POST("restfulapi/users/")
    Call<User> createUser(@Body User user);

    @PUT("restfulapi/users/{pk}/")
    Call<User> updateUser(@Body User user, @Path("pk") int pk);

    @DELETE("restfulapi/users/{pk}/")
    Call<User> deleteUser(@Path("pk") int pk);

    @GET("restfulapi/playlists/")
    Call<List<Playlist>> getPlaylists();

    @POST("restfulapi/playlists/")
    Call<Playlist> createPlaylist(@Body Playlist playlist);

    @PUT("restfulapi/playlists/{pk}/")
    Call<Playlist> updatePlaylist(@Body Playlist playlist, @Path("pk") int pk);

    @DELETE("restfulapi/playlists/{pk}/")
    Call<Playlist> deletePlaylist(@Path("pk") int pk);


}