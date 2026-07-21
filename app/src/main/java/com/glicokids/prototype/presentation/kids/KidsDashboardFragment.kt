package com.glicokids.prototype.presentation.kids

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.glicokids.prototype.databinding.FragmentKidsDashboardBinding
import com.glicokids.prototype.presentation.parents.ParentSecurityActivity
import com.glicokids.prototype.util.UIHelper
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class KidsDashboardFragment : Fragment() {

    private var _binding: FragmentKidsDashboardBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var storageRepository: com.glicokids.prototype.domain.repository.StorageRepository

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

        setupToolbar()
        updateGlucoseDisplay()

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

    private fun setupToolbar() {
        binding.toolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                com.glicokids.prototype.R.id.menu_gallery -> {
                    UIHelper.navigateTo(requireContext(), GalleryActivity::class.java)
                    true
                }
                com.glicokids.prototype.R.id.menu_avatar -> {
                    UIHelper.navigateTo(requireContext(), AvatarActivity::class.java)
                    true
                }
                com.glicokids.prototype.R.id.menu_help -> {
                    UIHelper.navigateTo(requireContext(), HelpActivity::class.java)
                    true
                }
                else -> false
            }
        }
    }

    private fun updateGlucoseDisplay() {
        val currentGlucose = 112 // Mocked for Dashboard
        val min = storageRepository.getInt("range_min", 70)
        val max = storageRepository.getInt("range_max", 180)

        val status = UIHelper.getGlucoseStatus(currentGlucose, min, max)
        val color = UIHelper.getStatusColor(status)

        binding.cardGlucose.setCardBackgroundColor(color)
        binding.tvGlucoseStatus.text = when (status) {
            UIHelper.GlucoseStatus.NA_META -> "mg/dL · Na meta!"
            UIHelper.GlucoseStatus.ATENCAO -> "mg/dL · Atenção"
            UIHelper.GlucoseStatus.FORA_DA_META -> "mg/dL · Fora da meta"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
