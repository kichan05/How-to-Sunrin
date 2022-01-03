package com.edcan.howtosunrin.model

import android.content.SharedPreferences

object SharedUtil {
    lateinit var pref : SharedPreferences
    lateinit var editor : SharedPreferences.Editor

    val keyUserId = "userId"
}