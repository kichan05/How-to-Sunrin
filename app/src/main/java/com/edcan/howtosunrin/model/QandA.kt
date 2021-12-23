package com.edcan.howtosunrin.model

import java.io.Serializable

data class QandA(
    val question : String = "",
    val answer : String = "",
) : Serializable
