package com.example.githubrowse.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubrowse.GithubBrowserService
import com.example.githubrowse.R
import com.example.githubrowse.model.Repo
import com.example.githubrowse.local.Injection
import com.example.githubrowse.local.ReposViewModel
import com.example.githubrowse.model.Contributions
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailActivity : AppCompatActivity() {
lateinit var  recyclerView:RecyclerView;

    lateinit var toolbar:Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        toolbar =  findViewById(R.id.toolbar)
        recyclerView =  findViewById(R.id.recycler_view)


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

        val api = Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(GithubBrowserService::class.java)

        mViewModel.updateRepos(turns).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    //Log.d("fhbvufb","fvff")
                }

        val userViewModel = ViewModelProviders.of(this, ContributionsViewModel.Factory(this!!, api)).get(ContributionsViewModel::class.java)

        userViewModel.getCtbn(turns.owner!!.login, turns!!.name!!).observe(this, Observer {
            items->

            recyclerView.layoutManager = LinearLayoutManager(this@DetailActivity)
            val ad = CtbtnAdapter(items,this@DetailActivity);
            recyclerView.adapter = ad
            ad.setOnItemClickedListener(object :CtbtnAdapter.OnItemClickedListener
            {
                override fun onClicked(repo: Contributions?, pos: Int) {

                }

            })
        })
        // https://api.github.com/repos/caged/microsis/contributors
        // https://api.github.com/repos/mojombo/god/contributors  owner = mojombo name = "god"
        // /repos/:owner/:repo/stats/contributors

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if(item?.itemId == android.R.id.home)
        {
            onBackPressed()
        }


        return super.onOptionsItemSelected(item)
    }
}
