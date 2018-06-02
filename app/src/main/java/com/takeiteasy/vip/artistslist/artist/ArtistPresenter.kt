package com.takeiteasy.vip.artistslist.artist

import com.takeiteasy.vip.artistslist.models.Album
import com.takeiteasy.vip.artistslist.repository.DataRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class ArtistPresenter(
        private val dataRepository: DataRepository,
        private val disposable: CompositeDisposable
) : ArtistContract.Presenter {

    private var view: ArtistContract.View? = null

    override fun loadArtistTopAlbums(artist: String) {
        disposable.add(
                dataRepository.getArtistTopAlbums(artist)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe { view?.showProgress(true) }
                        .doFinally { view?.showProgress(false) }
                        .subscribeWith(object : DisposableSingleObserver<List<Album>>() {
                            override fun onSuccess(t: List<Album>) {
                                view?.showTopAlbums(t)
                            }

                            override fun onError(e: Throwable) {
                                view?.showError(e.localizedMessage)
                            }
                        })
        )
    }

    override fun setView(view: ArtistContract.View?) {
        this.view = view

        if (this.view == null) {
            disposable.clear()
        }
    }
}