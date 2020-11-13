package com.example.daytonateamrandomizerapplication

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

/**
 * created by jeetendre
 * **/


interface PlayersAPI {
    @GET("fetchPlayerData")
    fun getPlayers() : Call<List<Player>>

}

@Parcelize
class Player(val playerName: String, val playerAttribute: String) : Parcelable

class PlayersRetriever {
    val service : PlayersAPI

    init {

        val retrofit = Retrofit.Builder().baseUrl("https://602d855a-a4f0-406f-b4ce-1e16cd697e75.mock.pstmn.io").addConverterFactory(GsonConverterFactory.create()).build()
        service  = retrofit.create(PlayersAPI::class.java)

    }

    fun getPlayersData(callback : Callback<List<Player>>){

        val call = service.getPlayers()
        call.enqueue(callback)
    }
}