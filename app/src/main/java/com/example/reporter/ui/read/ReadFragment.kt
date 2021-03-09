package com.example.reporter.ui.read

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.reporter.databinding.FragmentReadBinding

class ReadFragment : Fragment() {

    private lateinit var binding : FragmentReadBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentReadBinding.inflate(inflater, container, false)
        return binding.root
    }
}