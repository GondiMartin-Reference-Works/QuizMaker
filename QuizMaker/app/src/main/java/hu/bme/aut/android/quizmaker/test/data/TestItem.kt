package hu.bme.aut.android.quizmaker.test.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "testItem")
data class TestItem(
    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) var id: Long? = null,
    @ColumnInfo(name = "sumPoints") var sumPoints: Int,
    @ColumnInfo(name = "studPoints") var studPoints: Int
)
