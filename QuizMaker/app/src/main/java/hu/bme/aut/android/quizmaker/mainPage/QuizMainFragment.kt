package hu.bme.aut.android.quizmaker.mainPage

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import hu.bme.aut.android.quizmaker.question.activity.DatabaseActivity
import hu.bme.aut.android.quizmaker.test.activity.TestActivity
import hu.bme.aut.android.quizmaker.databinding.FragmentQuizMainBinding
import hu.bme.aut.android.quizmaker.question.data.QuestionDatabase
import kotlin.concurrent.thread

class QuizMainFragment : Fragment() {

    private lateinit var binding: FragmentQuizMainBinding
    private lateinit var questDB: QuestionDatabase
    private var empty: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        questDB = QuestionDatabase.getDatabase(this.requireContext().applicationContext)
        thread{
            empty = questDB.questionItemDao().getAll().isEmpty()
        }
    }

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {

        binding = FragmentQuizMainBinding.inflate(layoutInflater)

        val intent = Intent()

        binding.createDatabaseButton.setOnClickListener {
            intent.setClass(this@QuizMainFragment.requireContext(), DatabaseActivity::class.java)
            startActivity(intent)
        }
        binding.learnButton.setOnClickListener {
            if(empty){
                Snackbar.make(
                    binding.root,
                    "Add some question first!",
                    Snackbar.LENGTH_SHORT
                ).show()
            }else{
                intent.setClass(this@QuizMainFragment.requireContext(), TestActivity::class.java)
                startActivity(intent)
            }
        }

        return binding.root
    }
}