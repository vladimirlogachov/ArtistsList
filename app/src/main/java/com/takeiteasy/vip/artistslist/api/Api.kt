package com.takeiteasy.vip.artistslist.api

import com.takeiteasy.vip.artistslist.BuildConfig
import com.takeiteasy.vip.artistslist.api.responses.ArtistAlbumInfoResponse
import com.takeiteasy.vip.artistslist.api.responses.ArtistTopAlbumsResponse
import com.takeiteasy.vip.artistslist.api.responses.TopArtistsResponse
import com.takeiteasy.vip.artistslist.models.Artist
import com.takeiteasy.vip.artistslist.models.TopArtists
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("?method=geo.gettopartists&format=json&api_key=" + BuildConfig.API_KEY)
    fun getTopArtists(
            @Query("country") country: String
    ): Single<TopArtistsResponse>

    @GET("?method=artist.gettopalbums&format=json&api_key=" + BuildConfig.API_KEY)
    fun getArtistTopAlbums(
            @Query("artist") artist: String
    ): Single<ArtistTopAlbumsResponse>

    @GET("?method=album.getinfo&format=json&api_key=" + BuildConfig.API_KEY)
    fun getArtistAlbumInfo(
            @Query("artist") artist: String,
            @Query("album") album: String
    ): Single<ArtistAlbumInfoResponse>
}