package hu.bme.aut.android.quizmaker.test.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [TestItem::class], version = 1)
abstract class TestDatabase : RoomDatabase(){
    abstract fun testItemDao(): TestItemDao

    companion object {
        fun getDatabase(applicationContext: Context): TestDatabase {
            return Room.databaseBuilder(
                applicationContext,
                TestDatabase::class.java,
                "test-list"
            ).build();
        }
    }
}