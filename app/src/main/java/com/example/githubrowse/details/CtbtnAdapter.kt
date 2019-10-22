package com.example.githubrowse.details

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubrowse.R
import com.example.githubrowse.model.Contributions

class CtbtnAdapter(private val items: List<Contributions>, private val context: Context) : RecyclerView.Adapter<CtbtnAdapter.MyViewHolder>() {

    private lateinit var onItemClickedListener: OnItemClickedListener

     interface OnItemClickedListener {

         fun onClicked( repo: Contributions?, pos: Int)
     }

     fun setOnItemClickedListener(listener: OnItemClickedListener) {
         this.onItemClickedListener = listener
     }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.ctnbt_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.name.text = items[position].login
        Glide.with(context).load(items[position].avatar_url).into(holder.imag)

        onItemClickedListener?.onClicked(items[position],position)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView
        var imag: ImageView
        var avatar: ImageView? = null

        init {
            name = itemView.findViewById(R.id.name)
            imag = itemView.findViewById(R.id.avatar)
            // avatar = itemView.findViewById(R.id.avatar);
        }
    }
}
