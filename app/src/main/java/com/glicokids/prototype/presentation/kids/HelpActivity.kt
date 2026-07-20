package com.glicokids.prototype.presentation.kids

import android.os.Bundle
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.glicokids.prototype.databinding.ActivityHelpBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HelpActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHelpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHelpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener { finish() }

        binding.wvHelp.apply {
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
            loadUrl("https://diabetes.org.br")
        }
    }
}
