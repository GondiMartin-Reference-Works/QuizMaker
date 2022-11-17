package hu.bme.aut.android.quizmaker.learn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hu.bme.aut.android.quizmaker.databinding.ActivityTestBinding

class TestActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTestBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTestBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}