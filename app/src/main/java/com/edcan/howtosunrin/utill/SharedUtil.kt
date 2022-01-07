package com.edcan.howtosunrin.utill

import android.content.SharedPreferences

object SharedUtil {
    lateinit var pref : SharedPreferences
    lateinit var editor : SharedPreferences.Editor

    const val keyUserId = "userId"
}

//SharedPreferences에 저장하는 값들을 다루는 오브젝트