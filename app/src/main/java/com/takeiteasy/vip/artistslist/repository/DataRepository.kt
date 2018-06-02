package com.takeiteasy.vip.artistslist.repository

import com.takeiteasy.vip.artistslist.api.Api
import com.takeiteasy.vip.artistslist.api.responses.ArtistAlbumInfoResponse
import com.takeiteasy.vip.artistslist.api.responses.ArtistTopAlbumsResponse
import com.takeiteasy.vip.artistslist.api.responses.TopArtistsResponse
import com.takeiteasy.vip.artistslist.models.Album
import com.takeiteasy.vip.artistslist.models.AlbumInfo
import com.takeiteasy.vip.artistslist.models.Artist
import io.reactivex.Single
import java.io.IOException

class DataRepository(
        private val api: Api
) {

    private val artistsStorage = hashMapOf<String, List<Artist>>()
    private val artistAlbumsStorage = hashMapOf<String, List<Album>>()
    private val albumsInfoStorage = mutableListOf<AlbumInfo>()

    fun getPopularArtists(country: String): Single<List<Artist>> {
        return if (!isOnline() || artistsStorage.contains(country)) {
            Single.just(readArtists(country))
        } else {
            api.getTopArtists(country)
                    .flatMap { t: TopArtistsResponse ->
                        val artists = t.topartists.artist.sortedBy { it.name }
                        saveArtists(country, artists)
                        Single.just(artists)
                    }
        }
    }

    private fun saveArtists(country: String, artists: List<Artist>) {
        artistsStorage[country] = artists
    }

    private fun readArtists(country: String): List<Artist> {
        return if (artistsStorage.contains(country)) {
            artistsStorage.getValue(country)
        } else {
            listOf()
        }
    }

    private fun isOnline(): Boolean {
        val runtime = Runtime.getRuntime()

        try {
            val ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8")
            val exitVal = ipProcess.waitFor()
            return exitVal == 0
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

        return false
    }

    fun getArtistTopAlbums(artist: String): Single<List<Album>> {
        return if (!isOnline() || artistAlbumsStorage.contains(artist)) {
            Single.just(readAlbums(artist))
        } else {
            api.getArtistTopAlbums(artist)
                    .flatMap { t: ArtistTopAlbumsResponse ->
                        val albums = t.topalbums.album.sortedBy { it.name }
                        saveAlbums(artist, albums)
                        Single.just(albums)
                    }
        }
    }

    private fun saveAlbums(artist: String, albums: List<Album>) {
        artistAlbumsStorage[artist] = albums
    }

    private fun readAlbums(artist: String): List<Album> {
        return if (artistAlbumsStorage.contains(artist)) {
            artistAlbumsStorage.getValue(artist)
        } else {
            listOf()
        }
    }

    private fun saveAlbumInfo(albumInfo: AlbumInfo) {
        albumsInfoStorage.add(albumInfo)
    }

    private fun readAlbumInfo(artist: String, album: String): AlbumInfo? {
        return albumsInfoStorage.find { it.artist == artist && it.name == album }
    }

    fun loadArtistAlbumInfo(artist: String, album: String): Single<AlbumInfo> {
        val albumInfo = readAlbumInfo(artist, album)
        return if (!isOnline() || albumInfo != null) {
            Single.just(albumInfo)
        } else {
            api.getArtistAlbumInfo(artist, album)
                    .flatMap { t: ArtistAlbumInfoResponse ->
                        saveAlbumInfo(t.album)
                        Single.just(t.album)
                    }
        }
    }
}