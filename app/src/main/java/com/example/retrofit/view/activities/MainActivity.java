package com.example.retrofit.view.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.example.retrofit.MainViewModel;
import com.example.retrofit.R;
import com.example.retrofit.model.Caracter;
import com.example.retrofit.model.Episode;
import com.example.retrofit.view.fragments.CharacterListFragment;
import com.example.retrofit.view.fragments.EpisodeListFragment;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MainViewModel mViewModel, getmViewModel;
    private RecyclerView mRecyclerView, getmRecyclerView;
    private EpisodeListFragment.AnimeListAdapter mAnimeListAdapter;
    private CharacterListFragment.CharacterListAdapter mCharacterListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.animeList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Segunda lista
        getmRecyclerView = findViewById(R.id.characterList);
        getmRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAnimeListAdapter = new EpisodeListFragment.AnimeListAdapter();
        mRecyclerView.setAdapter(mAnimeListAdapter);

        //Segundo adapter
        mCharacterListAdapter = new CharacterListFragment.CharacterListAdapter();
        getmRecyclerView.setAdapter(mCharacterListAdapter);

        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        mViewModel.getAnimes().observe(this, new Observer<List<Episode>>() {
            @Override
            public void onChanged(@Nullable List<Episode> episodes) {
                mAnimeListAdapter.episodeList = episodes;
                mAnimeListAdapter.notifyDataSetChanged();
            }
        });

        getmViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        getmViewModel.getCharacters().observe(this, new Observer<List<Caracter>>() {
            @Override
            public void onChanged(@Nullable List<Caracter> caracters) {
                mCharacterListAdapter.caracterList = caracters;
                mCharacterListAdapter.notifyDataSetChanged();
            }
        });

    }
}
