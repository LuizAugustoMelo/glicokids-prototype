package com.glicokids.prototype.presentation.kids

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ViewSwitcher
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.glicokids.prototype.R
import com.glicokids.prototype.databinding.ActivityAvatarBinding
import com.glicokids.prototype.util.UIHelper
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AvatarActivity : AppCompatActivity(), ViewSwitcher.ViewFactory {

    private lateinit var binding: ActivityAvatarBinding
    private val viewModel: AvatarViewModel by viewModels()

    private val avatars = arrayOf(
        R.drawable.ic_avatar_1,
        R.drawable.ic_avatar_2,
        R.drawable.ic_avatar_3,
        R.drawable.ic_avatar_4,
        R.drawable.ic_avatar_5
    )

    private val avatarNames = arrayOf(
        "Glico Clássico",
        "Glico Astronauta",
        "Glico Dourado",
        "Glico Rosa",
        "Glico Esmeralda"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAvatarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener { finish() }

        binding.isAvatar.setFactory(this)
        
        observeViewModel()

        binding.btnNext.setOnClickListener {
            binding.isAvatar.setInAnimation(this, android.R.anim.slide_in_left)
            binding.isAvatar.setOutAnimation(this, android.R.anim.slide_out_right)
            viewModel.nextAvatar()
        }

        binding.btnPrevious.setOnClickListener {
            binding.isAvatar.setInAnimation(this, android.R.anim.slide_in_left)
            binding.isAvatar.setOutAnimation(this, android.R.anim.slide_out_right)
            viewModel.previousAvatar()
        }

        binding.btnChooseAvatar.setOnClickListener {
            val index = viewModel.currentIndex.value ?: 0
            viewModel.selectAvatar(index)
            UIHelper.showToast(this, "Avatar escolhido: ${avatarNames[index]}")
            finish()
        }
    }

    private fun observeViewModel() {
        viewModel.currentIndex.observe(this) { index ->
            binding.isAvatar.setImageResource(avatars[index])
            binding.tvAvatarName.text = avatarNames[index]
        }
    }

    override fun makeView(): View {
        val imageView = ImageView(this)
        imageView.layoutParams = ViewSwitcher.LayoutParams(
            ViewSwitcher.LayoutParams.MATCH_PARENT,
            ViewSwitcher.LayoutParams.MATCH_PARENT
        )
        imageView.scaleType = ImageView.ScaleType.FIT_CENTER
        return imageView
    }
}
