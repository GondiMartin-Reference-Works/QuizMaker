package hu.bme.aut.android.quizmaker.database

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDialogFragment
import hu.bme.aut.android.quizmaker.databinding.DialogNewQuestionBinding

class AddQuestionDialogFragment : AppCompatDialogFragment() {
    private lateinit var binding: DialogNewQuestionBinding
    private lateinit var listener: AddQuestionDialogListener

    interface AddQuestionDialogListener{
        fun onQuestionAdded(question: Question?)
        fun onValueAdded(value: String?)
    }

    override fun onAttach(context: Context){
        super.onAttach(context)
        binding = DialogNewQuestionBinding.inflate(LayoutInflater.from(context))

        listener = context as? AddQuestionDialogListener
            ?: throw RuntimeException("Activity must implement the AddQuestionDialogListener interface!")
    }

    private fun isValid() = binding.NewQuestionDialogEditText.text.isNotEmpty()

    private fun getQuestionItem() = Question(
        text = binding.NewQuestionDialogEditText.text.toString(),
        value = trueOrFalse()
    )

    private fun trueOrFalse(): String {
        return if(binding.rbTrue.isChecked)
            "True"
        else
            "False"
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        return AlertDialog.Builder(requireContext())
            .setTitle("New question")
            .setView(binding.root)
            .setPositiveButton("OK") { _, _ ->
                if (isValid()) {
                    listener.onQuestionAdded(getQuestionItem())
                }
            }
            .setNegativeButton("Cancel", null)
            .create()
    }
}