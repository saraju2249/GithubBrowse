package com.example.githubrowse.local

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.githubrowse.R
import com.example.githubrowse.model.Repo

class LocalAdapter(private val items: List<Repo>, private val context: Context) : RecyclerView.Adapter<LocalAdapter.MyViewHolder>() {

    private lateinit var onItemClickedListener: OnItemClickedListener
    private lateinit var onItemDeleteListener: OnItemDeleteListener

     interface OnItemClickedListener {

         fun onClicked( repo: Repo?, pos: Int)
     }

    interface OnItemDeleteListener {

        fun onDeleted( repo: Repo?, pos: Int)
    }


    fun setOnItemClickedListener(listener: OnItemClickedListener) {
         this.onItemClickedListener = listener
     }

    fun setOnItemDeletedListener(listener: OnItemDeleteListener) {
        this.onItemDeleteListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.local_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.name.text = items[position].name
        holder.desc?.text = items[position].description

        holder.deleteBtn.setOnClickListener {

            onItemDeleteListener?.onDeleted(items[position],position)
        }

        holder.itemView.setOnClickListener({
            v->
            onItemClickedListener.onClicked(items[position],position)
        })


    }

    override fun getItemCount(): Int {
        return items.size
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView
        var deleteBtn: ImageView
        var desc: TextView? = null

        init {
            name = itemView.findViewById(R.id.name)
            deleteBtn = itemView.findViewById(R.id.btn_delete)
            desc =   itemView.findViewById(R.id.desc);
        }
    }
}
