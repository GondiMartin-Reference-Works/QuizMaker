package hu.bme.aut.android.quizmaker.test.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import hu.bme.aut.android.quizmaker.databinding.ActivityTestBinding
import hu.bme.aut.android.quizmaker.databinding.FragmentQuizResultBinding
import hu.bme.aut.android.quizmaker.mainPage.QuizActivity
import hu.bme.aut.android.quizmaker.question.activity.DatabaseActivity
import hu.bme.aut.android.quizmaker.question.adapter.QuestionAdapter
import hu.bme.aut.android.quizmaker.question.data.QuestionDatabase
import hu.bme.aut.android.quizmaker.question.data.QuestionItem
import hu.bme.aut.android.quizmaker.question.fragment.AddQuestionDialogFragment
import hu.bme.aut.android.quizmaker.test.adapter.TestAdapter
import hu.bme.aut.android.quizmaker.test.data.TestDatabase
import hu.bme.aut.android.quizmaker.test.data.TestItem
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.thread

class TestActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTestBinding
    private lateinit var testAdapt: TestAdapter
    private lateinit var testDB: TestDatabase
    private lateinit var questDB: QuestionDatabase

    private val questionItems: MutableList<QuestionItem> = ArrayList()
    private lateinit var question: QuestionItem
    private lateinit var test: TestItem
    private var maxQuestionNum: Int = 0
    private var answeredQuestionNumber: Int = -1

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        testDB = TestDatabase.getDatabase(applicationContext)
        questDB = QuestionDatabase.getDatabase(applicationContext)
        testAdapt = TestAdapter()

        loadItemsInBackground()
    }

    private fun loadItemsInBackground() {
        thread {
            val testItems = testDB.testItemDao().getAll()
            runOnUiThread {
                testAdapt.update(testItems)
            }

            //Load all the questions
            for(question in questDB.questionItemDao().getAll()){
                questionItems.add(question)
            }
            maxQuestionNum = questionItems.size
            progressUp()

            // First question
            question = popOneQuestion()
            binding.cardText.text =  question.text

            //Create new test
            test = TestItem(
                sumPoints = maxQuestionNum,
                studPoints = 0
            )

            val insertId = testDB.testItemDao().insert(test)
            test.id = insertId
            runOnUiThread{
                testAdapt.addItem(test)
            }
        }
    }

    private fun popOneQuestion(): QuestionItem {
        val random: Random = Random()
        val randomNumber: Int = random.nextInt(questionItems.size - 0) + 0
        return questionItems.removeAt(randomNumber)
    }

    @SuppressLint("SetTextI18n")
    private fun progressUp(){
        thread{
            ++answeredQuestionNumber
            binding.progressTextView.text = "$answeredQuestionNumber/$maxQuestionNum"
        }
    }
}