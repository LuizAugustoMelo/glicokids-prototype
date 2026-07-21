package com.glicokids.prototype.presentation.kids

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.ListPopupWindow
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import com.glicokids.prototype.R
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

    private val menuOptions = listOf(
        MenuOption(R.id.menu_gallery, "Galeria de Medalhas", R.drawable.ic_menu_medal),
        MenuOption(R.id.menu_avatar, "Mudar Avatar", R.drawable.ic_menu_avatar),
        MenuOption(R.id.menu_help, "Central de Ajuda", R.drawable.ic_menu_help)
    )

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

        setupMenu()
        setupAnimations()
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

        binding.btnMedals.setOnClickListener {
            UIHelper.navigateTo(requireContext(), GalleryActivity::class.java)
        }

        binding.cardGlucose.setOnClickListener {
            UIHelper.navigateTo(requireContext(), GlucoseLogActivity::class.java)
        }
    }

    private fun setupMenu() {
        val popup = ListPopupWindow(requireContext())
        popup.anchorView = binding.btnMenu
        popup.setAdapter(MenuAdapter(requireContext(), menuOptions))
        popup.width = 700 // Approximate width
        popup.setBackgroundDrawable(AppCompatResources.getDrawable(requireContext(), R.drawable.bg_popup_menu))
        popup.isModal = true

        popup.setOnItemClickListener { _, _, position, _ ->
            val option = menuOptions[position]
            when (option.id) {
                R.id.menu_gallery -> UIHelper.navigateTo(requireContext(), GalleryActivity::class.java)
                R.id.menu_avatar -> UIHelper.navigateTo(requireContext(), AvatarActivity::class.java)
                R.id.menu_help -> UIHelper.navigateTo(requireContext(), HelpActivity::class.java)
            }
            popup.dismiss()
        }

        binding.btnMenu.setOnClickListener {
            popup.show()
        }
    }

    private fun setupAnimations() {
        // Pulse effect for the main button
        val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 1f, 1.05f)
        val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 1f, 1.05f)
        
        ObjectAnimator.ofPropertyValuesHolder(binding.btnNewMeal, scaleX, scaleY).apply {
            duration = 1500
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
            interpolator = AccelerateDecelerateInterpolator()
            start()
        }

        // Orbit rotation effect
        ObjectAnimator.ofFloat(binding.vOrbit, View.ROTATION, 0f, 360f).apply {
            duration = 10000
            repeatCount = ObjectAnimator.INFINITE
            interpolator = android.view.animation.LinearInterpolator()
            start()
        }
    }

    private fun updateGlucoseDisplay() {
        val currentGlucose = 112 // Mocked for Dashboard
        val min = storageRepository.getInt("range_min", 70)
        val max = storageRepository.getInt("range_max", 180)

        val status = UIHelper.glucoseStatus(currentGlucose, min, max)
        val color = UIHelper.getStatusColor(status)

        binding.cardGlucose.strokeColor = color
        binding.tvGlucoseStatus.text = when (status) {
            UIHelper.GlucoseStatus.NA_META -> "mg/dL · Na meta!"
            UIHelper.GlucoseStatus.ATENCAO -> "mg/dL · Atenção"
            UIHelper.GlucoseStatus.FORA_DA_META -> "mg/dL · Fora da meta"
        }
        
        // Update trend chip background based on status
        binding.tvTrend.backgroundTintList = android.content.res.ColorStateList.valueOf(color)
        binding.tvTrend.setTextColor(if (status == UIHelper.GlucoseStatus.NA_META) 
            android.graphics.Color.parseColor("#221650") else android.graphics.Color.WHITE)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private data class MenuOption(val id: Int, val title: String, val iconRes: Int)

    private class MenuAdapter(val context: Context, val options: List<MenuOption>) : BaseAdapter() {
        override fun getCount(): Int = options.size
        override fun getItem(position: Int): Any = options[position]
        override fun getItemId(position: Int): Long = position.toLong()

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_menu_option, parent, false)
            val option = options[position]
            
            val icon = view.findViewById<ImageView>(R.id.ivOptionIcon)
            val title = view.findViewById<TextView>(R.id.tvOptionTitle)
            val divider = view.findViewById<View>(R.id.vDivider)
            
            icon.setImageResource(option.iconRes)
            title.text = option.title
            divider.visibility = if (position == options.size - 1) View.GONE else View.VISIBLE
            
            return view
        }
    }
}
