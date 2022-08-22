package com.example.newsapp.news

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NavUtils
import com.example.newsapp.R
import com.example.newsapp.databinding.ActivityAboutPageBinding

class AboutPageActivity : AppCompatActivity() {
    private lateinit var activityAboutPageBinding: ActivityAboutPageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityAboutPageBinding = ActivityAboutPageBinding.inflate(layoutInflater)
        setContentView(activityAboutPageBinding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.aboutpage_title)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                NavUtils.navigateUpFromSameTask(this)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}