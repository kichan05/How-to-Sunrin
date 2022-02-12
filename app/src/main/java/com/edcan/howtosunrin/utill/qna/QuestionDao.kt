package com.edcan.howtosunrin.utill.qna

import androidx.room.*

@Dao
interface QuestionDao {
    @Query("SELECT * FROM save_questions")
    fun getAll() : Array<Question>

    @Insert
    fun insert(vararg question: Question)

    @Update
    fun update(question: Question)

    @Delete
    fun delete(question: Question)

    @Query("DELETE FROM save_questions WHERE question = :question")
    fun deleteQuestion(question : String)
}