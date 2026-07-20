package com.glicokids.prototype.presentation.kids

import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import com.glicokids.prototype.R
import com.glicokids.prototype.databinding.ActivityGalleryBinding
import com.glicokids.prototype.util.UIHelper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GalleryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGalleryBinding
    private lateinit var medals: List<Medal>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGalleryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener { finish() }

        loadMedals()
        binding.gvMedals.adapter = MedalAdapter(this, medals)
        registerForContextMenu(binding.gvMedals)
    }

    private fun loadMedals() {
        medals = listOf(
            Medal(1, "Primeira Missão", "Você completou sua primeira missão!", R.color.gold),
            Medal(2, "Semana na Meta", "Sete dias seguidos no alvo!", R.color.teal),
            Medal(3, "Corredor Cósmico", "Atividade física registrada hoje.", R.color.primary),
            Medal(4, "10 Fotos de Prato", "Mestre da IA de alimentos.", R.color.gold),
            Medal(5, "Maratonista", "Bloqueada: Complete 20 missões.", R.color.teal, true),
            Medal(6, "Mês Perfeito", "Bloqueada: 30 dias no alvo.", R.color.primary, true)
        )
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        val medal = medals[info.position]
        
        menu?.setHeaderTitle(medal.name)
        menu?.add(0, 1, 0, "Ver Detalhes")
        menu?.add(0, 2, 1, "Compartilhar")
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        val medal = medals[info.position]

        return when (item.itemId) {
            1 -> {
                UIHelper.showToast(this, medal.description)
                true
            }
            2 -> {
                shareMedal(medal)
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    private fun shareMedal(medal: Medal) {
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "Ganhei a medalha ${medal.name} no GlicoKids! 🚀")
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }
}
