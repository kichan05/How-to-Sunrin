package com.edcan.howtosunrin.model.user

import com.edcan.howtosunrin.model.user.UserUtil.SW
import java.io.Serializable

data class User(
    val name : String = "",
    val userID : String = "",
    val major : Int = SW,
    val newbie : Boolean = false
) : Serializable
