package com.example.githubrowse.local


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.githubrowse.R
import com.example.githubrowse.model.Repo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class LocalRepoFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var toolbar: Toolbar

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {


      val v =  inflater.inflate(R.layout.fragment_home, container, false)


        recyclerView =  v.findViewById(R.id.recycler_view)
        recyclerView.hasFixedSize()


        return v
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        val  mViewModelFactory = Injection.provideViewModelFactory(context)
        val mViewModel = ViewModelProvider(this, mViewModelFactory).get(ReposViewModel::class.java)


        mViewModel.userName.
                subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread())
                .subscribe{s->
                    recyclerView.layoutManager = LinearLayoutManager(context)
                    val adapter = LocalAdapter(s,context!!)
                    recyclerView.adapter = adapter

                    adapter.setOnItemClickedListener(object :LocalAdapter.OnItemClickedListener
                    {
                        override fun onClicked(repo: Repo?, pos: Int) {

                            repo?.let {
                                mViewModel.delete(it).subscribeOn(Schedulers.io())
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe{

                                            Log.d("checkLog","")
                                        }
                            }
                        }

                    })
                    //Log.d("fhbvufb",Gson().toJson(s))
                }


    }





}// Required empty public constructor



