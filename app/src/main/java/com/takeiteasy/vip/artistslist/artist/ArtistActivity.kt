package com.takeiteasy.vip.artistslist.artist

import android.content.Intent
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.takeiteasy.vip.artistslist.R
import com.takeiteasy.vip.artistslist.models.Album
import com.takeiteasy.vip.artistslist.router.ActivityRouter
import dagger.android.AndroidInjection

import kotlinx.android.synthetic.main.activity_artist.*
import kotlinx.android.synthetic.main.content_artist.*
import javax.inject.Inject

class ArtistActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener, ArtistContract.View, ActivityRouter.OnIntentReadyListener {
    @Inject
    lateinit var presenter: ArtistContract.Presenter
    @Inject
    lateinit var router: ActivityRouter

    private lateinit var adapter: AlbumsAdapter
    private lateinit var artist:String

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_artist)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        artist = intent.getStringExtra(ActivityRouter.ARG_ARTIST_NAME)

        title = artist

        adapter = AlbumsAdapter{ album: String ->
            router.requestAlbumInfoActivityIntent(this, artist, album, this)
        }

        albumsList.adapter = adapter
        albumsList.layoutManager = LinearLayoutManager(this)
        albumsList.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        swipeRefresh.setOnRefreshListener(this)

        presenter.setView(this)

        if (adapter.itemCount == 0) {
            presenter.loadArtistTopAlbums(artist)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.setView(null)
    }

    override fun showProgress(show: Boolean) {
        swipeRefresh.isRefreshing = show
    }

    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }

    override fun showTopAlbums(topAlbums: List<Album>) {
        adapter.updateData(topAlbums)
    }

    override fun onRefresh() {
        presenter.loadArtistTopAlbums(artist)
    }

    override fun onIntentReady(intent: Intent) {
        startActivity(intent)
    }
}
