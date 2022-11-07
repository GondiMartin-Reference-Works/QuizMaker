package hu.bme.aut.android.quizmaker

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ToggleButton
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDialogFragment
import hu.bme.aut.android.quizmaker.databinding.DialogNewQuestionBinding
import kotlinx.coroutines.selects.select

class AddQuestionDialogFragment : AppCompatDialogFragment() {
    private lateinit var binding: DialogNewQuestionBinding
    private lateinit var listener: AddQuestionDialogListener

    interface AddQuestionDialogListener{
        fun onQuestionAdded(question: String?)
        fun onValueAdded(value: String?)
    }

    override fun onAttach(context: Context){
        super.onAttach(context)
        binding = DialogNewQuestionBinding.inflate(LayoutInflater.from(context))

        listener = context as? AddQuestionDialogListener
            ?: throw RuntimeException("Activity must implement the AddQuestionDialogListener interface!")
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val trueOrFalse = arrayOf<String>("True", "False")

        return AlertDialog.Builder(requireContext())
            .setTitle(R.string.new_question)
            .setView(binding.root)
            .setPositiveButton(R.string.ok) { _, _ ->
                listener.onQuestionAdded(
                    binding.NewQuestionDialogEditText.text.toString()
                )
            }
            .setSingleChoiceItems(trueOrFalse, 0,
                DialogInterface.OnClickListener{ _, which ->
                    when (which) {
                        0 -> listener.onValueAdded("True")
                        1 -> listener.onValueAdded("False")
                        else -> {
                            listener.onValueAdded("True")
                        }
                    }
                }
            )
            .setNegativeButton(R.string.cancel, null)
            .create()
    }
}