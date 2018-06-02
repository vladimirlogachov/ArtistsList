package com.takeiteasy.vip.artistslist.album

import com.takeiteasy.vip.artistslist.models.AlbumInfo
import com.takeiteasy.vip.artistslist.repository.DataRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class AlbumInfoPresenter(
        private val dataRepository: DataRepository,
        private val disposable: CompositeDisposable
) : AlbumInfoContract.Presenter {
    private var view: AlbumInfoContract.View? = null

    override fun setView(view: AlbumInfoContract.View?) {
        this.view = view

        if (this.view == null) {
            disposable.clear()
        }
    }

    override fun loadAlbumInfo(artist: String, album: String) {
        disposable.add(
                dataRepository.loadArtistAlbumInfo(artist, album)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe { view?.showProgress(true) }
                        .doFinally { view?.showProgress(false) }
                        .subscribeWith(object : DisposableSingleObserver<AlbumInfo>() {
                            override fun onSuccess(t: AlbumInfo) {
                                view?.showAlbumInfo(t)
                            }

                            override fun onError(e: Throwable) {
                                view?.showError(e.localizedMessage)
                            }
                        })
        )
    }

}