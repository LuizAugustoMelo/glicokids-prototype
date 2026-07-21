package com.glicokids.prototype.presentation.kids

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.glicokids.prototype.databinding.ActivityRewardBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RewardActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRewardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRewardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBackToBase.setOnClickListener {
            finish()
        }
    }
}
