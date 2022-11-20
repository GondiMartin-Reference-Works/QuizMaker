package hu.bme.aut.android.quizmaker.test.data

import androidx.room.*

@Dao
interface TestItemDao {
    @Query("SELECT * FROM testItem")
    fun getAll(): List<TestItem>

    @Insert
    fun insert(testItems: TestItem): Long

    @Update
    fun update(testItem: TestItem)

    @Delete
    fun deleteItem(testItem: TestItem)
}