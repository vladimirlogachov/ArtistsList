package com.takeiteasy.vip.artistslist.di.modules

import com.takeiteasy.vip.artistslist.album.AlbumInfoActivity
import com.takeiteasy.vip.artistslist.artist.ArtistActivity
import com.takeiteasy.vip.artistslist.popular.PopularArtistsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuildersModule {
    @ContributesAndroidInjector
    abstract fun bindPopularArtistsActivity(): PopularArtistsActivity

    @ContributesAndroidInjector
    abstract fun bindArtistActivity(): ArtistActivity

    @ContributesAndroidInjector
    abstract fun bindAlbumInfoActivity(): AlbumInfoActivity
}