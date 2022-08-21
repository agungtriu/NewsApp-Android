package com.example.newsapp.news

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NavUtils
import androidx.core.app.ShareCompat
import com.example.newsapp.R
import com.example.newsapp.databinding.ActivityDetailNewsBinding
import com.example.newsapp.network.ArticlesItem

class DetailNewsActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_DETAIL = "extra_detail"
    }

    private lateinit var detailNewsBinding: ActivityDetailNewsBinding
    private lateinit var url: String
    private lateinit var title: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailNewsBinding = ActivityDetailNewsBinding.inflate(layoutInflater)
        setContentView(detailNewsBinding.root)
        val extras = intent.getParcelableExtra<ArticlesItem>(EXTRA_DETAIL)
        extras?.also {
            url = it.url.toString()
            title = it.title.toString()
        }
        supportActionBar?.title = title
        val webView = detailNewsBinding.webviewDetailNews
        extras?.let { webView.loadUrl(url) }
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = WebViewClient()
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
                ShareCompat.IntentBuilder(this@DetailNewsActivity)
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