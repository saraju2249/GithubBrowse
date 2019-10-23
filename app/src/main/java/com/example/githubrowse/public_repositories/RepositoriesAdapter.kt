package com.example.githubrowse.public_repositories

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

import com.example.githubrowse.R
import com.example.githubrowse.model.Repo
import kotlinx.android.synthetic.main.item_repo.view.*


class RepositoriesAdapter(private val mCtx: Context) : PagedListAdapter<Repo, RepositoriesAdapter.ItemViewHolder>(DIFF_CALLBACK) {
    private lateinit var onItemClickedListener: OnItemClickedListener

    interface OnItemClickedListener {

        fun onClicked(view: View, transaction: Repo?, pos: Int)
    }

    fun setOnItemClickedListener(listener: OnItemClickedListener) {
        this.onItemClickedListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(mCtx).inflate(R.layout.item_repo, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val transaction = getItem(position)


       holder.itemView.name.text =  transaction?.name
       holder.itemView.desc.text = transaction?.description
       holder.itemView.lyt_parent.setOnClickListener { view ->
           onItemClickedListener.onClicked(view, transaction, position)
       }

    }

    inner class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {

//        var image: ImageView
//        var sync: ImageView
//        var name: TextView
//        var date: TextView
//        var time: TextView
//        var processCount: TextView
//        var contextType: TextView
//        var lyt_parent: View


//        init {
//
//            image = view.findViewById<ImageView>(R.id.image) as ImageView
//            sync = view.findViewById<ImageView>(R.id.sync_icon) as ImageView
//            name = view.findViewById<TextView>(R.id.name) as TextView
//            processCount = view.findViewById<TextView>(R.id.process_count) as TextView
//            date = view.findViewById<TextView>(R.id.date) as TextView
//            time = view.findViewById<TextView>(R.id.time) as TextView
//            lyt_parent = view.findViewById(R.id.lyt_parent) as View
//            contextType = view.findViewById<TextView>(R.id.context_type) as TextView
//
//        }

    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Repo>() {
            override fun areItemsTheSame(oldItem: Repo, newItem: Repo): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Repo, newItem: Repo): Boolean {
                return oldItem == newItem
            }
        }
    }
}
