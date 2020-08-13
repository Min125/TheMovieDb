package coder.mtk.themoviedb.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import coder.mtk.themoviedb.api.ApiClient
import coder.mtk.themoviedb.model.Result
import coder.mtk.themoviedb.model.TopRatedMovies
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TopRatedMoviesViewModel : ViewModel() {
    private var result : MutableLiveData<TopRatedMovies> = MutableLiveData()
    private var details : MutableLiveData<Result> = MutableLiveData()

    fun getResult () : LiveData<TopRatedMovies> = result
    fun getDetaild () : LiveData<Result> = details

    fun loadTopRatedMovie (){
        var apiClient = ApiClient()
        var apiCall = apiClient.getTopRatedMovie()

        apiCall.enqueue(
            object : Callback <TopRatedMovies>{
                override fun onFailure(call: Call<TopRatedMovies>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<TopRatedMovies>,
                    response: Response<TopRatedMovies>
                ) {
                    result.value = response.body()
                    Log.d("movie >>>>>>>",response.body().toString())
                }
            }

        )
    }

    fun loadDetai(id : Int){
        var apiClient = ApiClient()
        var apiCall = apiClient.getDetailMovie(id)

        apiCall.enqueue(
            object : Callback<Result>{
                override fun onFailure(call: Call<Result>, t: Throwable) {

                }

                override fun onResponse(call: Call<Result>, response: Response<Result>) {
                    details.value = response.body()
            }

            }
        )
    }
}