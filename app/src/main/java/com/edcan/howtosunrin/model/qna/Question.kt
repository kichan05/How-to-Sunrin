package com.edcan.howtosunrin.model.qna

import java.io.Serializable

data class Question(
    val question : String = "",
    val answer : String = "",
) : Serializable
