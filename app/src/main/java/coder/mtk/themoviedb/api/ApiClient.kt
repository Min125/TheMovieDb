package coder.mtk.themoviedb.api

import coder.mtk.themoviedb.model.Result
import coder.mtk.themoviedb.model.TopRatedMovies
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    private val BASE_URL = "https://api.themoviedb.org/3/movie/"

    private val apiInterface: ApiInterface

    init {
        var retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiInterface = retrofit.create(ApiInterface::class.java)
    }

    fun getTopRatedMovie(): Call<TopRatedMovies> {
        return apiInterface.getTopRated("9ef2ef916822104b0887b6c1419c6e7c")
    }

    fun getDetailMovie(id : Int) : Call<Result>{
        return apiInterface.getDetailMovie(id,"9ef2ef916822104b0887b6c1419c6e7c")
    }
}