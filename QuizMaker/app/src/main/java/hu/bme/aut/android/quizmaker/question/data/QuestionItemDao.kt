package hu.bme.aut.android.quizmaker.question.data

import androidx.room.*

@Dao
interface QuestionItemDao {
    @Query("SELECT * FROM questionitem")
    fun getAll(): List<QuestionItem>

    @Insert
    fun insert(questionItems: QuestionItem): Long

    @Update
    fun update(questionItem: QuestionItem)

    @Delete
    fun deleteItem(questionItem: QuestionItem)
}