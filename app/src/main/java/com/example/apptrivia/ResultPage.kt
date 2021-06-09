package com.example.apptrivia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.apptrivia.databinding.ActivityResultPageBinding

class ResultPage : AppCompatActivity() {

    lateinit var binding: ActivityResultPageBinding

    //lateinit var viewModel: HistoryViewModel
    companion object {
        const val NAME_EXTRA = "name_extra"
        const val QUE_ONE = "choiceOne_extra"
        const val QUE_TWO = "choiceTwo_extra"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_AppTrivia)
        binding = ActivityResultPageBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val name = intent.getStringExtra(NAME_EXTRA)
        val answerOne = intent.getStringExtra(QUE_ONE)
        val answerTwo = intent.getStringExtra(QUE_TWO)

        binding.nameView.text = name
        binding.answerOneView.text = answerOne
        binding.answerTwoView.text = answerTwo


    }

    fun mainPage(view: View) {

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun saveRecord(view: View) {
        
        val playerName = binding.nameView.text.toString()
        val answerOne = binding.answerOneView.text.toString()
        val answerTwo = binding.answerTwoView.text.toString()
        val databaseHandler: DatabaseHandler = DatabaseHandler(this)
        if (playerName.trim() != "" && answerOne.trim() != "" && answerTwo.trim() != "") {
            val status =
                databaseHandler.addHistory(HistoryModelClass(playerName, answerOne, answerTwo))
            if (status > -1) {
                Toast.makeText(applicationContext, "record save", Toast.LENGTH_LONG).show()
                binding.uName.text.clear()
                binding.uAnswerOne.text.clear()
                binding.uAnswerTwo.text.clear()

            }
        } else {
            Toast.makeText(
                applicationContext,
                "id or name or email cannot be blank",
                Toast.LENGTH_LONG
            ).show()
        }


    }

    fun viewRecord(view: View) {
        //creating the instance of DatabaseHandler class
        val databaseHandler: DatabaseHandler= DatabaseHandler(this)
        //calling the viewEmployee method of DatabaseHandler class to read the records
        val emp: List<HistoryModelClass> = databaseHandler.viewHistory()
        val historyArrayName = Array(emp.size){"0"}
        val historyArrayAnswerOne = Array(emp.size){"null"}
        val historyArrayAnswerTwo = Array(emp.size){"null"}
        var index = 0
        for(e in emp){
            historyArrayName[index] = e.name
            historyArrayAnswerOne[index] = e.answerOne
            historyArrayAnswerTwo[index] = e.answerTwo
            index++
        }
        //creating custom ArrayAdapter
        val myListAdapter = HistoryListAdapter(this,historyArrayName,historyArrayAnswerOne,historyArrayAnswerTwo)
        binding.listView.adapter = myListAdapter
    }
}