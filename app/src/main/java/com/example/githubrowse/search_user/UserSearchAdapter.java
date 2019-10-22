package com.example.githubrowse.search_user;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.githubrowse.R;
import com.example.githubrowse.model.Items;


import java.util.List;

public class UserSearchAdapter extends RecyclerView.Adapter<UserSearchAdapter.MyViewHolder> {

    private List<Items> items;
    private Context context;

    public UserSearchAdapter(List<Items> items, Context context) {
        this.items = items;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(items.get(position).getLogin());
//        Glide.with(context)
//                .load(items.get(position).getAvatar_url())
//                .apply(
//                        RequestOptions.placeholderOf(R.drawable.ic_github)
//                )
//                .transition(DrawableTransitionOptions.withCrossFade())
//                .into(holder.avatar);

        Glide.with(context).load(items.get(position).getAvatar_url()).into(holder.avatar);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView avatar;
        MyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            avatar = itemView.findViewById(R.id.avatar);
        }
    }
}
