package com.example.reporter.ui.playerhost

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import com.example.reporter.databinding.FragmentPlayerHostBinding
import com.example.reporter.ui.player.PlayerFragment
import com.example.reporter.ui.read.ReadFragment

class PlayerHostFragment : Fragment() {

    private lateinit var binding : FragmentPlayerHostBinding
    private lateinit var viewPagerAdapter : FragmentPagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlayerHostBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        val listFrags = listOf<Fragment>(PlayerFragment(), ReadFragment())
        viewPagerAdapter = ViewPagerAdapter(childFragmentManager, listFrags)

        binding.viewPager.adapter = viewPagerAdapter

    }
}