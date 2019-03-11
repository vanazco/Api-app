package com.example.retrofit;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.example.retrofit.api.AnimedbAPI;
import com.example.retrofit.api.MoviedbModule;
import com.example.retrofit.model.AnimeCharactersResponse;
import com.example.retrofit.model.AnimeEpisodesList;
import com.example.retrofit.model.Caracter;
import com.example.retrofit.model.Episode;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AnimedbRepository {
    AnimedbAPI animedbAPI;

    public AnimedbRepository(){
        animedbAPI = MoviedbModule.getAPI();
    }

    public LiveData<List<Episode>> getAnimes(){
        final MutableLiveData<List<Episode>> lista = new MutableLiveData<>();

        animedbAPI.getAnimes().enqueue(new Callback<AnimeEpisodesList>() {
            @Override
            public void onResponse(Call<AnimeEpisodesList> call, Response<AnimeEpisodesList> response) {
                lista.setValue(response.body().episodes);
            }

            @Override
            public void onFailure(Call<AnimeEpisodesList> call, Throwable t) {
            }
        });

        return lista;
    }


    public LiveData<List<Caracter>> getCharacters(){
        final MutableLiveData<List<Caracter>> listchar = new MutableLiveData<>();

        animedbAPI.getCharacters().enqueue(new Callback<AnimeCharactersResponse>() {
            @Override
            public void onResponse(Call<AnimeCharactersResponse> call, Response<AnimeCharactersResponse> response) {
                listchar.setValue(response.body().characters);
            }

            @Override
            public void onFailure(Call<AnimeCharactersResponse> call, Throwable t) {
            }
        });

        return listchar;
    }


}