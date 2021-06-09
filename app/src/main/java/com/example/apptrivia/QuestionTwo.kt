package com.example.apptrivia

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import androidx.appcompat.app.AppCompatActivity
import com.example.apptrivia.databinding.ActivityQuestionTwoBinding

class QuestionTwo : AppCompatActivity() {

    lateinit var binding: ActivityQuestionTwoBinding

     var totalChoice:String =""

    companion object {
        const val NAME_EX = "name_ex"
        const val QU_ON = "ques_one"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_AppTrivia)
        binding = ActivityQuestionTwoBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
    fun onCheckBoxClicked(view: View) {

         var totalColors:String = ""

        if (view is CheckBox) {
            val checked: Boolean = view.isChecked

            when (view.id) {
                R.id.questionTwoOptionOne -> {
                    if (checked) {
                        totalColors+=binding.questionTwoOptionOne.text.toString()+","
                    } else {
                        //ignore
                    }
                }
                R.id.questionTwoOptionTwo -> {
                    if (checked) {
                        totalColors+=binding.questionTwoOptionTwo.text.toString()+","
                    } else {
                        // Ignore
                    }
                }

                R.id.questionTwoOptionThree -> {
                    if (checked) {
                        totalColors+=binding.questionTwoOptionThree.text.toString()+","
                    } else {
                        // Ignore
                    }
                }

                R.id.questionTwoOptionFour -> {
                    if (checked) {
                        totalColors += binding.questionTwoOptionFour.text.toString()+","
                    } else {
                        // Ignore
                    }
                }

            }
        }
        totalChoice += totalColors
    }


    fun nextActivity(view: View) {

        val name1 = intent.getStringExtra(NAME_EX)
        val name2 = intent.getStringExtra(QU_ON)

        val intent = Intent(this,ResultPage::class.java)
        intent.putExtra(ResultPage.NAME_EXTRA,name1)
        intent.putExtra(ResultPage.QUE_ONE,name2)
        intent.putExtra(ResultPage.QUE_TWO,totalChoice)
        startActivity(intent)
    }

}