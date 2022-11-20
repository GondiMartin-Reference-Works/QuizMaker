package hu.bme.aut.android.quizmaker.mainPage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import hu.bme.aut.android.quizmaker.R
import hu.bme.aut.android.quizmaker.databinding.FragmentQuizResultBinding
import hu.bme.aut.android.quizmaker.question.adapter.QuestionAdapter
import hu.bme.aut.android.quizmaker.test.adapter.TestAdapter
import hu.bme.aut.android.quizmaker.test.data.TestDatabase
import kotlin.concurrent.thread

class QuizResultFragment : Fragment() {

    private lateinit var binding: FragmentQuizResultBinding
    private lateinit var adapter: TestAdapter
    private lateinit var database: TestDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = FragmentQuizResultBinding.inflate(layoutInflater)

        database = TestDatabase.getDatabase(requireContext().applicationContext)

        initFab()
        initRecyclerView()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        return binding.root
    }

    private fun initFab() {
        binding.deleteAll.setOnClickListener {
            thread{
                val items = database.testItemDao().getAll()
                activity?.runOnUiThread{
                    for(item in items){
                        adapter.deleteItems()
                    }
                }
                database.testItemDao().deleteAll()
            }
        }
    }

    private fun initRecyclerView() {
        binding.testRecyclerView.layoutManager = LinearLayoutManager(this.requireContext())
        adapter = TestAdapter()
        binding.testRecyclerView.adapter = adapter
        loadItemsInBackground()
    }

    private fun loadItemsInBackground() {
        thread {
            val items = database.testItemDao().getAll()
            activity?.runOnUiThread {
                adapter.update(items)
            }
        }
    }
}