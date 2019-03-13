package com.example.apssdc.imagesapi;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {
    Context context;
    ArrayList<ImageModel> imgModel;

    public ImageAdapter(MainActivity mainActivity, ArrayList<ImageModel> imageModels) {
        this.context=mainActivity;
        this.imgModel=imageModels;
    }

    @NonNull
    @Override
    public ImageAdapter.ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v=LayoutInflater.from(context).inflate(R.layout.image,parent,false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageAdapter.ImageViewHolder holder, final int position) {
      ImageModel model=imgModel.get(position);
      //holder.imgview.setImageResource(Integer.parseInt(model.images));
        holder.tv.setText(model.likes);
        Picasso.with(context).load(model.images).into(holder.imgview);
        //holder.tv.setText(model.tags);

        holder.imgview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] str=new String[4];
                str[0]=imgModel.get(position).getImages();
                str[1]=imgModel.get(position).getLikes();
                str[2]=imgModel.get(position).getViews();
                str[3]=imgModel.get(position).getTags();
                Intent intent=new Intent(context,Results.class);
                intent.putExtra("data",str);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return imgModel.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        ImageView imgview;
        TextView tv;
        public ImageViewHolder(View itemView) {
            super(itemView);
            imgview=itemView.findViewById(R.id.img);
            tv=itemView.findViewById(R.id.tv);
        }
    }
}
