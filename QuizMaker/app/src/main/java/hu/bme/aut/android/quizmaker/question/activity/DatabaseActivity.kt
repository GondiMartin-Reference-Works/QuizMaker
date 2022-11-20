package hu.bme.aut.android.quizmaker.question.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import hu.bme.aut.android.quizmaker.databinding.ActivityDatabaseBinding
import hu.bme.aut.android.quizmaker.databinding.ItemQuestionBinding
import hu.bme.aut.android.quizmaker.question.adapter.QuestionAdapter
import hu.bme.aut.android.quizmaker.question.adapter.QuestionAdapter.DatabaseViewHolder
import hu.bme.aut.android.quizmaker.question.data.QuestionDatabase
import hu.bme.aut.android.quizmaker.question.data.QuestionItem
import hu.bme.aut.android.quizmaker.question.fragment.AddQuestionDialogFragment
import kotlin.concurrent.thread

class DatabaseActivity : AppCompatActivity(), AddQuestionDialogFragment.AddQuestionDialogListener, QuestionAdapter.QuestionItemClickListener {

    private lateinit var binding : ActivityDatabaseBinding
    private lateinit var adapter: QuestionAdapter
    private lateinit var database: QuestionDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDatabaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = QuestionDatabase.getDatabase(applicationContext)

        initFab()
        initRecyclerView()
    }

    private fun initFab() {
        binding.fab.setOnClickListener {
            AddQuestionDialogFragment().show(supportFragmentManager, AddQuestionDialogFragment::class.java.simpleName)
        }
    }

    private fun initRecyclerView() {
        binding.mainRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = QuestionAdapter()
        binding.mainRecyclerView.adapter = adapter
        loadItemsInBackground()
    }

    private fun loadItemsInBackground() {
        thread {
            val items = database.questionItemDao().getAll()
            runOnUiThread {
                adapter.update(items)
            }
        }
    }

    override fun onQuestionItemCreated(newItem: QuestionItem){
        thread {
            val insertId = database.questionItemDao().insert(newItem)
            newItem.id = insertId
            runOnUiThread {
                adapter.addItem(newItem)
            }
        }
    }

    override fun onDeleteItem(item: QuestionItem) {
        thread{
            database.questionItemDao().deleteItem(item)
            Log.d("MainActivity", "ShoppingItem delete was successful")
        }
        initRecyclerView()
    }

}