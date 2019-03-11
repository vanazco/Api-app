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
import android.widget.ImageView;
import android.widget.TextView;

import com.example.retrofit.GlideApp;
import com.example.retrofit.MainViewModel;
import com.example.retrofit.R;
import com.example.retrofit.model.Caracter;
import com.example.retrofit.model.Episode;

import java.util.ArrayList;
import java.util.List;


public class CharacterListFragment extends Fragment {
    private MainViewModel mViewModel;
    private RecyclerView mRecyclerView;
    private CharacterListFragment.CharacterListAdapter mCharacterListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_character_list, container, false);


        mRecyclerView = view.findViewById(R.id.characterList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mCharacterListAdapter = new CharacterListFragment.CharacterListAdapter();
        mRecyclerView.setAdapter(mCharacterListAdapter);

        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        mViewModel.getCharacters().observe(this, new Observer<List<Caracter>>() {
            @Override
            public void onChanged(@Nullable List<Caracter> caracters) {
                mCharacterListAdapter.caracterList = caracters;
                mCharacterListAdapter.notifyDataSetChanged();
            }
        });
        return view;
    }


    public static class CharacterListAdapter extends RecyclerView.Adapter<CharacterListAdapter.CharacterListViewHolder>{
        public List<Caracter> caracterList = new ArrayList<>();

        @NonNull
        @Override
        public CharacterListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_character, parent, false);
            return new CharacterListViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull CharacterListViewHolder holder, int position) {
            Caracter caracter = caracterList.get(position);

            holder.name.setText(caracter.name);
            holder.role.setText(caracter.role);
            GlideApp.with(holder.itemView.getContext()).load(caracter.image_url).into(holder.img);
        }

        @Override
        public int getItemCount() {
            return caracterList.size();
        }

        class CharacterListViewHolder extends RecyclerView.ViewHolder {
            TextView name;
            TextView role;
            ImageView img;

            public CharacterListViewHolder(View itemView) {
                super(itemView);
                name = itemView.findViewById(R.id.characterName);
                role = itemView.findViewById(R.id.characterRole);
                img = itemView.findViewById(R.id.characterImg);

            }
        }
    }
}

