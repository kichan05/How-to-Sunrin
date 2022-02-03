package com.edcan.howtosunrin.utill.qna

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "save_questions")
data class Question(
    @PrimaryKey(autoGenerate = true)
    var _id : Int = 0,

    @ColumnInfo(name = "question")
    var question : String = "", //질문

    @ColumnInfo(name = "answer")
    var answer : String = "", //질문에 대한 답
) : Serializable

// 질문 하나를 저장하는 클래스
