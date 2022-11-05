package hu.bme.aut.android.quizmaker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hu.bme.aut.android.quizmaker.databinding.ActivityDatabaseBinding

class DatabaseActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDatabaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDatabaseBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}