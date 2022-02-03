package com.edcan.howtosunrin.utill.qna

import android.content.Context
import android.content.Intent

object QuestionUtil {
    fun shareQuestion(context : Context, questionData : Question) {

        val shareMsg = "[How to Sunrin]\nQ.${questionData.question}\nA.${questionData.answer}\n\n\n더 많은 선린생활을 알고싶다면 How to Sunrin앱을 설치하세요."

        val shareIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/html"
            //putExtra(Intent.EXTRA_TEXT, Html.fromHtml("<h1>Hello EDCAN</h1>", Html.FROM_HTML_MODE_LEGACY).toString())
            putExtra(Intent.EXTRA_TEXT, shareMsg)
        }

        context.startActivity(Intent.createChooser(shareIntent, "질문을 공유하세요!"))
    }
}