package com.example.githubrowse.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import com.example.githubrowse.R
import com.example.githubrowse.model.Items
import com.example.githubrowse.model.Owner
import com.example.githubrowse.model.Repo
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_user_details_actitiy.*

class UserDetailsActivity : AppCompatActivity() {
    lateinit var toolbar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_details_actitiy)

        toolbar =  findViewById(R.id.toolbar)


        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp)
        supportActionBar!!.setTitle("")


        val turnsType = object : TypeToken<Owner>() {}.type
        val item = Gson().fromJson<Owner>(intent.getStringExtra("data"), turnsType)

        login_id.text = item.login
        score_id.text = item.score.toString()
        contributions_id.text = item.contributions.toString()

        Glide.with(this)
                .load(item.avatar_url)
                .centerCrop()
                .placeholder(R.drawable.ic_github)
                .into(image);

    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if(item?.itemId == android.R.id.home)
        {
            onBackPressed()
        }


        return super.onOptionsItemSelected(item)
    }
}
