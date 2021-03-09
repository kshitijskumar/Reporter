package com.example.reporter.ui.allnews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.reporter.R
import com.example.reporter.databinding.FragmentAllNewsBinding

class AllNewsFragment : Fragment() {

    private lateinit var binding : FragmentAllNewsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAllNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
    }

    private fun setupViews() {
        binding.playerFab.setOnClickListener {
            findNavController().navigate(R.id.action_allNewsFragment_to_playerHostFragment)
        }
    }
}