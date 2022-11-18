package hu.bme.aut.android.quizmaker.question.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "questionitem")
data class QuestionItem(
    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) var id: Long? = null,
    @ColumnInfo(name = "text") var text: String,
    @ColumnInfo(name = "value") var value: String
)
