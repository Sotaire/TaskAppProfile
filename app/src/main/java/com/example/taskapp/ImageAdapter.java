package com.example.taskapp;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.taskapp.ui.ProfileFragment;
import com.example.taskapp.ui.interfaces.ImagesListener;

import java.util.ArrayList;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {

    ArrayList<ImageRV> uris = new ArrayList<>();
    public ImagesListener listener;

    public ImageAdapter(ArrayList<ImageRV> uris) {
        this.uris = uris;
    }

    public void setImages(ImageRV uri){
        uris.add(uri);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_holder, parent, false);
        ImageViewHolder viewHolder = new ImageViewHolder(v);
        viewHolder.listener = listener;
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        holder.bind(uris.get(position));
    }

    @Override
    public int getItemCount() {
        return uris.size();
    }





    public class ImageViewHolder extends RecyclerView.ViewHolder {

        ImagesListener listener;
        private ImageView imageView;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.v_holder);
        }

        public void bind(ImageRV imageRV) {
            Glide
                    .with((ProfileFragment)listener)
                    .load(Uri.parse(imageRV.getUri()))
                    .transform(new RoundedCorners(16))
                    .into(imageView);
        }
    }



}
