package com.edcan.howtosunrin.utill.user

import com.edcan.howtosunrin.utill.user.UserUtil.SW
import java.io.Serializable

data class User(
    val name : String = "", //유저 이름
    val userID : String = "", //유저 id
    val major : Int = SW, //유저의 학과
    val newbie : Boolean = false //유저가 신입생인지
) : Serializable
//유저 정보를 저장하는 클래스