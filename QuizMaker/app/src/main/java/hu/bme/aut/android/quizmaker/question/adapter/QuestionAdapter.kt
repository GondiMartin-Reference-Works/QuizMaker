package hu.bme.aut.android.quizmaker.question.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.android.quizmaker.R
import hu.bme.aut.android.quizmaker.databinding.ActivityDatabaseBinding
import hu.bme.aut.android.quizmaker.databinding.ItemQuestionBinding
import hu.bme.aut.android.quizmaker.question.activity.DatabaseActivity
import hu.bme.aut.android.quizmaker.question.data.QuestionDatabase
import hu.bme.aut.android.quizmaker.question.data.QuestionItem
import kotlin.collections.ArrayList
import kotlin.concurrent.thread

class QuestionAdapter() : RecyclerView.Adapter<QuestionAdapter.DatabaseViewHolder>(){
    private val questionItems: MutableList<QuestionItem> = ArrayList()
    private lateinit var binding: ActivityDatabaseBinding

    interface QuestionItemClickListener {
        fun onDeleteItem(item: QuestionItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DatabaseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_question, parent, false)
        return DatabaseViewHolder(view)
    }

    override fun onBindViewHolder(holder: DatabaseViewHolder, position: Int) {
        val item = questionItems[position]
        holder.bind(item)

        holder.binding.QuestionItemRemoveButton.setOnClickListener {
            val pos = questionItems.indexOf(item)
            deleteItem(pos)
        }
    }

    override fun getItemCount(): Int = questionItems.size

    fun addItem(newQuestionItem: QuestionItem) {
        questionItems.add(newQuestionItem)
        notifyItemInserted(questionItems.size - 1)
    }

    fun update(items: List<QuestionItem>) {
        questionItems.clear()
        questionItems.addAll(items)
        notifyDataSetChanged()
    }

    fun deleteItem(position: Int) {
        questionItems.removeAt(position)
        notifyItemRemoved(position)
        if (position < questionItems.size) {
            notifyItemRangeChanged(position, questionItems.size - position)
        }
    }

    fun delete(quest: QuestionItem){
        val pos: Int = questionItems.indexOf(quest)
        deleteItem(pos)
    }

    inner class DatabaseViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding = ItemQuestionBinding.bind(itemView)
        private var item: QuestionItem? = null

        fun bind(newQuestionItem: QuestionItem?) {
            item = newQuestionItem
            binding.QuestionItemNameTextView.text = item?.text
            binding.QuestionItemValueView.text = item?.value
        }
    }
}