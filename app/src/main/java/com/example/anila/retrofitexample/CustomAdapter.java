package com.example.anila.retrofitexample;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import okhttp3.OkHttpClient;
import com.example.anila.retrofitexample.model.RetroPhoto;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {
    private List<RetroPhoto> dataList;
    private Context context;
    public CustomAdapter(Context context,List<RetroPhoto>dataList){

        this.context=context;
        this.dataList=dataList;
    }

    @NonNull
    @Override
    public CustomAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.custom_layout,parent,false);
        return  new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.CustomViewHolder holder, int position) {
        holder.txtView.setText((CharSequence) dataList.get(position).getTitle());
        Picasso.Builder builder=new Picasso.Builder(context);
        builder.downloader(new OkHttpDownloader(context));
        builder.build().load(dataList.get(position).getThumbnailUrl())
                .placeholder((R.drawable.ic_launcher_background))
                .error(R.drawable.ic_launcher_background)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
    class CustomViewHolder extends RecyclerView.ViewHolder{

        private TextView txtView;
        private ImageView imageView;
        public CustomViewHolder(View itemView) {
            super(itemView);
             final View mView=itemView;
            txtView=itemView.findViewById(R.id.cover_text);
            imageView=itemView.findViewById(R.id.cover_image);


        }
    }


}
