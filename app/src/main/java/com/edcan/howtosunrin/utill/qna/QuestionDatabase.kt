package com.edcan.howtosunrin.utill.qna

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Question::class], version = 1)
abstract class QuestionDatabase : RoomDatabase() {
    abstract fun questionDao() : QuestionDao

    companion object{
        @Volatile
        private var INSTANCE : QuestionDatabase? = null

        fun getInstance(context : Context) : QuestionDatabase {
            return INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                QuestionDatabase::class.java,
                "question_database"
            )
                .fallbackToDestructiveMigration()
                .build()
                .also { INSTANCE = it }
        }
    }
}