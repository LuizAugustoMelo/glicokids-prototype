package com.glicokids.prototype.presentation.parents

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.glicokids.prototype.databinding.FragmentParentAreaBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ParentAreaFragment : Fragment() {

    private var _binding: FragmentParentAreaBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentParentAreaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
