package com.example.retrofit.view.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.retrofit.GlideApp;
import com.example.retrofit.MainViewModel;
import com.example.retrofit.R;
import com.example.retrofit.model.Episode;

import java.util.ArrayList;
import java.util.List;


public class EpisodeListFragment extends Fragment {
    private MainViewModel mViewModel;
    private RecyclerView mRecyclerView;
    private AnimeListAdapter mAnimeListAdapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_anime_list, container, false);


        mRecyclerView = view.findViewById(R.id.animeList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mAnimeListAdapter = new AnimeListAdapter();
        mRecyclerView.setAdapter(mAnimeListAdapter);

        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        mViewModel.getAnimes().observe(this, new Observer<List<Episode>>() {
            @Override
            public void onChanged(@Nullable List<Episode> episodes) {
                mAnimeListAdapter.episodeList = episodes;
                mAnimeListAdapter.notifyDataSetChanged();
            }
        });
        return view;
    }

   public static class AnimeListAdapter extends RecyclerView.Adapter<AnimeListAdapter.AnimeListViewHolder>{
        public List<Episode> episodeList = new ArrayList<>();

        @NonNull
        @Override
        public AnimeListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_anime, parent, false);
            return new AnimeListViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull AnimeListViewHolder holder, int position) {
            Episode episode = episodeList.get(position);

            holder.title.setText(episode.title);
            holder.title_jap.setText(episode.title_japanese);
            holder.title_romanji.setText(episode.title_romanji);
            holder.string.setText(episode.aired.string);
//            GlideApp.with(holder.itemView.getContext()).load("https://image.tmdb.org/t/p/w500/" + episode.poster_path).into(holder.poster);
        }

        @Override
        public int getItemCount() {
            return episodeList.size();
        }

        class AnimeListViewHolder extends RecyclerView.ViewHolder {
            TextView title;
            TextView title_jap;
            TextView string;
            TextView title_romanji;

            public AnimeListViewHolder(View itemView) {
                super(itemView);
                title = itemView.findViewById(R.id.animeTitle);
                title_jap = itemView.findViewById(R.id.animeTitle_jap);
                string = itemView.findViewById(R.id.animeDate);
                title_romanji = itemView.findViewById(R.id.animeTitle_romanji);
            }
        }
    }
}

