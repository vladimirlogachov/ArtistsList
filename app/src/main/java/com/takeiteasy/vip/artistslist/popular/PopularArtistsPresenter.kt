package com.takeiteasy.vip.artistslist.popular

import com.takeiteasy.vip.artistslist.models.Artist
import com.takeiteasy.vip.artistslist.repository.DataRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class PopularArtistsPresenter (
        private val dataRepository: DataRepository,
        private val disposable: CompositeDisposable
) : PopularArtistsContract.Presenter {

    private var view: PopularArtistsContract.View? = null

    override fun setView(view: PopularArtistsContract.View?) {
        this.view = view

        if (this.view == null) {
            disposable.clear()
        }
    }

    override fun loadArtists(country: String) {
        disposable.add(
                dataRepository.getPopularArtists(country)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnSubscribe { view?.showProgress(true) }
                        .doFinally { view?.showProgress(false) }
                        .subscribeWith(object : DisposableSingleObserver<List<Artist>>() {
                            override fun onSuccess(artists: List<Artist>) {
                                view?.showArtists(artists)
                            }

                            override fun onError(e: Throwable) {
                                view?.showError(e.localizedMessage)
                            }
                        })
        )
    }

}