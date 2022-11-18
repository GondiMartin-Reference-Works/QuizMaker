package hu.bme.aut.android.quizmaker.question.data

import androidx.room.*

@Dao
interface QuestionItemDao {
    @Query("SELECT * FROM questionitem")
    fun getAll(): List<QuestionItem>

    @Insert
    fun insert(shoppingItems: QuestionItem): Long

    @Update
    fun update(shoppingItem: QuestionItem)

    @Delete
    fun deleteItem(shoppingItem: QuestionItem)
}