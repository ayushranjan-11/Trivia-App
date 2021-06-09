package com.example.apptrivia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class PlayerHistory : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_AppTrivia)
        setContentView(R.layout.activity_player_history)
    }
}