package hu.bme.aut.android.quizmaker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import hu.bme.aut.android.quizmaker.databinding.ActivityDatabaseBinding

class DatabaseActivity : AppCompatActivity(), AddQuestionDialogFragment.AddQuestionDialogListener{

    private lateinit var binding : ActivityDatabaseBinding
    private lateinit var adapter: DatabaseAdapter
    private var tempQ : Question = Question(null, null)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDatabaseBinding.inflate(layoutInflater)
        setContentView(binding.root)
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
        adapter = DatabaseAdapter()
        adapter.addQuestion(Question("Kérdés1", "True"))
        adapter.addQuestion(Question("Kérdés2", "True"))
        adapter.addQuestion(Question("Kérdés3", "True"))
        binding.mainRecyclerView.adapter = adapter
    }

    override fun onQuestionAdded(question: String?){
        tempQ.text = question
        adapter.addQuestion(tempQ!!)
    }

    override fun onValueAdded(value: String?) {
        tempQ.value = value
    }
}