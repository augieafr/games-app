package com.augieafr.gamesapp.data.api

import com.augieafr.gamesapp.model.GameModel
import com.augieafr.gamesapp.model.ResponseList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("games?key=6e7a6013987c4179a8f751e9cd0c7e97&ordering=-released")
    fun getNewestGames() : Call<ResponseList>

    @GET("games?key=6e7a6013987c4179a8f751e9cd0c7e97&ordering=-rating")
    fun getPopularGames() : Call<ResponseList>

    @GET("games/{gameId}?key=6e7a6013987c4179a8f751e9cd0c7e97")
    fun getDetailGame(
        @Path("gameId") gameId: Int
    ): Call<GameModel>
}