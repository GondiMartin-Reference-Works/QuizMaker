package hu.bme.aut.android.quizmaker.test.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.android.quizmaker.R
import hu.bme.aut.android.quizmaker.databinding.ItemTestBinding
import hu.bme.aut.android.quizmaker.test.data.TestItem

class TestAdapter() : RecyclerView.Adapter<TestAdapter.DatabaseViewHolder>(){
    private val testItems: MutableList<TestItem> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DatabaseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_test, parent, false)
        return DatabaseViewHolder(view)
    }

    override fun onBindViewHolder(holder: DatabaseViewHolder, position: Int) {
        val item = testItems[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = testItems.size

    fun addItem(newTestItem: TestItem) {
        testItems.add(newTestItem)
        notifyItemInserted(testItems.size - 1)
    }

    fun update(items: List<TestItem>) {
        testItems.clear()
        testItems.addAll(items)
        notifyDataSetChanged()
    }

    fun deleteItems() {
        testItems.clear()
        notifyDataSetChanged()
    }

    inner class DatabaseViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding = ItemTestBinding.bind(itemView)
        private var item: TestItem? = null

        @SuppressLint("SetTextI18n")
        fun bind(newTestItem: TestItem?) {
            item = newTestItem
            binding.TestId.text = "Test_" + item!!.id.toString()
            binding.TestScore.text = "Score: " + item!!.studPoints.toString() + "/" + item!!.sumPoints.toString()
        }
    }
}