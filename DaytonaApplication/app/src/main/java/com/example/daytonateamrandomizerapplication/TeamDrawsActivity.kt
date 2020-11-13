package com.example.daytonateamrandomizerapplication
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_team_draw.*

class TeamDrawsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_draw)

        //lets define some variables
        val attackingPlayer = "(FW)"
        val midfieldPlayer = "(MID)"
        val defensivePlayer = "(DF)"
        val goalkeeper = "(GK)"

        //lets retrieve the list of players from previous activity
        val item = intent.getParcelableArrayListExtra<Player>("parcel")

        //lets split the list of players based on player attributes
        //2: normal player
        //3: super player
        val (listA, listB) = item.partition { e -> e.playerAttribute.toInt() == 3 }

        var attackingPlayers: MutableList<Player> = listA.toMutableList()
        var defensivePlayers: MutableList<Player> = listB.toMutableList()

        attackingPlayers.shuffle()
        defensivePlayers.shuffle()

        //TODO: rework logic of balancing teams
        balanceTeams("Yoven",getString(R.string.yadav_bro),attackingPlayers.subList(0, 2))
        balanceTeams("Yoven",getString(R.string.yadav_bro),attackingPlayers.subList(2, 4))

        //lets populate the attacking views
        teamAP4.text = attackingPlayers[0].playerName + attackingPlayer
        teamAP6.text = attackingPlayers[1].playerName + midfieldPlayer

        teamBP4.text = attackingPlayers[2].playerName + attackingPlayer
        teamBP6.text = attackingPlayers[3].playerName + midfieldPlayer

        //lets populate the defensive views
        teamAP1.text = defensivePlayers[0].playerName + defensivePlayer
        teamAP2.text = defensivePlayers[1].playerName + goalkeeper
        teamAP3.text = defensivePlayers[2].playerName + defensivePlayer

        teamBP1.text = defensivePlayers[3].playerName + defensivePlayer
        teamBP2.text = defensivePlayers[4].playerName + goalkeeper
        teamBP3.text = defensivePlayers[5].playerName + defensivePlayer

        //lets do the draws again
        buttonAgainDraw.setOnClickListener {
            finish()
            startActivity(intent)
        }
    }

    //lets randomize again if the two players are in the same team
    private fun balanceTeams(player1: String, player2: String, listOfPlayers: MutableList<Player>) {

        listOfPlayers.forEach { players ->
            if (players.playerName.contentEquals(player1)) {

                listOfPlayers.forEach { player ->
                    if (player.playerName.contentEquals(player2)) {
                        listOfPlayers.shuffle()
                    }
                }
            }
        }
    }
}
