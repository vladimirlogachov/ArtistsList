package com.takeiteasy.vip.artistslist.album

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.takeiteasy.vip.artistslist.R
import com.takeiteasy.vip.artistslist.models.AlbumInfo
import com.takeiteasy.vip.artistslist.router.ActivityRouter
import dagger.android.AndroidInjection

import kotlinx.android.synthetic.main.activity_album_info.*
import kotlinx.android.synthetic.main.content_album_info.*
import javax.inject.Inject

class AlbumInfoActivity : AppCompatActivity(), AlbumInfoContract.View {

    @Inject
    lateinit var presenter: AlbumInfoContract.Presenter

    private lateinit var artist: String
    private lateinit var album: String
    private lateinit var adapter: TracksAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_album_info)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        artist = intent.getStringExtra(ActivityRouter.ARG_ARTIST_NAME)
        album = intent.getStringExtra(ActivityRouter.ARG_ALBUM_NAME)

        title = album
        toolbar.subtitle = artist

        adapter = TracksAdapter()

        tracksList.adapter = adapter
        tracksList.layoutManager = LinearLayoutManager(this)
        tracksList.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        presenter.setView(this)

        if (adapter.itemCount == 0) {
            presenter.loadAlbumInfo(artist, album)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.setView(null)
    }

    override fun showProgress(show: Boolean) {
        if (show) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }

    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }

    override fun showAlbumInfo(albumInfo: AlbumInfo) {
        adapter.updateData(albumInfo.tracks.track)
    }
}
