package com.example.githubrowse.search_user

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.bumptech.glide.Glide
import com.example.githubrowse.R
import com.example.githubrowse.model.Items
import com.example.githubrowse.model.Owner
import com.example.githubrowse.model.Repo

class UserSearchAdapter(private val items: List<Owner>, private val context: Context) : RecyclerView.Adapter<UserSearchAdapter.MyViewHolder>() {

    private lateinit var onItemClickedListener: OnItemClickedListener

    interface OnItemClickedListener {

        fun onClicked(repo: Owner?, pos: Int)
    }

    fun setOnItemClickedListener(listener: OnItemClickedListener) {
        this.onItemClickedListener = listener
    }





    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.name.text = items[position].login
        holder.itemView.setOnClickListener{v->


            onItemClickedListener.onClicked(items[position],position)
        }

        Glide.with(context).load(items[position].avatar_url).into(holder.avatar)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView
        var avatar: ImageView

        init {
            name = itemView.findViewById(R.id.name)
            avatar = itemView.findViewById(R.id.avatar)
        }
    }
}
