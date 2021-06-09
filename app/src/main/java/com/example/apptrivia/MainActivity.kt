package com.example.apptrivia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.apptrivia.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_AppTrivia)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun nextActivity(view: View) {

        val name = binding.nameEditText.text.toString()

        val intent = Intent(this,QuestionOne::class.java)
            intent.putExtra(QuestionOne.NAME_EXTRAS,name)
        startActivity(intent)
    }
}