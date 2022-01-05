package com.edcan.howtosunrin.model.user

import com.edcan.howtosunrin.model.user.UserUtil.SW
import java.io.Serializable

data class User(
    val name : String = "", //사용자 이름
    val userID : String = "", //사용자 ID
    val major : Int = SW, //사용자의 학과
    val newbie : Boolean = false //사용자가 신입생인지
) : Serializable

// 유저 정보 하나를 저장한 클래스