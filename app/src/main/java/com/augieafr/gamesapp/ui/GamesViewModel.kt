package com.augieafr.gamesapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.augie.moviecatalogue.api.ApiConfig
import com.augieafr.gamesapp.data.ApiResponse
import com.augieafr.gamesapp.model.GameModel
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
        apiCall.enqueue(object: Callback<List<GameModel>> {
            override fun onResponse(
                call: Call<List<GameModel>>,
                response: Response<List<GameModel>>
            ) {
                response.body()?.let {
                    if (it.isNullOrEmpty()) gameList.value = ApiResponse.Empty
                    else gameList.value = ApiResponse.Success(it)
                }
                isLoading.value = false
            }

            override fun onFailure(call: Call<List<GameModel>>, t: Throwable) {
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