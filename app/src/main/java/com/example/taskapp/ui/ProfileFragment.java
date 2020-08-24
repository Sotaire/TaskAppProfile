package com.example.taskapp.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.taskapp.App;
import com.example.taskapp.ImageAdapter;
import com.example.taskapp.ImageRV;
import com.example.taskapp.Prefs;
import com.example.taskapp.R;
import com.example.taskapp.ui.interfaces.ImagesListener;

import java.util.ArrayList;
import java.util.List;

public class ProfileFragment extends Fragment implements ImagesListener {

    ImageView avatar;

    ArrayList<ImageRV> imageRVSs;

    ImageAdapter imageAdapter;
    RecyclerView recyclerView;
    ArrayList<Uri> uris;
    Button btnAdd;

    Prefs prefs;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_profile, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.delete){
            deleted();
        }
        return super.onOptionsItemSelected(item);
    }

    public void deleted (){
        App.getInstance().getDatabase().taskDao().deleteImage(imageRVSs);
        imageAdapter.notifyDataSetChanged();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnAdd = view.findViewById(R.id.btn_add);
        prefs = new Prefs(getContext());
        imageRVSs = new ArrayList<>();
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent imageIntent = new Intent(Intent.ACTION_PICK);
                imageIntent.setType("image/*");
                startActivityForResult(imageIntent,43);
            }
        });

        avatar = view.findViewById(R.id.avatar);

        avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent imageIntent = new Intent(Intent.ACTION_PICK);
                imageIntent.setType("image/*");
                startActivityForResult(imageIntent,42);
            }
        });

        App.getInstance().getDatabase().taskDao().getAllLiveImage().observe(getViewLifecycleOwner(), new Observer<List<ImageRV>>() {
            @Override
            public void onChanged(List<ImageRV> imageRVS) {
                imageRVSs.clear();
                imageRVSs.addAll(App.getInstance().getDatabase().taskDao().getAllImage());
                imageAdapter.notifyDataSetChanged();
            }
        });
        recyclerView = view.findViewById(R.id.recycler);
        imageAdapter = new ImageAdapter(imageRVSs);
        recyclerView.setAdapter(imageAdapter);
        imageAdapter.listener = this;
        if (prefs.getImage() != null){
            String selectedImage = prefs.getImage();
            Log.d("TAG","YES");
            Uri uri = Uri.parse(selectedImage);
            Glide.with(this).load(selectedImage).circleCrop().into(avatar);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && data != null && requestCode == 42){
            Uri selectedImage = data.getData();
            Glide.with(this).load(selectedImage).circleCrop().into(avatar);
            prefs.setImage(selectedImage.toString());
        }else if (requestCode == 43 && resultCode == Activity.RESULT_OK && data != null){
            ImageRV imageRV = new ImageRV();
            imageRV.setUri(data.getData().toString());
            App.getInstance().getDatabase().taskDao().insertImage(imageRV);
        }
    }
}


