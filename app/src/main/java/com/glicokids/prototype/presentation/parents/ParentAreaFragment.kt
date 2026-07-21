package com.glicokids.prototype.presentation.parents

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.glicokids.prototype.databinding.FragmentParentAreaBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ParentAreaFragment : Fragment() {

    private var _binding: FragmentParentAreaBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ParentAreaViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentParentAreaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lblTargetRange.setOnClickListener { showRangeDialog() }
        binding.chipTargetRange.setOnClickListener { showRangeDialog() }

        viewModel.targetRange.observe(viewLifecycleOwner) { range ->
            binding.chipTargetRange.text = "${range.first} - ${range.second} mg/dL"
        }
    }

    private fun showRangeDialog() {
        val currentRange = viewModel.targetRange.value ?: Pair(70, 180)
        
        val layout = LinearLayout(requireContext()).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(60, 20, 60, 0)
        }

        val etMin = EditText(requireContext()).apply {
            hint = "Mínimo"
            inputType = android.text.InputType.TYPE_CLASS_NUMBER
            setText(currentRange.first.toString())
        }
        val etMax = EditText(requireContext()).apply {
            hint = "Máximo"
            inputType = android.text.InputType.TYPE_CLASS_NUMBER
            setText(currentRange.second.toString())
        }

        layout.addView(etMin)
        layout.addView(etMax)

        val dialog = MaterialAlertDialogBuilder(requireContext())
            .setTitle("Editar Faixa Alvo")
            .setMessage("Define quando o app mostra 'na meta'. Confirme com o médico da criança.")
            .setView(layout)
            .setPositiveButton("Salvar", null)
            .setNegativeButton("Cancelar", null)
            .create()

        dialog.show()

        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
            val min = etMin.text.toString().toIntOrNull() ?: 0
            val max = etMax.text.toString().toIntOrNull() ?: 0

            if (viewModel.updateTargetRange(min, max)) {
                dialog.dismiss()
            } else {
                if (min < 40 || min >= max) etMin.error = "Valor inválido"
                if (max > 300) etMax.error = "Máximo 300"
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
