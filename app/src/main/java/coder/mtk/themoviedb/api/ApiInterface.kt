package coder.mtk.themoviedb.api

import coder.mtk.themoviedb.model.Result
import coder.mtk.themoviedb.model.TopRatedMovies
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    @GET("top_rated")
    fun getTopRated (
        @Query("api_key") api_key : String
    ) : Call<TopRatedMovies>

    @GET("{movie_id}")
    fun getDetailMovie(
        @Path("movie_id") movie_id : Int,
        @Query("api_key") api_key : String
    ) : Call<Result>
}