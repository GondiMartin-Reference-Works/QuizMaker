package hu.bme.aut.android.quizmaker.question.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [QuestionItem::class], version = 1)
abstract class QuestionDatabase : RoomDatabase(){
    abstract fun questionItemDao(): QuestionItemDao

    companion object {
        fun getDatabase(applicationContext: Context): QuestionDatabase {
            return Room.databaseBuilder(
                applicationContext,
                QuestionDatabase::class.java,
                "question-list"
            ).build();
        }
    }
}