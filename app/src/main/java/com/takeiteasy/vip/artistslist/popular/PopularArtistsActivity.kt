package com.takeiteasy.vip.artistslist.popular

import android.content.Intent
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.takeiteasy.vip.artistslist.R
import com.takeiteasy.vip.artistslist.models.Artist
import com.takeiteasy.vip.artistslist.router.ActivityRouter
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_popular_artists.*
import kotlinx.android.synthetic.main.content_popular_artists.*
import javax.inject.Inject


class PopularArtistsActivity :
        AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener, PopularArtistsContract.View,
        ActivityRouter.OnIntentReadyListener, AdapterView.OnItemSelectedListener {

    @Inject
    lateinit var presenter: PopularArtistsContract.Presenter
    @Inject
    lateinit var router: ActivityRouter

    lateinit var adapter: PopularArtistsAdapter
    lateinit var spinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_popular_artists)
        setSupportActionBar(toolbar)
        setTitle(R.string.pupular_artists)

        adapter = PopularArtistsAdapter { artistName: String ->
            router.requestArtistActivityIntent(this, artistName, this)
        }

        artistsList.adapter = adapter
        artistsList.layoutManager = LinearLayoutManager(this)
        artistsList.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        swipeRefresh.setOnRefreshListener(this)

        presenter.setView(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.setView(null)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)

        val item = menu.findItem(R.id.spinner)
        spinner = item.actionView as Spinner

        val adapter = ArrayAdapter.createFromResource(this,
                R.array.countries, R.layout.spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinner.adapter = adapter
        spinner.onItemSelectedListener = this
        spinner.setSelection(0)

        return true
    }

    override fun onRefresh() {
        presenter.loadArtists(spinner.selectedItem as String)
    }

    override fun showProgress(show: Boolean) {
        swipeRefresh.isRefreshing = show
    }

    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }

    override fun showArtists(artists: List<Artist>) {
        adapter.updateData(artists)
    }

    override fun onIntentReady(intent: Intent) {
        startActivity(intent)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        presenter.loadArtists(spinner.selectedItem as String)
    }
}
