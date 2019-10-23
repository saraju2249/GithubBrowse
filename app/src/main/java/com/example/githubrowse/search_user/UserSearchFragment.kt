package com.example.githubrowse.search_user


import android.content.Intent
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
import com.example.githubrowse.GithubClient
import com.example.githubrowse.R
import com.example.githubrowse.details.UserDetailsActivity
import com.example.githubrowse.model.Owner
import com.google.gson.Gson
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

        val api = GithubClient().getService()

       val userViewModel= ViewModelProviders.of(this, UserViewModel.Factory(context!!,api)).get(UserViewModel::class.java)

       val observable =  RxTextView.textChanges(searchEditext).filter{f->f.length > 3}
                .debounce(300, TimeUnit.MILLISECONDS)
                .map{charSequence ->  charSequence.toString()}
               .observeOn(AndroidSchedulers.mainThread())

        observable.subscribe({ string ->


            userViewModel.search(string).observe(this, Observer {
                items->

                recyclerView.layoutManager = LinearLayoutManager(context)
                val adapter =  UserSearchAdapter(items?.items!!, context!!)
                recyclerView.adapter = adapter
                adapter.setOnItemClickedListener(object:UserSearchAdapter.OnItemClickedListener
                {
                    override fun onClicked(repo: Owner?, pos: Int) {

                        val intent = Intent(context, UserDetailsActivity::class.java);
                        intent.putExtra("data", Gson().toJson(repo));
                        intent.putExtra("isOffline", true);
                        startActivity(intent);


                                      }

                })

            })
        })




}}
