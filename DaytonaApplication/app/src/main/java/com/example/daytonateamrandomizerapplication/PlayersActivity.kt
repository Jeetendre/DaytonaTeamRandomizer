package com.example.daytonateamrandomizerapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_teams.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlayersActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teams)

        val playerList = mutableListOf<String>()
        val players = mutableListOf<Player>()
        val playersRetriever = PlayersRetriever()

        val callback = object : Callback<List<Player>> {

            //lets handle the fail scenario
            override fun onFailure(call: Call<List<Player>>, t: Throwable) {

                println("fail")
                print(t.printStackTrace())
                finish()

            }
            //lets handle the success scenario
            override fun onResponse(call: Call<List<Player>>, response: Response<List<Player>>) {
                println("success")
                println(response.body())

                progressBar1.visibility = View.GONE
                buttonDrawTeams.visibility = View.VISIBLE
                listViewPlayers.visibility = View.VISIBLE

                //lets populate the list of players
                for (player in response.body()!!){
                    playerList.add(player.playerName)
                    players.add(player)
                }

                //lets fill up the adapter with the player names
                val adapter  = ArrayAdapter(this@PlayersActivity,android.R.layout.simple_list_item_1, playerList)
                listViewPlayers.adapter = adapter
            }

        }
        //lets call the fetch Players API
        playersRetriever.getPlayersData(callback)

        //lets draw the teams now
        buttonDrawTeams.setOnClickListener {

            val openTeamSheetScreen = Intent(this, TeamDrawsActivity::class.java)

            //lets send the fetched list of players to the next activity to perform the draws
            openTeamSheetScreen.putParcelableArrayListExtra("parcel", ArrayList(players))
            startActivity(openTeamSheetScreen)

        }
    }
}
