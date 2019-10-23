package com.example.githubrowse.details

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubrowse.GithubClient
import com.example.githubrowse.R
import com.example.githubrowse.model.Repo
import com.example.githubrowse.local.Injection
import com.example.githubrowse.local.ReposViewModel
import com.example.githubrowse.model.Owner
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DetailActivity : AppCompatActivity() {
lateinit var  recyclerView:RecyclerView;
    lateinit var descTv:TextView;

    lateinit var toolbar:Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        toolbar =  findViewById(R.id.toolbar)
        recyclerView =  findViewById(R.id.recycler_view)
        descTv =  findViewById(R.id.desc)

        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp)


        val mViewModelFactory = Injection.provideViewModelFactory(this)
        val mViewModel = ViewModelProvider(this, mViewModelFactory).get(ReposViewModel::class.java)

        val turnsType = object : TypeToken<Repo>() {}.type
        val turns = Gson().fromJson<Repo>(intent.getStringExtra("repos"), turnsType)

        supportActionBar!!.title =  turns.name
        // intent.getStringExtra("repos")
        val api = GithubClient().getService()

        val userViewModel = ViewModelProviders.of(this, ContributionsViewModel.Factory(this!!, api)).get(ContributionsViewModel::class.java)
        descTv.text= turns.description
        if (!intent.getBooleanExtra("isOffline",false)) {

            userViewModel.getCtbn(turns.owner!!.login, turns!!.name!!).observe(this, Observer {
                items->

                with(turns)
                {

                    contributors = items
                }

                mViewModel.updateRepos(turns).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                {},{ t-> Log.d("jfff",t.message)
                        }
                        )

                recyclerView.layoutManager = LinearLayoutManager(this@DetailActivity)
                val ad = CtbtnAdapter(items,this@DetailActivity);
                recyclerView.adapter = ad
                ad.setOnItemClickedListener(object :CtbtnAdapter.OnItemClickedListener
                {
                    override fun onClicked(repo: Owner?, pos: Int) {

                        val intent = Intent(this@DetailActivity, UserDetailsActivity::class.java);
                        intent.putExtra("data", Gson().toJson(repo));
                        intent.putExtra("isOffline", true);
                        startActivity(intent);

                    }

                })
            })

        }

        else
        {
            turns.contributors?.let {
                recyclerView.layoutManager = LinearLayoutManager(this@DetailActivity)
                val ad = CtbtnAdapter(it,this@DetailActivity);
                recyclerView.adapter = ad
                ad.setOnItemClickedListener(object :CtbtnAdapter.OnItemClickedListener
                {
                    override fun onClicked(repo: Owner?, pos: Int) {

                        val intent = Intent(this@DetailActivity, UserDetailsActivity::class.java);
                        intent.putExtra("data", Gson().toJson(repo));
                        intent.putExtra("isOffline", true);
                        startActivity(intent);
                    }
                })
        }


        }

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if(item?.itemId == android.R.id.home)
        {
            onBackPressed()
        }


        return super.onOptionsItemSelected(item)
    }
}
