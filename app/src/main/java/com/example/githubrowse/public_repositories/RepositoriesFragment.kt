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
import com.example.githubrowse.GithubBrowserService
import com.example.githubrowse.R
import com.example.githubrowse.details.DetailActivity
import com.example.githubrowse.model.Repo
import com.facebook.shimmer.ShimmerFrameLayout
import com.google.gson.Gson
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RepositoriesFragment : Fragment() {

    lateinit var recyclerView: RecyclerView

    private var shimmerViewContainer: ShimmerFrameLayout? = null
    private var adapter: RepositoriesAdapter? = null
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        adapter = RepositoriesAdapter(context!!)
        showLoadingIndicator(true)
        // model.getTransactions(((NewTransactionsActivity)getActivity()).getUserName(),((NewTransactionsActivity)getActivity()).getKey(),progressBar,((NewTransactionsActivity)getActivity()).getServerIp(),getContext()).observe(this, new Observer<TransactionLog>() {

        val api = Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(GithubBrowserService::class.java)


       // val userViewModel= ViewModelProviders.of(this, ContributionsViewModel.Factory(context!!,api)).get(ContributionsViewModel::class.java)

        val itemViewModel = ViewModelProviders.of(this, RepositoriesViewModelFactory(api,context!!, "", "")).get(RepositoriesViewModel::class.java)

//        userViewModel.search("saraju2249").observe(this, Observer {
//            items->
//            Log.d("fbvifvb",Gson().toJson(items))
//        })



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
                //                Intent intent = new Intent(getContext(), TransactionsDetailActivity.class);
                //                intent.putExtra("data",new Gson().toJson(transaction));
                //                startActivity(intent);


                                val intent = Intent(context, DetailActivity::class.java);
                                intent.putExtra("repos",Gson().toJson(repo));
                                startActivity(intent);
            }
        })

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.activity_main_tansaction, container, false)

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