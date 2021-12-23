package com.edcan.howtosunrin.model

import java.io.Serializable

data class Question(
    val question : String = "",
    val answer : String = "",
) : Serializable
