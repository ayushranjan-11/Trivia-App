package com.example.apptrivia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.apptrivia.databinding.ActivityQuestionOneBinding

class QuestionOne : AppCompatActivity() {
    lateinit var binding: ActivityQuestionOneBinding

    lateinit var lastChoice:String

    companion object {
        const val NAME_EXTRAS="Name_extras"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_AppTrivia)
        binding = ActivityQuestionOneBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    fun nextActivity(view: View) {

        val selectId = binding.questionOneRadioGroup.checkedRadioButtonId

        val checkedButton = when(selectId){
            R.id.questionOneAnswerOne-> "Sachin Tendulkar"
            R.id.questionOneAnswerTwo-> "Virat Kohli"
            R.id.questionOneAnswerThree->"Adam Gilchrist"
            R.id.questionOneAnswerFour->"Jacques Kallis"
            else->"Sachin Tendulkar"
        }
        lastChoice=checkedButton

        val names = intent.getStringExtra(NAME_EXTRAS)

        val intent = Intent(this,QuestionTwo::class.java)
        intent.putExtra(QuestionTwo.NAME_EX,names)
        intent.putExtra(QuestionTwo.QU_ON,lastChoice)
        startActivity(intent)
    }
}