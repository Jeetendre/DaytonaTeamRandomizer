package com.example.daytonateamrandomizerapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //lets display the list of players available
        buttonGenerateTeams.setOnClickListener {

            val openHomeScreen = Intent(this, PlayersActivity::class.java)
            startActivity(openHomeScreen)

        }
    }
}
