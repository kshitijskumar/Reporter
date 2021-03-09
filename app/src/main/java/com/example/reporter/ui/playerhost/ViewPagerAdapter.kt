package com.example.reporter.ui.playerhost

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerAdapter(
    fm : FragmentManager,
    private val listFrags: List<Fragment>
    ) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getCount(): Int {
        return listFrags.size
    }

    override fun getItem(position: Int): Fragment {
        return listFrags[position]
    }
}