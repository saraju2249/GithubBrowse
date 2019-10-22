package com.example.githubrowse.search_user


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubrowse.GithubBrowserService
import com.example.githubrowse.R
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.jakewharton.rxbinding.widget.RxTextView
import rx.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit


class UserSearchFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var toolbar: Toolbar
    lateinit var searchEditext:EditText

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {


        val v =  inflater.inflate(R.layout.fragment_dashboard, container, false)
        searchEditext = v.findViewById(R.id.editText)

        recyclerView =  v.findViewById(R.id.recycler_view)
        recyclerView.hasFixedSize()
        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        //adapter = RepositoriesAdapter(context!!)
        // showLoadingIndicator(true)
        // model.getTransactions(((NewTransactionsActivity)getActivity()).getUserName(),((NewTransactionsActivity)getActivity()).getKey(),progressBar,((NewTransactionsActivity)getActivity()).getServerIp(),getContext()).observe(this, new Observer<TransactionLog>() {

        val api = Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(GithubBrowserService::class.java)


       val userViewModel= ViewModelProviders.of(this, UserViewModel.Factory(context!!,api)).get(UserViewModel::class.java)

       val observable =  RxTextView.textChanges(searchEditext).filter{f->f.length > 3}
                .debounce(300, TimeUnit.MILLISECONDS)
                .map{charSequence ->  charSequence.toString()}
               .observeOn(AndroidSchedulers.mainThread())

        observable.subscribe({ string ->


            userViewModel.search(string).observe(this, Observer {
                items->

                recyclerView.layoutManager = LinearLayoutManager(context)
                recyclerView.adapter = UserSearchAdapter(items?.items,context)
            })
        })




}}
