package com.edcan.howtosunrin.utill.qna

import java.io.Serializable

data class Question(
    val question : String = "", //질문
    val answer : String = "", //질문에 대한 답
) : Serializable

// 질문 하나를 저장하는 클래스
