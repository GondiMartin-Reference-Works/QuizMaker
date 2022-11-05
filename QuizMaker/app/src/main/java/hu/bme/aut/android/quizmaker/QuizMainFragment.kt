package hu.bme.aut.android.quizmaker

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import hu.bme.aut.android.quizmaker.databinding.FragmentQuizMainBinding

class QuizMainFragment : Fragment() {

    private lateinit var binding: FragmentQuizMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {

        binding = FragmentQuizMainBinding.inflate(layoutInflater)

        val intent = Intent()
        binding.createDatabaseButton.setOnClickListener {
            intent.setClass(this@QuizMainFragment.requireContext(), DatabaseActivity::class.java)
            startActivity(intent)
        }
        binding.learnButton.setOnClickListener {
            intent.setClass(this@QuizMainFragment.requireContext(), TestActivity::class.java)
            startActivity(intent)
        }

        return binding.root
    }
}