package com.example.githubrowse.public_repositories

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubrowse.GithubClient
import com.example.githubrowse.R
import com.example.githubrowse.details.DetailActivity
import com.example.githubrowse.model.Repo
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.gson.Gson

class RepositoriesFragment : Fragment() {

    lateinit var recyclerView: RecyclerView

    private var shimmerViewContainer: ShimmerFrameLayout? = null
    private var adapter: RepositoriesAdapter? = null
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = RepositoriesAdapter(context!!)
        showLoadingIndicator(true)

        val api = GithubClient().getService()

        val itemViewModel = ViewModelProviders.of(this, RepositoriesViewModelFactory(api,context!!, "", "")).get(RepositoriesViewModel::class.java)


        itemViewModel.itemPagedList.observe(this, Observer { items ->
            if (shimmerViewContainer!!.visibility == View.VISIBLE) {
                showLoadingIndicator(false)
            }
            recyclerView.visibility = View.VISIBLE
            adapter!!.submitList(items)
        })

        recyclerView.adapter = adapter
        adapter!!.setOnItemClickedListener(object : RepositoriesAdapter.OnItemClickedListener {
            override fun onClicked(view: View, repo: Repo?, pos: Int) {

                                val intent = Intent(context, DetailActivity::class.java);
                                intent.putExtra("repos",Gson().toJson(repo));
                                intent.putExtra("isOffline", false);
                                startActivity(intent);
            }
        })

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.activity_main_repo, container, false)

        recyclerView = rootView.findViewById(R.id.image_list)
        shimmerViewContainer = rootView.findViewById(R.id.shimmer_view_container)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)
        return rootView
    }


    fun showLoadingIndicator(active: Boolean) {
        if (active) {
            shimmerViewContainer!!.visibility = View.VISIBLE
            shimmerViewContainer!!.startShimmerAnimation()
        } else {

            val handler = Handler()
            handler.postDelayed({
                shimmerViewContainer!!.stopShimmerAnimation()
                shimmerViewContainer!!.visibility = View.GONE
            }, 2000)
        }
    }

    override fun onResume() {
        super.onResume()
        shimmerViewContainer!!.startShimmerAnimation()
    }

    override fun onPause() {
        shimmerViewContainer!!.stopShimmerAnimation()
        super.onPause()
    }

    companion object {

        fun newInstance(): RepositoriesFragment {
            return RepositoriesFragment()
        }
    }


}