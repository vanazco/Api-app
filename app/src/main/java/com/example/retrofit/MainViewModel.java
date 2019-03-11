package com.example.retrofit;


import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.retrofit.model.Caracter;
import com.example.retrofit.model.Episode;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private AnimedbRepository animedbRepository;

    public MainViewModel(@NonNull Application application) {
        super(application);
        animedbRepository = new AnimedbRepository();
    }

    public LiveData<List<Episode>> getAnimes(){
        return animedbRepository.getAnimes();
    }

    public LiveData<List<Caracter>> getCharacters(){
        return animedbRepository.getCharacters();
    }
}
