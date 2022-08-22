package com.example.newsapp.news

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.core.app.NavUtils
import androidx.core.app.ShareCompat
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.databinding.ActivityDetailBinding
import com.example.newsapp.network.ArticlesItem
import com.example.newsapp.news.DetailNewsActivity.Companion.EXTRA_DETAIL_NEWS

class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_DETAIL = "extra_detail"
    }
    private lateinit var detailBinding: ActivityDetailBinding
    private lateinit var url: String
    private lateinit var title: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(detailBinding.root)
        val extras = intent.getParcelableExtra<ArticlesItem>(EXTRA_DETAIL)
        if (extras != null) {
            if (extras.title !=null){
                title = extras.title
            }
            if (extras.url != null){
                url = extras.url
            }
            supportActionBar?.title = title
            with(detailBinding){
                Glide.with(this@DetailActivity)
                    .load(extras.urlToImage)
                    .placeholder(R.drawable.ic_loading)
                    .error(R.drawable.ic_error)
                    .into(imageviewDetailPoster)
                textviewDetailTitle.text = title
                textviewDetailAuthor.text = extras.author
                textviewDetailDate.text = extras.publishedAt
                textviewDetailDesc.text = extras.content

                buttonDetailVisitnews.setOnClickListener {
                    val intentDetailNews = Intent(this@DetailActivity, DetailNewsActivity::class.java)
                    val extraUrl = intentDetailNews.putExtra(EXTRA_DETAIL_NEWS, extras)
                    startActivity(extraUrl)
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_detail, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_share -> {
                val mimeType = "text/plain"
                ShareCompat.IntentBuilder(this@DetailActivity)
                    .setType(mimeType)
                    .setChooserTitle(getString(R.string.all_share))
                    .setText("Check $title at $url")
                    .startChooser()
                true
            }
            android.R.id.home -> {
                NavUtils.navigateUpFromSameTask(this)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}