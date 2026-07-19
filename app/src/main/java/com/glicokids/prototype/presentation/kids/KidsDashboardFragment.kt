package com.glicokids.prototype.presentation.kids

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.glicokids.prototype.databinding.FragmentKidsDashboardBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class KidsDashboardFragment : Fragment() {

    private var _binding: FragmentKidsDashboardBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentKidsDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnParentArea.setOnClickListener {
            // Requisito Módulo 2: Navegação via Intent com Passagem de Dados (Extras)
            val intent = Intent(requireContext(), com.glicokids.prototype.presentation.parents.ParentSecurityActivity::class.java).apply {
                putExtra("CHILD_NAME", "Lucas")
            }
            startActivity(intent)
        }

        binding.btnNewMeal.setOnClickListener {
            // Módulo 3: Abrindo Missão da Refeição via Intent
            val intent = Intent(requireContext(), NewMealActivity::class.java).apply {
                putExtra("CHILD_NAME", "Lucas")
            }
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
