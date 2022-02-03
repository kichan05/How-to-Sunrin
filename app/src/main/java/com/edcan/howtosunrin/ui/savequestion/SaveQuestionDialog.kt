package com.edcan.howtosunrin.ui.savequestion

import android.app.Dialog
import android.content.Context
import android.view.Window
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.edcan.howtosunrin.R
import com.edcan.howtosunrin.ui.splash.saveQuestionDB
import com.edcan.howtosunrin.utill.qna.Question
import com.edcan.howtosunrin.utill.qna.QuestionUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SaveQuestionDialog(val context : Context, val questionData : Question) {
    private val dialog = Dialog(context)

    fun start() = with(dialog){
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_save_question)

        findViewById<TextView>(R.id.txt_saveQnADialog_question).apply {
            text = questionData.question
        }
        findViewById<TextView>(R.id.txt_saveQnADialog_answer).apply {
            text = questionData.answer
        }
        findViewById<Button>(R.id.btn_saveQnADialog_share).apply {
            setOnClickListener { QuestionUtil.shareQuestion(context, questionData) }
        }
        findViewById<Button>(R.id.btn_saveQnADialog_delete).apply {
            setOnClickListener {

                CoroutineScope(Dispatchers.IO).launch {
                    saveQuestionDB.questionDao().delete(questionData)
                    withContext(Dispatchers.Main){
                        dialog.dismiss()

                        Toast.makeText(context, "질문이 삭제되었습니다.", Toast.LENGTH_LONG).show()
                    }
                }

            }
        }
        findViewById<Button>(R.id.btn_saveQnADialog_ok).apply {
            setOnClickListener { dialog.dismiss() }
        }

        show()
    }
}