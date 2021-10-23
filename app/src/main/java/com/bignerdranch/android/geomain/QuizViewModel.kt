package com.bignerdranch.android.geomain

import android.util.Log
import androidx.lifecycle.ViewModel

private const val TAG = "QuizViewModel"

class QuizViewModel(private var currentQuestionIndex: Int = 0) : ViewModel() {
    var currentIndex = 0
    private val questionBank = listOf(
        Question(R.string.question_australia,true),
        Question(R.string.question_oceans,true),
        Question(R.string.question_mideast,false),
        Question(R.string.question_africa,false),
        Question(R.string.question_americas,true),
        Question(R.string.question_asia, true)
    )

    val bankSize: Int get() = questionBank.size

    val currentQuestionAnswer: Boolean
    get() =questionBank[currentIndex].answer

    val currentQuestionText: Int
    get() =questionBank[currentIndex].textResId

    var counterCorrectAnswer = 0
    var counterCompleteQuestion = 0

    var currentQuestionIsEnabled: Boolean get() = questionBank[currentIndex].isEnabled
    set(value) {questionBank[currentIndex].isEnabled = value}

    var isCheated: Boolean get() = questionBank[currentIndex].isCheated
    set(value) {questionBank[currentIndex].isCheated = value}

    fun moveToNext() {
        currentIndex  =  (currentIndex +  1)  %questionBank.size
    }
    fun moveToPrev() {
        currentIndex = if (currentIndex < 1){
            questionBank.size - 1
        }
        else
        {  (currentIndex -  1)  %questionBank.size}
    }
    fun restart () {
        for (element in questionBank){
            element.isEnabled = true
            element.isCheated = false
        }
        counterCorrectAnswer = 0
        counterCompleteQuestion = 0
    }
    fun setCheating () {
        questionBank[currentIndex].isCheated = true
    }
}
