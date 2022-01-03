package com.edcan.howtosunrin.model.user

import com.edcan.howtosunrin.model.user.UserUtill.SW
import java.io.Serializable

data class User(
    val name : String = "",
    val userID : String = "",
    val major : Int = SW,
    val newbi : Boolean = false
) : Serializable
