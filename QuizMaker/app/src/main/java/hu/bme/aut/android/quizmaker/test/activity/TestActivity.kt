package hu.bme.aut.android.quizmaker.test.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hu.bme.aut.android.quizmaker.databinding.ActivityTestBinding
import hu.bme.aut.android.quizmaker.test.adapter.TestAdapter
import hu.bme.aut.android.quizmaker.test.data.TestDatabase

class TestActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTestBinding
    private lateinit var adapter: TestAdapter
    private lateinit var test_db: TestDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        test_db = TestDatabase.getDatabase(applicationContext)
    }
}