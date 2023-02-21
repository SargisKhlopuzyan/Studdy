package com.example.studdy

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class DemoCollectionPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    override fun getCount(): Int = 2

    override fun getItem(i: Int): Fragment {
        return if (i == 0) {
            StudyFragment()
        } else {
            RestFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence {
        return if (position == 0) {
            "Study"
        } else {
            "Rest"
        }
    }
}