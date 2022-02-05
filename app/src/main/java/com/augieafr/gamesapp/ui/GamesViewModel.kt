package com.augieafr.gamesapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.augie.moviecatalogue.api.ApiConfig
import com.augieafr.gamesapp.data.ApiResponse
import com.augieafr.gamesapp.model.GameModel
import com.augieafr.gamesapp.model.ResponseList
import com.augieafr.gamesapp.ui.games.GamesDomainType
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GamesViewModel : ViewModel() {

    var isLoading = MutableLiveData<Boolean>()

    val gameList = MutableLiveData<ApiResponse<List<GameModel>>>()

    val game = MutableLiveData<ApiResponse<GameModel>>()

    private val apiService = ApiConfig.getApiService()

    fun getGameList(domainType: GamesDomainType) {
        isLoading.value = true
        val apiCall = when(domainType) {
            GamesDomainType.NEWEST_GAMES -> apiService.getNewestGames()
            GamesDomainType.POPULAR_GAMES -> apiService.getPopularGames()
        }

        apiCall.enqueue(object: Callback<ResponseList> {
            override fun onResponse(call: Call<ResponseList>, response: Response<ResponseList>) {
                response.body()?.let {
                    val gameData = it.results
                    if (gameData.isNullOrEmpty()) gameList.value = ApiResponse.Empty
                    else gameList.value = ApiResponse.Success(gameData)
                }
                isLoading.value = false
            }

            override fun onFailure(call: Call<ResponseList>, t: Throwable) {
                gameList.value = ApiResponse.Error("Failed to fetch data")
                isLoading.value = false
            }
        })
    }

    fun getDetailGame(gameId: Int){
        isLoading.value = true
        apiService.getDetailGame(gameId).enqueue(object: Callback<GameModel> {
            override fun onResponse(call: Call<GameModel>, response: Response<GameModel>) {
                response.body()?.let {
                    game.value = ApiResponse.Success(it)
                }
                isLoading.value = false
            }

            override fun onFailure(call: Call<GameModel>, t: Throwable) {
                game.value = ApiResponse.Error("Failed to fetch data")
                isLoading.value = false
            }
        })
    }
}