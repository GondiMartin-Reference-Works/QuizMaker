package hu.bme.aut.android.quizmaker.test.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hu.bme.aut.android.quizmaker.databinding.ActivityTestBinding
import hu.bme.aut.android.quizmaker.question.adapter.QuestionAdapter
import hu.bme.aut.android.quizmaker.question.data.QuestionDatabase
import hu.bme.aut.android.quizmaker.test.adapter.TestAdapter
import hu.bme.aut.android.quizmaker.test.data.TestDatabase
import kotlin.concurrent.thread

class TestActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTestBinding
    private lateinit var test_adapt: TestAdapter
    private lateinit var quest_adapt: QuestionAdapter
    private lateinit var test_db: TestDatabase
    private lateinit var quest_db: QuestionDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        test_db = TestDatabase.getDatabase(applicationContext)
        quest_db = QuestionDatabase.getDatabase(applicationContext)
        test_adapt = TestAdapter()
        quest_adapt = QuestionAdapter()

        loadItemsInBackground()
    }

    private fun loadItemsInBackground() {
        thread {
            val testItems = test_db.testItemDao().getAll()
            val questItems = quest_db.questionItemDao().getAll()
            runOnUiThread {
                test_adapt.update(testItems)
                quest_adapt.update(questItems)
            }
        }
    }
}