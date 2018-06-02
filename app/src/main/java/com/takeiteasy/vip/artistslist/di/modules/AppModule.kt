package com.takeiteasy.vip.artistslist.di.modules

import com.takeiteasy.vip.artistslist.album.AlbumInfoContract
import com.takeiteasy.vip.artistslist.album.AlbumInfoPresenter
import com.takeiteasy.vip.artistslist.api.Api
import com.takeiteasy.vip.artistslist.artist.ArtistContract
import com.takeiteasy.vip.artistslist.artist.ArtistPresenter
import com.takeiteasy.vip.artistslist.popular.PopularArtistsContract
import com.takeiteasy.vip.artistslist.popular.PopularArtistsPresenter
import com.takeiteasy.vip.artistslist.repository.DataRepository
import com.takeiteasy.vip.artistslist.router.ActivityRouter
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    fun provideCompositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }

    @Provides
    @Singleton
    fun provideActivityRouter(): ActivityRouter {
        return ActivityRouter()
    }

    @Provides
    @Singleton
    fun provideDataRepository(api: Api): DataRepository {
        return DataRepository(api)
    }

    @Provides
    @Singleton
    fun providePopularArtistsPresenter(dataRepository: DataRepository, disposable: CompositeDisposable): PopularArtistsContract.Presenter {
        return PopularArtistsPresenter(dataRepository, disposable)
    }

    @Provides
    @Singleton
    fun provideArtistPresenter(dataRepository: DataRepository, disposable: CompositeDisposable): ArtistContract.Presenter {
        return ArtistPresenter(dataRepository, disposable)
    }

    @Provides
    @Singleton
    fun provideAlbumInfoPresenter(dataRepository: DataRepository, disposable: CompositeDisposable): AlbumInfoContract.Presenter {
        return AlbumInfoPresenter(dataRepository, disposable)
    }
}