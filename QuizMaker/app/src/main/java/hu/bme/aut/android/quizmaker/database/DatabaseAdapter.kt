package hu.bme.aut.android.quizmaker.database

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.android.quizmaker.R
import hu.bme.aut.android.quizmaker.databinding.ItemQuestionBinding
import kotlin.collections.ArrayList

class DatabaseAdapter() : RecyclerView.Adapter<DatabaseAdapter.DatabaseViewHolder>(){
    private val questions: MutableList<Question> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DatabaseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_question, parent, false)
        return DatabaseViewHolder(view)
    }

    override fun onBindViewHolder(holder: DatabaseViewHolder, position: Int) {
        val item = questions[position]
        holder.bind(item)

        holder.binding.QuestionItemRemoveButton.setOnClickListener {
            var pos = questions.indexOf(item)
            removeQuestion(pos)
        }
    }

    override fun getItemCount(): Int = questions.size

    fun addQuestion(newQuestion: Question?) {
        if (newQuestion != null) {
            questions.add(newQuestion)
        }
        notifyItemInserted(questions.size - 1)
    }

    fun removeQuestion(position: Int) {
        questions.removeAt(position)
        notifyItemRemoved(position)
        if (position < questions.size) {
            notifyItemRangeChanged(position, questions.size - position)
        }
    }

    inner class DatabaseViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding = ItemQuestionBinding.bind(itemView)
        var item: Question? = null

        fun bind(newQuestion: Question?) {
            item = newQuestion
            binding.QuestionItemNameTextView.text = item?.text
            binding.QuestionItemValueView.text = item?.value
        }
    }
}