package com.example.apptrivia

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class HistoryListAdapter(private val context: Activity, private val playerName: Array<String>, private val answerOne: Array<String>, private val answerTwo: Array<String>)
    : ArrayAdapter<String>(context, R.layout.history_list, answerOne) {

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.history_list, null, true)

        val nameText = rowView.findViewById(R.id.textViewName) as TextView
        val answerOneText = rowView.findViewById(R.id.textViewAnswerOne) as TextView
        val answerTwoText = rowView.findViewById(R.id.textViewAnswerTwo) as TextView

        nameText.text = "Name: ${playerName[position]}"
        answerOneText.text = "Answer One: ${answerOne[position]}"
        answerTwoText.text = "Answer Two: ${answerTwo[position]}"
        return rowView
    }
}