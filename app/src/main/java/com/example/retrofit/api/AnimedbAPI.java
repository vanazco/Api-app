package com.example.retrofit.api;

import com.example.retrofit.model.AnimeCharactersResponse;
import com.example.retrofit.model.AnimeEpisodesList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AnimedbAPI {

    @GET("/v3/anime/2001/episodes")
    Call<AnimeEpisodesList> getAnimes();

    @GET("/v3/anime/2001/characters_staff")
    Call<AnimeCharactersResponse> getCharacters();
}
